package parserNodes;

import java.util.ArrayList;
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

    public static ProgramNode parse(Stack<Token> tokens) throws java.lang.Exception{
        if (tokens.isEmpty())
        {
            throw Exception;
        }
        ArrayList<FuncDefNode> functionDefNodes = new ArrayList<>();
        Token curToken = tokens.get(0);
        while (curToken.getTokenType() == TokenType.ID_KEYWORD)
        {
            functionDefNodes.add(FuncDefNode.parse(tokens));
        }
        if (tokens.isEmpty())
        {
            return new ProgramNode(functionDefNodes);
        }
        else
        {
            throw Exception;
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
