package parserNodes;
import java.util.Stack;
import provided.*;

public class ElseIfNode implements JottTree {
    private BodyNode body;
    private ExprNode expr;

    public ElseIfNode(BodyNode b, ExprNode e) {
        body = b;
        expr = e;
    }

    public static ElseIfNode parse(Stack<Token> tokens) throws Exception {
        Token next = tokens.peek();
        // if this token is parsed with no elseif there, it returns null. this means
        // the if statement - which is the only one to call it - will be told "hey, there's no more elifs."
        if (next.getTokenType() != TokenType.ID_KEYWORD | next.getToken() != "Elseif") {
            return null;
        }
        // undeclared else - the next keyword is Else and an ID.
        tokens.pop();
        next = tokens.pop();
        if (next.getTokenType() != TokenType.L_BRACKET) {
            // if elseif is called without [
            throw new Exception ("Syntax Error:\nExpected \"[\"\n"+next.getFilename()+":"+next.getLineNum());
        }
        ExprNode e = ExprNode.parse(tokens);
        next = tokens.pop();
        if (next.getTokenType() != TokenType.R_BRACKET) {
            // if elseif is called without ]
            throw new Exception ("Syntax Error:\nExpected \"]\"\n"+next.getFilename()+":"+next.getLineNum());
        }
        next = tokens.pop();
        if (next.getTokenType() != TokenType.L_BRACE) {
            // if elseif is called without {
            throw new Exception ("Syntax Error:\nExpected \"{\"\n"+next.getFilename()+":"+next.getLineNum());
        }
        BodyNode b = BodyNode.parse(tokens);
        next = tokens.pop();
        if (next.getTokenType() != TokenType.R_BRACE) {
            // if elseif is called without }
            throw new Exception ("Syntax Error:\nExpected \"}\"\n"+next.getFilename()+":"+next.getLineNum());
        }
        return new ElseIfNode(b, e);
    }

    public boolean checkCondtion()
    {
        expr.execute();
        return expr.getValue();
    }

    @Override
    public String convertToJott() {
        return "Elseif [" + expr.convertToJott() + "]{ " + body.convertToJott() + " }";
    }

    public boolean hasReturn() {
        return body.hasReturn();
    }

    @Override
    public boolean validateTree() {
        return (body.validateTree() && expr.validateTree());
    }

    @Override
    public void execute() {
        expr.execute();
        if (expr.getValue())
        {
            body.execute();
        }
    }
    
}
