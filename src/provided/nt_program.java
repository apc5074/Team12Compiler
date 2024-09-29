package provided;

import java.util.ArrayList;

public class nt_program {

    // the string associated with this functions calling
    private ArrayList<nt_functions> callSigns;

    public nt_program(ArrayList<Token> tokens) {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD || !tokens.get(0).getToken().equals("Def")) {
            System.err.println("Error! Function doesn't start with DEF!");
            return;
        }
        
    }
    public ArrayList<nt_functions> getFuncIDs() {
        return callSigns;
    }
}
