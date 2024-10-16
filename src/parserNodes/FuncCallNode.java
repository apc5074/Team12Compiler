package parserNodes;
import java.util.Stack;
import provided.*;

public class FuncCallNode implements BodyStatementNodeInterface {

    private static final Exception Exception = null;

    private IdNode id;
    private ParamNode args;

    public FuncCallNode(IdNode idNode, ParamNode params){
        id = idNode;
        args = params;
    }

    public static FuncCallNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            System.err.println("Expected function call, got EOF.");
            throw Exception;
        }
        Token toke = tokens.peek();
        if (toke.getTokenType() != TokenType.FC_HEADER) {
            System.err.println("Expected colon, got " + toke.getTokenType());
            throw Exception;
        }
        tokens.pop();

        IdNode id = IdNode.parse(tokens);

        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.L_BRACKET) {
            System.err.println("Expected left bracket, got " + toke.getTokenType());
            throw Exception;
        }
        tokens.pop();

        ParamNode args = ParamNode.parseParamNode(tokens);

        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.R_BRACKET) {
            System.err.println("Expected right bracket, got " + toke.getTokenType());
            throw Exception;
        }
        tokens.pop();

        return new FuncCallNode(id, args);
    }

    @Override
    public String convertToJott() {
        return ("::" + id.convertToJott() +"[" + args.convertToJott() + "]");
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
