package parserNodes;
import provided.*;
import java.util.Stack;

import helpers.SyntaxException;

public class NumberNode implements JottTree {

    private Token token;

    public NumberNode (Token number) {
        this.token = number;
    }

    public static NumberNode parse(Stack<Token> tokens) throws Exception{
        if (tokens.size() == 0) {
            SyntaxException e = new SyntaxException("Expected number token, got end of file.");
            System.out.println(e.toString());
            return null;
        }
        Token toke = tokens.pop();
        if (tokens.get(0).getTokenType() != TokenType.NUMBER) {
            SyntaxException e = new SyntaxException(toke.getLineNum(), toke.getFilename(), "Expected number token, got " +toke.getTokenType() + ".");
            System.out.println(e.toString());
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
        return true;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
