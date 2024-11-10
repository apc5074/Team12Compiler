package parserNodes;

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
        List<TypeNode> funcParams = params.getList();
        funcParams.add(returnType);
        
        SymbolTable.addFunction(funcName.getIdToken().getToken(), funcParams);

        return funcName.validateTree() && params.validateTree() && body.validateTree();
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}

