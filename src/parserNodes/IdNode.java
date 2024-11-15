package parserNodes;

import java.util.Stack;
import helpers.SymbolTable;
import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class IdNode implements JottTree{

    private Token idToken;

    public IdNode(Token idToken)
    {
        this.idToken = idToken;
    }

    public static IdNode parse(Stack<Token> tokens) throws Exception {    
        if (tokens.empty())
        {
            throw new Exception("Syntax error:\nExpected IdNode but got End of File");
        }    
        Token idToken = tokens.peek();
        if (idToken.getTokenType() != TokenType.ID_KEYWORD)
        {
            throw new Exception("Syntax error:\nExpected id but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }    
        return new IdNode(tokens.pop());
    }

    public Token getIdToken() {
        return idToken;
    }

    public int getLine() {
        return idToken.getLineNum();
    }

    @Override
    public String convertToJott() {
        return this.idToken.getToken();
    }

    @Override
    public boolean validateTree() {
        if(SymbolTable.varDefined(idToken.getToken()) || SymbolTable.funcDefined(idToken.getToken()))
        {
            return true;
        }
        System.out.println("Semantic Error:\nKeyword " + idToken.getToken() + " not recognized.\nLine " + idToken.getLineNum());
        return false;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
