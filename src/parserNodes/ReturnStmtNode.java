package parserNodes;

import helpers.SemanticException;
import helpers.SymbolTable;
import java.util.Stack;
import provided.JottTree;
import provided.Token;

public class ReturnStmtNode implements JottTree{

    private ExprNodeInterface expressionNode;
    private Object returnVal;

    public ReturnStmtNode(ExprNodeInterface exrpessionNode)
    {
        this.expressionNode = exrpessionNode;
    }

    public static ReturnStmtNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty())
        {
            throw new Exception("Syntax error:\nExpected ReturnStmtNode but no tokens left");
        }
        Token curToken = tokens.peek();
        if (curToken.getToken().equals("Return"))
        {
            tokens.pop();
        }
        else
        {
            throw new Exception("Syntax error:\nExpected Return but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        ExprNodeInterface expression = ExprNodeInterface.parse(tokens);
        if (tokens.peek().getToken().equals(";"))
        {
            tokens.pop();
        }
        else
        {
            throw new Exception("Syntax error:\nMissing semi colon \n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        return new ReturnStmtNode(expression);

    }


    @Override
    public String convertToJott() {
        return "Return " + this.expressionNode.convertToJott() + " ;";
    }

    @Override
    public boolean validateTree() {
        if(expressionNode.validateTree())
        {

            if(SymbolTable.getFuncReturnType(SymbolTable.scope) != null)
            {
                if(SymbolTable.getFuncReturnType(SymbolTable.scope).getTypeName().equals(expressionNode.getExprType()))
                {
                    return true;
                }
                else {
                    SemanticException exception = new SemanticException(expressionNode.getLine(), expressionNode.getFilename(), "Return type is not correct.");
                    System.err.println(exception.toString());
                    return false;
                }
            }
            else
            {
                SemanticException exception = new SemanticException(expressionNode.getLine(), expressionNode.getFilename(), "Return present in voided method.");
                System.err.println(exception.toString());
                return false;
            }
        }
        // does not need error output.
        return false;
    }

    @Override
    public void execute() {
        expressionNode.execute();
        returnVal = expressionNode.getValue();
    }

    public Object getReturnStmtVal() {
        return returnVal;
    }
    

}
