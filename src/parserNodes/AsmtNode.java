package parserNodes;
import java.util.Stack;

import helpers.SyntaxException;
import provided.*;


public class AsmtNode implements BodyStatementNodeInterface {
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
            throw new SyntaxException("Token list is empty.");      
        }
        Token iToken = tokens.peek();
        if (iToken.getTokenType() != TokenType.ID_KEYWORD)
        {

            throw new SyntaxException(tokens.peek().getLineNum(),tokens.peek().getFilename(),"Expected ID Keyword token but got " + tokens.peek().getTokenType());
        }
        tokens.pop();
        if (tokens.peek().getTokenType() != TokenType.ASSIGN)
        {
            throw new SyntaxException(tokens.peek().getLineNum(),tokens.peek().getFilename(),"Expected Assign Type token but got " + tokens.peek().getTokenType());
        }
        tokens.pop();
        ExprNodeInterface expr = ExprNodeInterface.parse(tokens);
        if (tokens.peek().getTokenType() == TokenType.SEMICOLON) {
            tokens.pop();
            return new AsmtNode(iToken, expr);
        } else {
            throw new SyntaxException(tokens.peek().getLineNum(),tokens.peek().getFilename(),"Line does not end with semicolon.");
        }
    }

    @Override
    public String convertToJott() {
        return idToken.getToken() + " = " + expr.convertToJott() + " ; ";
    }

    @Override
    public boolean validateTree() {
        boolean validate = true;

        if(idToken.validateTree() && expr.validateTree())
        {
            if(symtable.getType(idToken).equals(symtable.getType(expr))) // checks if int if 0
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}

