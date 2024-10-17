package parserNodes;

import java.util.ArrayList;
import java.util.Stack;

import provided.JottTree;
import provided.Token;

public class FuncDefNode implements JottTree {

    private static final Exception Exception = null;
    private IdNode funcName;
    private FuncDefParams params;
    private TypeNode returnType;
    private FuncBodyNode body;

    public FuncDefNode(IdNode name, FuncDefParams params, TypeNode returnType, FuncBodyNode body)
    {
        this.funcName = name;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    public static FuncDefNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.empty())
        {
            throw Exception;
        }

        if(!tokens.peek().getToken().equals("Def"))
            throw Exception;
        tokens.pop();

        IdNode name = IdNode.parse(tokens);

        if(!(tokens.peek().getToken().equals("[")))
            throw Exception;
        tokens.pop();
        FuncDefParams params = FuncDefParams.parse(tokens);

        if(!tokens.peek().getToken().equals("]"))
            throw Exception;
        tokens.pop();
        
        if(!tokens.peek().getToken().equals(":"))
            throw Exception;
        tokens.pop();

        TypeNode returnType = TypeNode.parse(tokens);

        if(!tokens.peek().getToken().equals("{"))
            throw Exception;
        tokens.pop();
        FuncBodyNode body = FuncBodyNode.parse(tokens);
        
        if (!tokens.peek().getToken().equals("}"))
            throw Exception;
        tokens.pop();

        return new FuncDefNode(name, params, returnType, body);
    }

    @Override
    public String convertToJott() {
        String jottString = "Def" + " " + funcName.convertToJott() + "[";
        if (this.params != null)
        {
            jottString+= params.convertToJott();
        }
        jottString +=  "]" + ":" + returnType.convertToJott() + "{" + body.convertToJott() + "}";
        return jottString;
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
