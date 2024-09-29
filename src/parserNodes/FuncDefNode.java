package parserNodes;

import java.util.ArrayList;
import java.util.Stack;

import provided.JottTree;
import provided.Token;

public class FuncDefNode implements JottTree {

    private static final Exception Exception = null;
    private IdNode funcName;
    private ArrayList<FuncDefParams> params;
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

    @Override
    public String convertToJott() {
        String jottString = "Def" + funcName.convertToJott() + "[";
        for (FuncDefParams param: params)
        {
            jottString+= param.convertToJott();
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
