package parserNodes;
import java.util.Stack;

import helpers.SymbolTable;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class BodyNode implements JottTree{


    ArrayList<BodyStatementNodeInterface> bodyStatements;
    ReturnStmtNode returnStmt;
    boolean oop;
    private static Object returnValue;



    public BodyNode(ArrayList<BodyStatementNodeInterface> bodyStatementNodes, ReturnStmtNode returnStmtNode)
    {
        this.bodyStatements = bodyStatementNodes;
        this.returnStmt = returnStmtNode;
        oop = true;
    }

    public BodyNode(ArrayList<BodyStatementNodeInterface> bodyStatementNodes)
    {
        this.bodyStatements = bodyStatementNodes;
        oop = false;
    }

    public boolean hasReturn() {
        return oop;
    }

    public static BodyNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw new Exception ("Syntax Error: \n Expected a function body but file ended.");
        }
        ArrayList<BodyStatementNodeInterface> bodyStatements = new ArrayList<BodyStatementNodeInterface>();
        Token curToken = tokens.peek();
        while (curToken.getTokenType() != TokenType.R_BRACE && !(curToken.getToken().equals("Return")))
        {
            BodyStatementNodeInterface BSN = BodyStatementNodeInterface.parse(tokens);
            bodyStatements.add(BSN);
            curToken = tokens.peek();
        }
        if (curToken.getTokenType() == TokenType.R_BRACE)
        {
            return new BodyNode(bodyStatements);
        }
        else
        {
            ReturnStmtNode retrn = ReturnStmtNode.parse(tokens);
            return new BodyNode(bodyStatements, retrn);
        }


    }

    @Override
    public String convertToJott() {
        String body = "";
        for (BodyStatementNodeInterface BSN: bodyStatements)
        {
            body += BSN.convertToJott();
            // if it's a funccallnode, we need to add ; at the end.
            if (BSN.convertToJott().startsWith("::")) {
                body += ";"; 
            }
        }
        if (returnStmt != null)
        {
            body += returnStmt.convertToJott();
        }
        return body;

    }

    @Override
    public boolean validateTree() {
        for (BodyStatementNodeInterface i: bodyStatements) {
            if (i.validateTree() == false) {
                // does not need error output.
                return false;
            }
            if (i.isIf()) {
                if (i.ifReturn()) {
                    oop = true;
                }
            }
        }
        if (returnStmt == null)
        {
            if (SymbolTable.getFuncReturnType(SymbolTable.scope) == null)
            {
                return true;
            }
        }
        else
        {
            if (returnStmt.validateTree() == false) {
                // does not need error output.
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute() {
        for (BodyStatementNodeInterface b: bodyStatements) {
            b.execute();
        }
        if (returnStmt != null) {
            returnStmt.execute();
            returnValue = returnStmt.getReturnStmtVal();
        }
    }

    public Object getValue()
    {
        return returnValue;
    }
    
}
