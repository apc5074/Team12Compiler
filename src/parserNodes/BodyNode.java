package parserNodes;
import java.util.Stack;
import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class BodyNode implements JottTree{

    private static final BodyNode Exception = null;

    ArrayList<BodyStatementNodeInterface> bodyStatements;
    ReturnStmtNode returnStmt;



    public BodyNode(ArrayList<BodyStatementNodeInterface> bodyStatementNodes, ReturnStmtNode returnStmtNode)
    {
        this.bodyStatements = bodyStatementNodes;
        this.returnStmt = returnStmtNode;
    }

    public BodyNode(ArrayList<BodyStatementNodeInterface> bodyStatementNodes)
    {
        this.bodyStatements = bodyStatementNodes;
    }

    public static BodyNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            return Exception;
        }
        ArrayList<BodyStatementNodeInterface> bodyStatements = new ArrayList<BodyStatementNodeInterface>();
        Token curToken = tokens.peek();
        while (curToken.getTokenType() != TokenType.R_BRACE && curToken.getToken() != "Return")
        {
            BodyStatementNodeInterface BSN = BodyStatementNodeInterface.parse(tokens);
            bodyStatements.add(BSN);
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
        }
        if (returnStmt != null)
        {
            body += returnStmt.convertToJott();
        }
        return body;

    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
