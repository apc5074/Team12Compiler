package parserNodes;

public class ExprNode implements ExprNodeInterface{
    private OperandNode left;;
    private OpNode op;
    private OperandNode right;

    public ExprNode(OperandNode left, OpNode op, OperandNode right)
    {
        this.left = left;
        this.op = op;
        this.right = right;
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
