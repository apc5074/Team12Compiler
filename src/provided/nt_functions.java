package provided;

import java.util.ArrayList;

public class nt_functions implements JottTree {
    public nt_functions() {
        
    }

    public ArrayList<Token> grantTokens(ArrayList<Token> tokens) {
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD || !tokens.get(0).getToken().equals("Def")) {
            System.err.println("Error! Function doesn't start with DEF!");
            return null;
        }
        /* do stuff */
        /* trim out all the tokens that were used */
        /* return the updated ArrayList */
        return null;
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
