package provided;

import java.util.ArrayList;

public class nt_program implements JottTree {

    // the string associated with this functions calling
    private ArrayList<nt_functions> callSigns;

    public nt_program(ArrayList<Token> tokens) {
        while (!tokens.isEmpty()) {
            nt_functions func = new nt_functions();
            tokens = func.grantTokens(tokens);
            if (tokens == null) {
                return;
            }
            callSigns.add(func);
        }
    }
    public ArrayList<nt_functions> getFuncIDs() {
        return callSigns;
    }

    public boolean validateTree() {
        return true;
    }

    public String convertToJott() {
        return "Yeah this function was called";
    }

    public void execute() {
        return;
    }
}
