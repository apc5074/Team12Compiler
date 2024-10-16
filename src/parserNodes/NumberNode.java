package parserNodes;
import provided.*;
import java.util.Stack;

public class NumberNode implements JottTree {

    private Token token;

    public NumberNode (Token number) {
        this.token = number;
    }

    public static NumberNode parse(Stack<Token> tokens) {
        if (tokens.size() == 0) {
            System.err.println("Expected number token, got end of file.");
            return null;
        }
        Token toke = tokens.pop();
        if (tokens.get(0).getTokenType() != TokenType.NUMBER) {
            System.err.println("Expected number token at line " + toke.getLineNum() + ", got " + toke.getTokenType());
            return null;
        }

        return new NumberNode(toke);
    }

    @Override
    public String convertToJott() {
        return (token.getToken());
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
