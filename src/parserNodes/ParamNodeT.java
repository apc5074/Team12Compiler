package parserNodes;
import provided.*;
import java.util.Stack;

public class ParamNodeT implements JottTree {
    private ExprNode exprNode;
    
    public ParamNodeT(ExprNode expression) {
        exprNode = expression;
    }

    public static ParamNodeT parse(Stack<Token> tokens) throws Exception{
        if (tokens.size() == 0) {
            throw new Exception("Syntax error:\nExpected ProgramNode but no tokens left");
        }
        if (tokens.peek().getTokenType() == TokenType.R_BRACKET) {
            throw new Exception("Syntax error:\nExpected Right Bracket but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }

        if (tokens.peek().getTokenType() != TokenType.COMMA) {
            throw new Exception("Syntax error:\nExpected comma but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
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
