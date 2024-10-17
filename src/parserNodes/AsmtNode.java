package parserNodes;
import java.util.Stack;
import provided.*;


public class AsmtNode implements BodyStatementNodeInterface {
    private static final Exception Exception = null;

    private Token idToken;
    private ExprNodeInterface expr;


    public AsmtNode (Token idToken, ExprNodeInterface expr)
    {
        this.idToken = idToken;
        this.expr = expr;
    }

    public static AsmtNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw new Exception("Syntax Error\n" + 
                                "Token list is empty.\n");        }
        Token iToken = tokens.peek();
        if (iToken.getTokenType() != TokenType.ID_KEYWORD)
        {
            throw new Exception("Syntax Error\n" + 
                                "Expected ID Keyword token but got\n" +
                                tokens.peek().getLineNum());
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.ASSIGN)
        {
            throw new Exception("Syntax Error:\n" + 
                                "Expected Assign Type token but got\n " + 
                                tokens.peek().getLineNum());
        }
        tokens.pop();
        ExprNodeInterface expr = ExprNodeInterface.parse(tokens);
        if (tokens.peek().getTokenType() == TokenType.SEMICOLON) {
            tokens.pop();
            return new AsmtNode(iToken, expr);
        } else {
            throw new Exception("Syntax Error:\n " + 
                                "Line does not end with semicolon at line\n" + 
                                tokens.peek().getLineNum());
        }
    }

    @Override
    public String convertToJott() {
        return idToken.getToken() + " = " + expr.convertToJott() + " ; ";
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

