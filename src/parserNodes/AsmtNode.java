package parserNodes;
import provided.*;
import java.util.Stack;


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
            throw Exception;
        }
        Token iToken = tokens.peek();
        if (iToken.getTokenType() != TokenType.ID_KEYWORD)
        {
            throw Exception;
        }
        System.out.println("ASMTNODE: " + tokens.pop().getToken());
        if (tokens.peek().getTokenType() != TokenType.ASSIGN)
        {
            System.out.println(tokens.peek().getToken());
            throw new Exception("Syntax error:\nUnexpected token "+tokens.peek().getTokenType() + " at " + tokens.peek().getLineNum());
        }
        tokens.pop();
        ExprNodeInterface expr = ExprNodeInterface.parse(tokens);
        if (tokens.peek().getTokenType() == TokenType.SEMICOLON) {
            tokens.pop();
            return new AsmtNode(iToken, expr);
        } else {
            throw new Exception("Line does not end with semicolon at line\n" + tokens.peek().getLineNum());
        }
    }

    @Override
    public String convertToJott() {
        return idToken.getToken() + " = " + expr.convertToJott() + ";";
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

