package parserNodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ProgramNode implements JottTree {

    private static final Exception Exception = null;

    private ArrayList<FuncDefNode> functionDefNodes;

    private ProgramNode(ArrayList<FuncDefNode> funcDefNodes)
    {
        this.functionDefNodes = funcDefNodes;
    }

    public static ProgramNode parse(ArrayList<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            throw new Exception("Syntax Error\n" + 
                                "Token list is empty.\n" +
                                tokens.get(1).getLineNum());
        }
        Stack<Token> tokenStack = new Stack<>();
        // TODO: Figure out if this is supposed to be reversed or not.
        // I don't think it should be, but it was already reversed here. - Gabe
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
            throw new Exception("Syntax Error\n" + 
                                "Tokens remaining after parsing functions.\n" +
                                tokens.get(1).getLineNum());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
