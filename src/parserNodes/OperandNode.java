package parserNodes;
import provided.*;
import java.util.Stack;

public class OperandNode implements ExprNodeInterface {
    private static final Exception Exception = null;

    private Token opToken;

    public OperandNode(Token opToken)
    {
        this.opToken = opToken;
    }

    public static OperandNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw Exception;
        }
        Token iToken = tokens.peek();

        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            throw Exception;
        }
        if(!(iToken.getToken().equals("NUMBER") || iToken.getToken().equals("ID_KEYWORD") ||
        iToken.getToken().equals("-") || iToken.getToken().equals("FC_HEADER")))
        {
            throw Exception;
        }

        return new OperandNode(iToken);
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJott'");
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
