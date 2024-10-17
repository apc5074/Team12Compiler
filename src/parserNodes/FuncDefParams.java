package parserNodes;

import java.util.ArrayList;
import java.util.Stack;
import provided.JottTree;
import provided.Token;

public class FuncDefParams implements JottTree{

    private TypeNode type;
    private IdNode id;
    private static ArrayList<FuncDefParams_t> funcDefParams_t;

    // Constructor to initialize a FuncDefParam with type and identifier
    public FuncDefParams(IdNode id, TypeNode type, ArrayList<FuncDefParams_t> funcDefParams_t) {
        this.id = id;
        this.type = type;
        this.funcDefParams_t = funcDefParams_t; 
    }

     public static FuncDefParams parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty() || tokens.peek().getToken().equals("]")) {
            // no params
            return null;
        }
    
        // Parse <id>
        IdNode idNode = IdNode.parse(tokens);
    
        // Expect ':'
        if (!tokens.peek().getToken().equals(":")) {
            throw new Exception("Syntax Error\n" + 
                                "Syntax Error: Expected ':' after parameter identifier.\n" +
                                tokens.peek().getLineNum());
        }
        tokens.pop(); // Remove ':'
    
        // Parse <type>
        TypeNode typeNode = TypeNode.parse(tokens);
    
        Token curToken = tokens.peek();
        if (curToken.getToken().equals(",")) {
            ArrayList<FuncDefParams_t > arrayfuncDefParams_t = new ArrayList<FuncDefParams_t>();
            while (curToken.getToken() == ",")
            {
                arrayfuncDefParams_t.add(FuncDefParams_t.parse(tokens));
                curToken = tokens.peek();
            }
            return new FuncDefParams(idNode, typeNode, funcDefParams_t);

        }
    
        return new FuncDefParams(idNode, typeNode, funcDefParams_t);
    }

    @Override
    public String convertToJott() {
        String convertedToJott = id.convertToJott() + " : " + type.convertToJott();
        if (funcDefParams_t == null) {
            return convertedToJott;
        }
        for (FuncDefParams_t params: funcDefParams_t)
        {
            convertedToJott += params.convertToJott();
        }
        return convertedToJott;
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
