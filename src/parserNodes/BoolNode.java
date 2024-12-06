package parserNodes;
import provided.*;
import java.util.Stack;

public class BoolNode implements ExprNodeInterface {    
    private Token boolToken;

    public BoolNode(Token boolToken) {
        this.boolToken = boolToken;
    }

    public static BoolNode parse (Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw new Exception ("Syntax Error: \n Expected a boolean but file ended.");
        }
        Token iToken = tokens.peek();
        if (iToken.getTokenType() != TokenType.ID_KEYWORD || iToken.getToken().equals("true") || iToken.getToken().equals("false"))
        {
            throw new Exception("Syntax error:\nExpected id but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }

        return new BoolNode(iToken);
    }

    @Override
    public String convertToJott() {
        return boolToken.getToken();
    }

    @Override
    public boolean validateTree() {
        return true;
    }

    @Override
    public int getLine() {
        return boolToken.getLineNum();
    }

    @Override
    public void execute() {
        return;
    }

    @Override
    public String getExprType() {
        return "Boolean";
    }

    @Override
    public String getFilename() {
        return "";
    }

    @Override
    public Object getValue() {
        return boolToken.getToken();
    }
}
