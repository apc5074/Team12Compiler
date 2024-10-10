package parserNodes;
import provided.*;
import java.util.Stack;

public class FuncReturn implements JottTree {

    Token toke;
    boolean voided;

    public FuncReturn() {
        voided = true;
    }

    public FuncReturn(Token t) {
        toke = t;
    }

    public FuncReturn parse(Stack<Token> tokens) {
        Token next = tokens.peek();
        if (tokens.size() == 0 && next.getTokenType() == TokenType.ID_KEYWORD) {
            String t = next.getToken();
            if (t == "Double" || t == "Integer" || t == "String" || t == "Boolean") {
                return new FuncReturn(tokens.pop());
            } else {
                return null;
            }
        } else {
            if (tokens.size() == 0 && tokens.peek().getTokenType() != TokenType.L_BRACE) {
                return new FuncReturn();
            } else {
                return null;
            }
        }
    }

    @Override
    public String convertToJott() {
        if (voided) {
            return ("Void");
        } else {
            return type.convertToJott();
        }
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
