package parserNodes;

import java.util.Stack;

import helpers.SymbolTable;
import provided.JottTree;
import provided.Token;

public class ReturnStmtNode implements JottTree{

    private ExprNodeInterface expressionNode;

    public ReturnStmtNode(ExprNodeInterface exrpessionNode)
    {
        this.expressionNode = exrpessionNode;
    }

    public static ReturnStmtNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty())
        {
            throw new Exception("Syntax error:\nExpected ReturnStmtNode but no tokens left");
        }
        Token curToken = tokens.peek();
        if (curToken.getToken().equals("Return"))
        {
            tokens.pop();
        }
        else
        {
            throw new Exception("Syntax error:\nExpected Return but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        ExprNodeInterface expression = ExprNodeInterface.parse(tokens);
        if (tokens.peek().getToken().equals(";"))
        {
            tokens.pop();
        }
        else
        {
            throw new Exception("Syntax error:\nMissing semi colon \n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        return new ReturnStmtNode(expression);

    }


    @Override
    public String convertToJott() {
        return "Return " + this.expressionNode.convertToJott() + " ;";
    }

    @Override
    public boolean validateTree() {
        if(expressionNode.validateTree())
        {

            if(SymbolTable.getFuncReturnType(SymbolTable.scope) != null)
            {
                return SymbolTable.getFuncReturnType(SymbolTable.scope).getTypeName().equals(expressionNode.getExprType());
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    

}
