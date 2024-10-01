package parserNodes;
import provided.*;

public class OpNode implements JottTree {
    private String operator;

    public OpNode(String operator) {
        this.operator = operator;
    }

    @Override
    public String convertToJott() {
        return operator;  // Just return the operator as part of the equation
    }

    @Override
    public boolean validateTree() {
        // No special validation for just the operator, but can ensure it's valid
        return operator.equals("+") || operator.equals("-") || operator.equals("*") ||
               operator.equals("/") || operator.equals(">") || operator.equals("<") || 
               operator.equals(">=") || operator.equals("<=") || operator.equals("==") ||
               operator.equals("!=");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    public String getOperator() {
        return operator;
    }

}
