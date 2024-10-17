package parserNodes;

import java.util.Stack;
import provided.JottTree;
import provided.Token;

public class FuncDefParams_t implements JottTree {

    private IdNode id;
    private TypeNode type;

    public FuncDefParams_t(IdNode id, TypeNode type) {
        this.id = id;
        this.type = type;
    }

    public static FuncDefParams_t parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            // No more parameters
            throw new Exception("Syntax Error\n" + 
                                "Expected a token but tokens list is null.\n");            
        }
        tokens.pop();

        // Parse <id>
        IdNode idNode = IdNode.parse(tokens);

        // Expect ':'
        if (tokens.isEmpty() || !tokens.peek().getToken().equals(":")) {
            throw new Exception("Syntax Error\n" + 
                                "Expected ':' after parameter identifier.\n" +
                                tokens.peek().getLineNum());
        }
        tokens.pop();

        // Parse <type>
        TypeNode typeNode = TypeNode.parse(tokens);

        return new FuncDefParams_t(idNode, typeNode);
    }

    @Override
    public String convertToJott() {
      return ", " + id.convertToJott() + " : " + type.convertToJott();
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
