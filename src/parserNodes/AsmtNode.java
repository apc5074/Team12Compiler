package parserNodes;
import java.util.Stack;

import helpers.SemanticException;
import helpers.SymbolTable;
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

        if(expr.validateTree()) // and idToken exists in symboltable
        {
            if (!SymbolTable.varDefined(idToken.getToken())) {
                SemanticException e = new SemanticException(idToken.getLineNum(), idToken.getFilename(), "Variable " + idToken.getToken() + " is not found.");
                System.out.println(e.toString());
                return false;
            }
            String symtab = SymbolTable.getVarType(idToken.getToken()).getTypeName();
            if(symtab.equals("Integer") &&  expr.getExprType().equals("Integer"))
            {
                return true;
            }
            else if (symtab.equals("Double") &&  (expr.getExprType().equals("Integer") || expr.getExprType().equals("Double")))
            {
                return true;
            }
            else if(symtab.equals("String") &&  expr.getExprType().equals("String"))
            {
                return true;
            }
            SemanticException e = new SemanticException(idToken.getLineNum(), idToken.getFilename(), "Found types " +
            symtab + " in the symbol table and " + expr.getExprType() + " in the expression.");
            System.out.println(e.toString());
            return false;
        }
        // only returns false if expression returns false.
        return false;
    }

    @Override
    public boolean isIf() {
        return false;
    }
    @Override
    public boolean ifReturn() {
        return false;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}

