package parserNodes;
import java.util.Stack;
import provided.*;

public class WhileLoopNode implements BodyStatementNodeInterface {
    private ExprNode expression;
    private BodyNode body;

    public WhileLoopNode(ExprNode exp, BodyNode body) {
        expression = exp;
        this.body = body;
    }

    public static WhileLoopNode parse(Stack<Token> tokens) throws Exception{
        // i still dont know how to check the whileloopnode
        if (tokens.size() == 0) {
            throw new Exception("ERROR 0");
        }
        Token well = tokens.peek();
        if (!well.getToken().equals("While") || well.getTokenType() != TokenType.ID_KEYWORD) {
            throw new Exception("ERROR 1");
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.L_BRACKET) {
            throw new Exception("ERROR 2");
        }
        tokens.pop();
        ExprNode exp = ExprNode.parse(tokens);
        if (tokens.peek().getTokenType() != TokenType.R_BRACKET) {
            throw new Exception("ERROR 3");
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.L_BRACE) {
            throw new Exception("ERROR 4");
        }
        tokens.pop();
        BodyNode bod = BodyNode.parse(tokens);
        if (tokens.peek().getTokenType() != TokenType.R_BRACE) {
            throw new Exception("ERROR 5");
        }
        tokens.pop();

        return new WhileLoopNode(exp, bod);
    }

    @Override
    public String convertToJott() {
        return ("While [ " + expression.convertToJott() + " ] { " + body.convertToJott() + " } ");
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
