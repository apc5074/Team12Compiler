package parserNodes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

import provided.Token;

public class FuncDefNode {

    private static final Exception Exception = null;
    private IdNode funcName;
    private Object params;
    private TypeNode returnType;
    private FuncBodyNode body;

    public FuncDefNode(IdNode name, ArrayList<FuncDefParams> params, TypeNode returnType, FuncBodyNode body)
    {
        this.funcName = name;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    public static FuncDefNode parse(Stack<Token> tokens) throws Exception {
        if(!tokens.get(0).getToken().equals("Def"))
            throw Exception;
        tokens.pop();
        ArrayList<FuncDefParams> params = FuncDefParams.parse(tokens);

        IdNode name = IdNode.parse(tokens);

        if(!(tokens.get(0).getToken().equals("[")))
            throw Exception;
        tokens.pop();
        ArrayList<FuncDefParams> params = FuncDefParams.parse(tokens);

        if(!tokens.get(0).getToken().equals("]"))
            throw Exception;
        tokens.pop();
        
        if(!tokens.get(0).getToken().equals(":"))
            throw Exception;
        tokens.pop();

        TypeNode returnType = TypeNode.parse(tokens);

        if(!tokens.get(0).getToken().equals("{"))
            throw Exception;
        tokens.pop();

        FuncBodyNode body = FuncBodyNode.parse(tokens);
        
        if (!tokens.get(0).getToken().equals("}"))
            throw Exception;
        tokens.pop();

        return new FuncDefNode(name, params, returnType, body);
    }

}
