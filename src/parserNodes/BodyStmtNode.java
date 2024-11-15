package parserNodes;

public class BodyStmtNode implements BodyStatementNodeInterface {
    private BodyStatementNodeInterface bn;

    public BodyStmtNode(BodyStatementNodeInterface bn)
    {
        bn = this.bn;
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
        return bn.convertToJott();
    }

}
