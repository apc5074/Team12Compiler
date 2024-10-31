package parserNodes;
import java.util.Stack;

import provided.*;

public class ElseNode implements JottTree {
    private BodyNode body;
    private Boolean isNull;

    public ElseNode(BodyNode b) {
        isNull = false;
        body = b;
    }

    public ElseNode() {
        isNull = true;
    }

    public static ElseNode parse(Stack<Token> tokens) throws Exception {
        Token next = tokens.peek();
        if (next.getTokenType() != TokenType.ID_KEYWORD | !next.getToken().equals("Else")) {
            return new ElseNode();
        }
        // undeclared else - the next keyword is Else and an ID.
        tokens.pop();
        next = tokens.pop();
        if (next.getTokenType() != TokenType.L_BRACE) {
            // if else is called without {
            throw new Exception ("Syntax Error: \nExpected \"{\"\n"+next.getFilename()+":"+next.getLineNum());
        }
        BodyNode b = BodyNode.parse(tokens);
        next = tokens.pop();
        if (next.getTokenType() != TokenType.R_BRACE) {
            // if else is called without }
            throw new Exception ("Syntax Error: \nExpected \"}\"\n"+next.getFilename()+":"+next.getLineNum());
        }
        return new ElseNode(b);
    }

    @Override
    public String convertToJott() {
        return "Else { " + body.convertToJott() + " }";
    }

    @Override
    public boolean validateTree() {
        return body.validateTree();
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
