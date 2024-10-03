package parserNodes;

import java.util.Stack;

import provided.JottTree;
import provided.Token;

public class ReturnStmtNode implements JottTree{

    private static final Exception Exception = null;

    private Token returnToken;
    private ExprNode expressionNode;

    public ReturnStmtNode(Token rToken, ExprNode expr)
    {
        this.returnToken = rToken;
        this.expressionNode = expr;
    }

    public static ReturnStmtNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty())
        {
            throw Exception;
        }
        Token curToken = tokens.get(0);
        if (curToken.getToken() == "return")
        {
            tokens.pop();
            ExprNode expr = ExprNode.parse(tokens);
            return new ReturnStmtNode(curToken, expr);
        }

    }


    @Override
    public String convertToJott() {
        return "return " + this.expressionNode.convertToJott();
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
