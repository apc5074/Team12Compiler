package parserNodes;
import provided.*;
import java.util.Stack;

public class ParamNodeT implements JottTree {
    private ExprNode exprNode;
    
    public ParamNodeT(ExprNode expression) {
        exprNode = expression;
    }

    public static ParamNodeT parseParamNodeT(Stack<Token> tokens) {
        if (tokens.size() == 0) {
            return null;
        }
        if (tokens.peek().getTokenType() == TokenType.R_BRACKET) {
            return null;
        }

        if (tokens.peek().getTokenType() != TokenType.COMMA) {
            return null;
        }

        // removes the comma from the list.
        tokens.pop();
        ExprNode toke = ExprNode.parseExprNode(tokens);
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
