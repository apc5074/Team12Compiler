package parserNodes;
import provided.*;
import java.util.Stack;

public class FunctionCallNode implements JottTree{


    private Token callNode;

    public FunctionCallNode(Token toke) {
        callNode = toke;
    }

    public static FunctionCallNode parseFunctionCallNode(Stack<Token> tokens) {
        if (tokens.size() < 3) {
            System.err.println("Expected function call, got EOF.");
            return null;
        }
        Token toke = tokens.pop();
        if (toke.getTokenType() != TokenType.COLON) {
            System.err.println("Expected colon, got " + toke.getTokenType());
            return null;
        }
        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.COLON) {
            System.err.println("Expected colon, got " + toke.getTokenType());
            return null;
        }
        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.ID_KEYWORD) {
            System.err.println("Expected func name, got " + toke.getTokenType());
        }

        return new FunctionCallNode(toke);
    }

    @Override
    public String convertToJott() {
        return ("::" + callNode.getToken());
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
