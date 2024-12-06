package helpers;
import parserNodes.TypeNode;

public class FunctionVariable {
    private int type;
    private String stringVal;
    private Integer intVal;
    private Double floatVal;
    private Boolean boolVal;

    // 1 = int, 2 = double, 3 = bool, 4 = string.
    public FunctionVariable(TypeNode alp) {
        if (alp.getTypeName().equals("Integer")) {
            type = 1;
            return;
        }
        if (alp.getTypeName().equals("Double")) {
            type = 2;
            return;
        }
        if (alp.getTypeName().equals("Boolean")) {
            type = 3;
            return;
        }
        if (alp.getTypeName().equals("String")) {
            type = 4;
            return;
        }

    }
    

    // 1 = int, 2 = double, 3 = bool, 4 = string.
    public int getType() {
        return type;
    }

    public boolean update(int i) {
        if (type != 1) {
            return false;
        }
        intVal = i;
        return true;
    }
    public int getInt() {
        if (type != 1) {
            System.err.println("Int was requested, variable is not int.");
            System.exit(0);
        }
        return intVal;
    }
    public boolean update (String i) {
        if (type != 4) {
            return false;
        }
        stringVal = i;
        return true;
    }
    public String getString() {
        if (type != 4) {
            System.err.println("String was requested, variable is not String.");
            System.exit(0);
        }
        return stringVal;
    }
    public boolean update(double i) {
        if (type != 2) {
            return false;
        }
        floatVal = i;
        return true;
    }
    public double getDouble() {
        if (type != 2) {
            System.err.println("Double was requested, variable is not double.");
            System.exit(0);
        }
        return floatVal;
    }
    public boolean update (boolean i) {
        if (type != 3) {
            return false;
        }
        boolVal = i;
        return true;
    }
    public boolean getBoolean() {
        if (type != 3) {
            System.err.println("Boolean was requested, variable is not boolean.");
            System.exit(0);
        }
        return boolVal;
    }

    public Object getValue()
    {
        if(type == 1){
            return intVal;
        }
        if(type == 2){
            return floatVal;
        }
        if(type == 3){
            return boolVal;
        }
        if(type == 4){
            return stringVal;
        }
        else return "Something is Wrong";
    }
}
