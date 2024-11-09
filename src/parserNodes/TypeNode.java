package parserNodes;
import java.util.Stack;
import provided.*;

public class TypeNode implements JottTree{

    private Token typeToken;

    public TypeNode(Token type) {
        this.typeToken = type;
    }

    public static TypeNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            throw new Exception("Syntax Error:\n" + 
                                "Expected token 'Def'\n" +
                                tokens.peek().getLineNum());  
        }

        Token currentToken = tokens.peek(); //dont pop yet
        
        if (!isValidType(currentToken)) {
            throw new Exception("Syntax Error:\n" + 
                                "Invalid type '" + currentToken.getToken() + "' at line " 
                                 + currentToken.getLineNum() + " in " + currentToken.getFilename());
        }
        if (!isValidText(currentToken)) {
            throw new Exception("Syntax Error:\n" +  "Invalid text '" + currentToken.getToken() + "' at line " 
                                 + currentToken.getLineNum() + " in " + currentToken.getFilename());
        }

        TypeNode returnNode = new TypeNode(currentToken);
        tokens.pop();
        return returnNode;
    }

    @Override
    public String convertToJott() {
        return typeToken.getToken();  
    }

    private static boolean isValidType(Token type) {
        return (type.getTokenType() == TokenType.ID_KEYWORD);
    }

    private static boolean isValidText(Token type) {
        // Void is not a type; it's its own special unique thing
        return type.getToken().equals("Integer") || type.getToken().equals("Double") 
            || type.getToken().equals("String")  || type.getToken().equals("Boolean");
    }

    
    @Override
    public boolean validateTree() {
        return true;
    }

    @Override
    public void execute() {
        // Not Implemented
    }

    public String getTypeName() {
        return typeToken.getToken();
    }

    @Override
    public String toString() {
        return typeToken.getToken();
    }


}
