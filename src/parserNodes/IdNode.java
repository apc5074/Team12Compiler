package parserNodes;

import java.util.Stack;

import provided.JottTree;
import provided.Token;

public class IdNode implements JottTree{

    private static final Exception Exception = null;
    private String name;

    public IdNode(String name)
    {
        this.name = name;
    }

    public static IdNode parse(Stack<Token> tokens) throws Exception {        
        if (!Character.isAlphabetic(tokens.get(0).getToken().indexOf(0)))
            throw Exception;
        String id = tokens.pop().getToken();
        return new IdNode(id);
    }

    @Override
    public String convertToJott() {
        return this.name;
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
