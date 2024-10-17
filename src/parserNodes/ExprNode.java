package parserNodes;
import provided.*;
import java.util.Stack;

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
        if (toke.getTokenType() == TokenType.ID_KEYWORD && (toke.getToken() == "True" || toke.getToken() == "False")) {
            tokens.pop();
            return new ExprNode(toke);
        }
        try {
            OperandNode l = OperandNode.parse(tokens);
            OpNode opNode = OpNode.parse(tokens);
            OperandNode r = OperandNode.parse(tokens);
            return new ExprNode(l, opNode, r);
        } catch (Exception e) {
            throw new Exception("Invalid expression detected.");
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
        return left.convertToJott() + op.convertToJott() + right.convertToJott();
    }
    
}
