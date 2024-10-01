package parserNodes;
import provided.*;

public class BoolNode implements JottTree {
    private boolean value;

    public BoolNode(boolean value) {
        this.value = value;
    }

    @Override
    public String convertToJott() {
        return value ? "True" : "False";
    }

    @Override
    public boolean validateTree() {
        return true;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    public boolean getValue() {
        return value;
    }
}
