package parserNodes;
import java.util.Stack;
import provided.*;

public class FunctionCallNode implements JottTree{


    private Token callNode;
    private ParamNode args;

    public FunctionCallNode(Token toke, ParamNode params) {
        callNode = toke;
        args = params;
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
        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.L_BRACKET) {
            System.err.println("Expected left bracket, got " + toke.getTokenType());
        }

        ParamNode args = ParamNode.parseParamNode(tokens);

        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.R_BRACKET) {
            System.err.println("Expected right bracket, got " + toke.getTokenType());
        }

        return new FunctionCallNode(toke, args);
    }

    @Override
    public String convertToJott() {
        return ("::" + callNode.getToken() +"[" + args + "]");
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
