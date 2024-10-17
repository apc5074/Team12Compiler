package parserNodes;

import java.util.Stack;

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
            throw new Exception("Unexpected EOF");
        }    
        Token idToken = tokens.peek();
        if (idToken.getTokenType() != TokenType.ID_KEYWORD)
        {
            throw new Exception("Syntax error\nInvalid token type at\n"  + idToken.getLineNum());
        }    
        return new IdNode(tokens.pop());
    }

    @Override
    public String convertToJott() {
        return this.idToken.getToken();
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
