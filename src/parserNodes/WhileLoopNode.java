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
            throw new Exception("Syntax error:\nExpected WhileLoopNode but no tokens left");
        }
        Token well = tokens.peek();
        if (!well.getToken().equals("While") || well.getTokenType() != TokenType.ID_KEYWORD) {
            throw new Exception("Syntax error:\nExpected id While but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.L_BRACKET) {
            throw new Exception("Syntax error:\nExpected Left Bracket but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        ExprNode exp = ExprNode.parse(tokens);
        if (tokens.peek().getTokenType() != TokenType.R_BRACKET) {
            throw new Exception("Syntax error:\nExpected Right Bracket but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.L_BRACE) {
            throw new Exception("Syntax error:\nExpected Left Brace but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        BodyNode bod = BodyNode.parse(tokens);
        if (tokens.peek().getTokenType() != TokenType.R_BRACE) {
            throw new Exception("Syntax error:\nExpected Right Brace but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();

        return new WhileLoopNode(exp, bod);
    }

    @Override
    public String convertToJott() {
        return ("While [ " + expression.convertToJott() + " ] { " + body.convertToJott() + " } ");
    }

    @Override
    public boolean isIf() {
        return false;
    }
    @Override
    public boolean ifReturn() {
        return false;
    }

    @Override
    public boolean validateTree() {
        return expression.validateTree() && body.validateTree();
    }

    @Override
    public void execute() {
        expression.execute();
        while ((boolean)expression.getValue())
        {
            body.execute();
            expression.execute();
        }
    }

}
