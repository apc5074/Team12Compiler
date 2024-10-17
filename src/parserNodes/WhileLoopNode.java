package parserNodes;
import provided.*;
import java.util.Stack;

public class WhileLoopNode implements BodyStatementNodeInterface {
    private ExprNode expression;
    private BodyNode body;

    public WhileLoopNode(ExprNode exp, BodyNode body) {
        expression = exp;
        this.body = body;
    }

    public static WhileLoopNode parse(Stack<Token> tokens) {
        // i still dont know how to check the whileloopnode
        if (tokens.size() == 0) {
            return null;
        }
        Token well = tokens.peek();
        if (well.getToken() != "while" || well.getTokenType() != TokenType.ID_KEYWORD) {
            return null;
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.L_BRACKET) {
            return null;
        }
        tokens.pop();
        ExprNode exp = ExprNode.parse(tokens);
        if (tokens.peek().getTokenType() != TokenType.R_BRACKET) {
            return null;
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.L_BRACE) {
            return null;
        }
        tokens.pop();
        BodyNode bod = BodyNode.parse(tokens);
        if (tokens.peek().getTokenType() != TokenType.R_BRACE) {
            return null;
        }
        tokens.pop();
    }

    @Override
    public String convertToJott() {
        return ("while[" + expression.convertToJott() + "]{" + body.convertToJott() + "}");
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
