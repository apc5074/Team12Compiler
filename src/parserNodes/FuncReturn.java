package parserNodes;
import provided.*;
import java.util.Stack;

public class FuncReturn implements JottTree {

    TypeNode type;
    boolean voided;

    public FuncReturn(Stack<Token> tokens) {
        
    }

    @Override
    public String convertToJott() {
        if (voided) {
            return ("Void");
        } else {
            return type.convertToJott();
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
}
