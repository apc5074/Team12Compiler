package parserNodes;
import provided.*;
import java.util.Stack;


public class AsmtNode implements BodyStatementNodeInterface {
    private static final Exception Exception = null;

    private Token idToken;
    private ExprNode expr;


    public AsmtNode (Token idToken, ExprNode expr)
    {
        this.idToken = idToken;
        this.expr = expr;
    }

    private static AsmtNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw Exception;
        }
        Token iToken = tokens.peek();
        if (iToken.getTokenType() != TokenType.ID_KEYWORD)
        {
            throw Exception;
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.ASSIGN)
        {
            throw Exception;
        }
        tokens.pop();
        ExprNode expr = ExprNode.parse(tokens);

        return new AsmtNode(iToken, expr);
    }

    @Override
    public String convertToJott() {
        return idToken + " = " + expr;
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

