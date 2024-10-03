package parserNodes;
import java.util.Stack;
import provided.*;

public class VarDec implements JottTree {
    private static Token varToken;
    private static Token IDToken;


    public VarDec(Token typeToken, Token IDToken) {
        this.varToken = varToken;
        this.IDToken = IDToken;
    }

    public static VarDec parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            throw new Exception();
        }

        Token typeToken = tokens.firstElement(); //dont pop yet
        Token IDtoken = tokens.elementAt(1); // Access the second element (index 1)
        if (!isValidType(typeToken) && !isValidText(typeToken)) {
            throw new Exception("Syntax Error: Invalid type '" + typeToken.getToken() + "' at line " 
                                 + typeToken.getLineNum() + " in " + typeToken.getFilename());
        }
        if (!isValidID(IDtoken)) {
            throw new Exception("Syntax Error: Invalid text '" + IDtoken.getToken() + "' at line " 
                                 + IDtoken.getLineNum() + " in " + IDtoken.getFilename());
        }

        VarDec returnNode = new VarDec(typeToken, IDtoken);

        tokens.pop();
        tokens.pop();
        return returnNode;
    }

    @Override
    public String convertToJott() {
        return varToken.getToken();  
    }

    private static boolean isValidType(Token type) {
        return (type.getTokenType() == TokenType.ID_KEYWORD);
    }

    private static boolean isValidID(Token varDec) {
        String id = varDec.getToken();
        return id.equals(id.toLowerCase());
    }

    private static boolean isValidText(Token type) {
        return type.getToken().equals("Int") || type.getToken().equals("Double") 
            || type.getToken().equals("String") || type.getToken().equals("Boolean");
    }

    @Override
    public boolean validateTree() {
        return true;
    }

    @Override
    public void execute() {
        // Not Implemented
    }

    @Override
    public String toString() {
        return varToken.getToken();
    }


}
