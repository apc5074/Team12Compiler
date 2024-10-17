package parserNodes;
import java.util.Stack;
import provided.*;

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
            throw new Exception("The stack is empty.");
        }
        Token iToken = tokens.peek();

        /*if (iToken.getTokenType() == TokenType.ID_KEYWORD) {
            throw new Exception("Invalid Token Type (Expected ID_KEYWORD), at line " + iToken.getLineNum() + iToken.getToken());
        }*/
        if(!(iToken.getTokenType() == TokenType.NUMBER|| iToken.getTokenType() == TokenType.ID_KEYWORD ||
        iToken.getTokenType() == TokenType.FC_HEADER || iToken.getTokenType() == TokenType.ID_KEYWORD))
        {
            throw new Exception("Invalid Token Type (Expected FC_HEADER, NUMBER, ID_KEYWORD), got "+iToken.getTokenType());
        }
        tokens.pop();
        return new OperandNode(iToken);
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return opToken.getToken();
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
