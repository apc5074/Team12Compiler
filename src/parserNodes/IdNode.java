package parserNodes;

import java.util.Stack;

import provided.JottTree;
import provided.Token;

public class IdNode implements JottTree{

    private static final Exception Exception = null;

    private Token idToken;

    public IdNode(Token idToken)
    {
        this.idToken = idToken;
    }

    public static IdNode parse(Stack<Token> tokens) throws Exception {    
        if (tokens.empty())
        {
            throw Exception;
        }    
        Token idToken = tokens.pop();
        return new IdNode(idToken);
    }

    @Override
    public String convertToJott() {
        return this.idToken.getToken();
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
