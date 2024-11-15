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

    @Override
    public int getLine() {
        return stringToken.getLineNum();
    }

    public static StringLiteralNode parse(Stack<Token> tokens) throws Exception{
        if (tokens.empty())
        {
            throw new Exception("Syntax error:\nExpected StringLiteralNode but no tokens left");
        }
        if (tokens.peek().getTokenType() != TokenType.STRING)
        {
            throw new Exception("Syntax error:\nExpected String but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        Token curToken = tokens.pop();

        return new StringLiteralNode(curToken);
    }

    @Override
    public boolean validateTree() {
        return true;
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

    @Override
    public String getExprType() {
        return "String";
    }

    @Override
    public String getFilename() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFilename'");
    }

    

}
