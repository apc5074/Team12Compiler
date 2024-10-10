package parserNodes;
import provided.*;
import java.util.Stack;

public class ParamNodeT implements JottTree {
    private final static Exception e = null;
    private ExprNode exprNode;
    
    public ParamNodeT(ExprNode expression) {
        exprNode = expression;
    }

    public static ParamNodeT parse(Stack<Token> tokens) {
        if (tokens.size() == 0) {
            throw e;
        }
        if (tokens.peek().getTokenType() == TokenType.R_BRACKET) {
            return e;
        }

        if (tokens.peek().getTokenType() != TokenType.COMMA) {
            return e;
        }

        // removes the comma from the list.
        tokens.pop();
        ExprNode toke = ExprNode.parse(tokens);
        if (toke == null) {
            return null;
        }
        
        return new ParamNodeT(toke);
    }

    @Override
    public String convertToJott() {
        return ("," + exprNode.convertToJott());
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
