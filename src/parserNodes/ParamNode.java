package parserNodes;
import provided.*;
import java.util.Stack;

public class ParamNode implements JottTree {
    private ExprNode exprNode;
    
    public ParamNode(ExprNode expression) {
        exprNode = expression;
    }

    public ParamNode() {
        exprNode = null;
    }

    public static ParamNode parseParameterNode(Stack<Token> tokens) {
        if (tokens.peek().getTokenType() == TokenType.R_BRACKET) {
            return new ParamNode();
        }
        ExprNode toke = ExprNode.parseExprNode(tokens);
        if (toke == null) {
            return null;
        }
        return new ParamNode(toke);
    }

    @Override
    public String convertToJott() {
        return (exprNode.convertToJott());
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
