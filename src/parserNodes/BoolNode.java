package parserNodes;
import provided.*;
import java.util.Stack;

public class BoolNode implements JottTree {
    private static final Exception Exception = null;
    
    private Token boolToken;

    public BoolNode(Token boolToken) {
        this.boolToken = boolToken;
    }

    private static BoolNode parse (Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw Exception;
        }
        Token iToken = tokens.peek();
        if (iToken.getTokenType() != TokenType.ID_KEYWORD || iToken.getToken().equals("true") || iToken.getToken().equals("false"))
        {
            throw Exception;
        }

        return new BoolNode(iToken);
    }

    @Override
    public String convertToJott() {
        return boolToken.getToken();
    }

    @Override
    public boolean validateTree() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
