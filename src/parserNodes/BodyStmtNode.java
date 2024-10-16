package parserNodes;
import provided.*;
import java.util.Stack;

public class BodyStmtNode implements BodyStatementNodeInterface {
    private static final Exception Exception = null;

    private BodyStatementNodeInterface bn;

    public BodyStmtNode(BodyStatementNodeInterface bn)
    {
        bn = this.bn;
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
