package parserNodes;

import java.util.Stack;

import provided.Token;
import provided.TokenType;

public class StringLiteralNode implements ExprNodeInterface{

    private Token stringToken;

    public StringLiteralNode(Token str)
    {
        this.stringToken = str;
    }

    public StringLiteralNode parse(Stack<Token> tokens) throws Exception{
        if (tokens.empty())
        {
            throw Exception;
        }
        if (tokens.get(0).getTokenType() != TokenType.STRING)
        {
            throw Exception;
        }
        Token curToken = tokens.pop();

        return new StringLiteralNode(curToken);
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

    @Override
    public String convertToJott() {
        return stringToken.getToken();
    }

}
