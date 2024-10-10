package parserNodes;
import provided.*;
import java.util.Stack;

public class OperandNode implements JottTree {
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

        if(!(iToken.getTokenType().equals("NUMBER") || iToken.getTokenType().equals("ID_KEYWORD") ||
        iToken.getToken().equals("-") || iToken.getTokenType().equals("FC_HEADER")))
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
