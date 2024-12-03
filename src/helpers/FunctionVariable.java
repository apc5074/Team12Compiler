package helpers;

public class FunctionVariable {
    private int type;
    private String stringVal;
    private Integer intVal;
    private Float floatVal;
    private Boolean boolVal;

    public FunctionVariable(int i) {
        type = 1;
        intVal = i;
    }
    public FunctionVariable(float i) {
        type = 2;
        floatVal = i;
    }
    public FunctionVariable(boolean i) {
        type = 3;
        boolVal = i;
    }
    public FunctionVariable(String i) {
        type = 4;
        stringVal = i;
    }
    

    // 1 = int, 2 = float, 3 = bool, 4 = string.
    public int getType() {
        return type;
    }

    public void update(int i) {
        if (type != 1) {
            System.err.println("Invalid type - int unexpected.");
        }
        intVal = i;
    }
    public int getInt() {
        if (type != 1) {
            System.err.println("Int was requested, variable is not int.");
            System.exit(0);
        }
        return intVal;
    }
    public void update (String i) {
        if (type != 4) {
            System.err.println("Invalid type - String unexpected.");
        }
        stringVal = i;
    }
    public String getString() {
        if (type != 4) {
            System.err.println("String was requested, variable is not String.");
            System.exit(0);
        }
        return stringVal;
    }
    public void update(float i) {
        if (type != 2) {
            System.err.println("Invalid type - float unexpected.");
        }
        floatVal = i;
    }
    public float getFloat() {
        if (type != 2) {
            System.err.println("Flaot was requested, variable is not float.");
            System.exit(0);
        }
        return floatVal;
    }
    public void update (boolean i) {
        if (type != 3) {
            System.err.println("Invalid type - boolean unexpected.");
        }
        boolVal = i;
    }
    public boolean getBoolean() {
        if (type != 3) {
            System.err.println("Boolean was requested, variable is not boolean.");
            System.exit(0);
        }
        return boolVal;
    }
}
