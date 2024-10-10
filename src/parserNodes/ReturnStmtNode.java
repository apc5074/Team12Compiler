package parserNodes;

import java.util.Stack;

import provided.JottTree;
import provided.Token;

public class ReturnStmtNode implements JottTree{

    private static final Exception Exception = null;

    private ExprNodeInterface expressionNode;

    public ReturnStmtNode(ExprNodeInterface exrpessionNode)
    {
        this.expressionNode = exrpessionNode;
    }

    public static ReturnStmtNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty())
        {
            throw Exception;
        }
        Token curToken = tokens.get(0);
        if (curToken.getToken() == "Return")
        {
            tokens.pop();
        }
        else
        {
            return null;
        }
        ExprNodeInterface expression = ExprNodeInterface.parse(tokens);
        if (tokens.peek().getToken() == ";")
        {
            tokens.pop();
        }
        else
        {
            throw Exception;
        }
        return new ReturnStmtNode(expression);

    }


    @Override
    public String convertToJott() {
        return "Return " + this.expressionNode.convertToJott() + ";";
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
