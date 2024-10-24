package parserNodes;
import java.util.Stack;
import provided.*;

public class ExprNode implements ExprNodeInterface{
    private OperandNode left;;
    private OpNode op;
    private OperandNode right;
    private Token toke;

    public ExprNode(OperandNode left, OpNode op, OperandNode right)
    {
        this.left = left;
        this.op = op;
        this.right = right;
        toke = null;
    }

    public ExprNode (Token toke) {
        this.toke = toke;
    }

    public static ExprNode parse(Stack<Token> tokens) throws Exception {
        Token toke = tokens.peek();
        if (toke.getTokenType() == TokenType.STRING) {
            tokens.pop();
            return new ExprNode(toke);
        }
        if (toke.getTokenType() == TokenType.ID_KEYWORD && (toke.getToken().equals("True") || toke.getToken().equals("False"))) {
            tokens.pop();
            return new ExprNode(toke);
        }
        try {
            OperandNode l = OperandNode.parse(tokens);
            OpNode opNode = OpNode.parse(tokens);
            OperandNode r = OperandNode.parse(tokens);
            return new ExprNode(l, opNode, r);
        } catch (Exception e) {
            throw new Exception("Syntax error:\nExpected correct Expression \n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
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

    @Override
    public String convertToJott() {
        if (toke != null) {
            return toke.getToken();
        }
        return left.convertToJott() + " " + op.convertToJott() + " " + right.convertToJott();
    }
    
}
