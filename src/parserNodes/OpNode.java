package parserNodes;
import provided.*;
import java.util.Stack;

public class OpNode implements JottTree {
    private static final Exception Exception = null;
    private Token opToken;

    public OpNode(Token opToken) {
        this.opToken = opToken;
    }

    public static OpNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw new Exception("Syntax error:\nExpected ProgramNode but no tokens left");
        }
        Token iToken = tokens.peek();

        if (iToken.getTokenType() != TokenType.MATH_OP && iToken.getTokenType() != TokenType.REL_OP) {
            throw new Exception("Syntax error:\nExpected math or relational op but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();

        return new OpNode(iToken);
    }

    @Override
    public String convertToJott() {
        return opToken.getToken();  // Just return the operator as part of the equation
    }

    @Override
    public boolean validateTree() {
        // TODO No special validation for just the operator, but can ensure it's valid
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
