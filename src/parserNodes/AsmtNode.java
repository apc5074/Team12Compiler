package parserNodes;
import provided.*;

public class AsmtNode implements JottTree {
    private String type;        
    private String id;         
    private JottTree expr;      

    public AsmtNode(String type, String id, JottTree expr) {
        this.type = type;       
        this.id = id;         
        this.expr = expr;       
    }

    public AsmtNode(String id, JottTree expr) {
        this(null, id, expr);   // No type, just the id and the expression
    }

    @Override
    public String convertToJott() {
        if (type != null) {
            return type + " " + id + " = " + expr.convertToJott() + ";";
        } else {
            return id + " = " + expr.convertToJott() + ";";
        }
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public JottTree getExpr() {
        return expr;
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

