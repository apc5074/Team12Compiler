package parserNodes;
import provided.*;

public class ExprNode implements JottTree {
    private JottTree left;      
    private String operator;    
    private JottTree right;     

    // Constructor for simple expressions (e.g., <id>, <num>, <bool>, <string_literal>)
    public ExprNode(JottTree simpleExpression) {
        this.left = simpleExpression;
        this.operator = null;
        this.right = null;
    }

    // Constructor for binary expressions (e.g., <id> <op> <expr>, <num> <op> <expr>)
    public ExprNode(JottTree left, String operator, JottTree right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String convertToJott() {
        if (operator == null) {
            return left.convertToJott();
        } else {
            return left.convertToJott() + " " + operator + " " + right.convertToJott();
        }
    }

    public JottTree getLeft() {
        return left;
    }

    public String getOperator() {
        return operator;
    }

    public JottTree getRight() {
        return right;
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

