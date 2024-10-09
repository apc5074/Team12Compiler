package parserNodes;

import java.util.ArrayList;
import java.util.Stack;
import provided.JottTree;
import provided.Token;

public class FuncDefParams implements JottTree{

    private TypeNode type;
    private IdNode identifier;

    // Constructor to initialize a FuncDefParam with type and identifier
    public FuncDefParams(TypeNode type, IdNode identifier) {
        this.type = type;
        this.identifier = identifier;
    }

     public static ArrayList<FuncDefParams> parse(Stack<Token> tokens) throws Exception {
        ArrayList<FuncDefParams> params = new ArrayList<>();

        // Continue parsing parameters until we hit the closing bracket ']'
        while (!tokens.get(0).getToken().equals("]")) {
            TypeNode type = TypeNode.parse(tokens);  // Parse the type of the parameter
            IdNode identifier = IdNode.parse(tokens);  // Parse the identifier of the parameter

            params.add(new FuncDefParams(type, identifier));

            // If there's a comma, continue parsing more parameters
            if (tokens.get(0).getToken().equals(",")) {
                tokens.pop();  // Pop the comma
            } else if (!tokens.get(0).getToken().equals("]")) {
                throw new Exception("Expected ',' or ']' after function parameter");
            }
        }

        return params;
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return type.convertToJott() + " " + identifier.convertToJott();
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
