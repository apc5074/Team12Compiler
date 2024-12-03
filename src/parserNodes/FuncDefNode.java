package parserNodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import helpers.SymbolTable;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class FuncDefNode implements JottTree {
    private IdNode funcName;
    private FuncDefParams params;
    private TypeNode returnType;
    private boolean isVoid;
    private FuncBodyNode body;

    public FuncDefNode(IdNode name, FuncDefParams params, TypeNode returnType, FuncBodyNode body)
    {
        this.funcName = name;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
        this.isVoid = false;
    }

    public FuncDefNode(IdNode name, FuncDefParams params, FuncBodyNode body)
    {
        this.funcName = name;
        this.params = params;
        this.isVoid = true;
        this.body = body;
    }

    public static FuncDefNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.empty())
        {
            throw new Exception ("Syntax Error \n Expected a function definition but file ended.");
        }

        if(!tokens.peek().getToken().equals("Def"))
            throw new Exception ("Syntax Error \n Expected \"Def\" Got \"" + tokens.peek().getToken() + "\" at " + tokens.peek().getFilename() + ":" + tokens.peek().getLineNum());
        tokens.pop();

        IdNode name = IdNode.parse(tokens);

        if(!(tokens.peek().getToken().equals("[")))
            throw new Exception ("Syntax Error \n Expected \"[\" Got \"" + tokens.peek().getToken() + "\" at " + tokens.peek().getFilename() + ":" + tokens.peek().getLineNum());
        tokens.pop();
        FuncDefParams params = FuncDefParams.parse(tokens);

        if(!tokens.peek().getToken().equals("]"))
            throw new Exception ("Syntax Error \n Expected \"]\" Got \"" + tokens.peek().getToken() + "\" at " + tokens.peek().getFilename() + ":" + tokens.peek().getLineNum());
        tokens.pop();
        
        if(!tokens.peek().getToken().equals(":"))
            throw new Exception ("Syntax Error \n Expected \":\" Got \"" + tokens.peek().getToken() + "\" at " + tokens.peek().getFilename() + ":" + tokens.peek().getLineNum());
        tokens.pop();
        TypeNode returnType = null;
        boolean isVoid = false;
        if (tokens.peek().getTokenType() == TokenType.ID_KEYWORD && tokens.peek().getToken().equals("Void")) {
            isVoid = true;
            tokens.pop();
        } else {
            returnType = TypeNode.parse(tokens);
        }

        if(!tokens.peek().getToken().equals("{"))
            throw new Exception ("Syntax Error \n Expected \"{\" Got \"" + tokens.peek().getToken() + "\" at " + tokens.peek().getFilename() + ":" + tokens.peek().getLineNum());
        tokens.pop();
        FuncBodyNode body = FuncBodyNode.parse(tokens);
        
        if (!tokens.peek().getToken().equals("}"))
            throw new Exception ("Syntax Error \n Expected \"}\" Got \"" + tokens.peek().getToken() + "\" at " + tokens.peek().getFilename() + ":" + tokens.peek().getLineNum());
        tokens.pop();
        if (isVoid) {
            return new FuncDefNode(name, params, body);
        } else {
            return new FuncDefNode(name, params, returnType, body);
        }
    }

    @Override
    public String convertToJott() {
        String jottString = "Def" + " " + funcName.convertToJott() + " [ ";
        if (this.params != null)
        {
            jottString+= params.convertToJott();
        }
        if (isVoid) {
            jottString +=  " ]" + " : Void { " + body.convertToJott() + " } ";   
        } else {
            jottString +=  " ]" + " : " + returnType.convertToJott() + " { " + body.convertToJott() + " } ";
        }
        return jottString;
    }

    @Override
    public boolean validateTree() {
        SymbolTable.addScope(funcName.getIdToken().getToken());
        SymbolTable.setScope(funcName.getIdToken().getToken());
        List<TypeNode> funcParams = new ArrayList<>();
        if (params != null) {
            funcParams = params.getList();
        }
        funcParams.add(returnType);
        SymbolTable.addFunction(funcName.getIdToken().getToken(), funcParams);
        if (params != null)
        {
            if (!params.validateTree())
            {
                // does not need error output.
                return false;
            }
        }
        return funcName.validateTree() && body.validateTree();
    }

    @Override
    public void execute() {
        // here's what I'm thinking.
        // when a function is called in another function, the PARAMETER variables are defined within that function.
        // when a function is called in another function, the INBODY variables are defined here.
        // theoretically, there shouldn't be an Execute() function for func_def_params, true?
        // also, funcCallNode needs to save the scope and update it ONLY after the function is finished executing.
        // this is just to reset the scope.
        SymbolTable.scope = funcName.getIdToken().getToken();
        this.body.execute();
        // now the final variable in the vValTable should be updated to be the return value.
        // so, in the parent function, before we reset the scope, we grab the -1 index of the vValTable, correct?
    }

    public Token getToken()
    {
        return funcName.getIdToken();
    }

}

