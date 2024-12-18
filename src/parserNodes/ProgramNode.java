package parserNodes;

import helpers.SemanticException;
import helpers.SymbolTable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import provided.*;

public class ProgramNode implements JottTree {

    private ArrayList<FuncDefNode> functionDefNodes;
    public static String filename;

    private ProgramNode(ArrayList<FuncDefNode> funcDefNodes)
    {
        this.functionDefNodes = funcDefNodes;
    }

    public static ProgramNode parse(ArrayList<Token> tokens) throws Exception {
        filename = tokens.get(0).getFilename();
        if (tokens.isEmpty()) {
            throw new Exception("Syntax error:\nExpected ProgramNode but no tokens left");
        }
        Stack<Token> tokenStack = new Stack<>();
        Collections.reverse(tokens);
        tokenStack.addAll(tokens);
        ArrayList<FuncDefNode> functionDefNodes = new ArrayList<>();
        
        Token curToken = tokenStack.peek();
        while (curToken.getTokenType() == TokenType.ID_KEYWORD) {
            functionDefNodes.add(FuncDefNode.parse(tokenStack));
            if (!tokenStack.isEmpty()) {
                curToken = tokenStack.peek();
            } else {
                break;
            }
        }
        
        if (tokenStack.isEmpty()) {
            return new ProgramNode(functionDefNodes);
        } else {
            throw new Exception("Syntax error:\nExpected end of file but got "+tokenStack.peek().getTokenType() + "\n" + tokenStack.peek().getFilename() + ".jott:" + tokenStack.peek().getLineNum());
        }
    }
    

    @Override
    public String convertToJott() {
        String programString = "";
        for (FuncDefNode FDN: this.functionDefNodes)
        {
            programString += FDN.convertToJott();
        }
        return programString;
    }

    @Override
    public boolean validateTree() {
        SymbolTable.addPrimativeFunctions();
        for (FuncDefNode i: functionDefNodes) {
            if (i.validateTree() == false) {
                SemanticException exception = new SemanticException(i.getToken().getLineNum(), i.getToken().getFilename(), "Function definition not correct.");
                exception.toString();
                return false;
            }
        }
        if (!SymbolTable.funcDefined("main")) {
            SemanticException e = new SemanticException(0, filename, "Main method is not defined.");
            System.err.println(e.toString());
            return false;
        }
        if (SymbolTable.getFuncReturnType("main") != null) {
            SemanticException e = new SemanticException(0, filename, "Main method is not void.");
            System.err.println(e.toString());
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        FuncDefNode mainFunction = SymbolTable.getfuncDef("main");
        mainFunction.execute();
    }
    
}
