package parserNodes;
import java.util.ArrayList;
import java.util.Stack;
import provided.*;

public class IfNode implements BodyStatementNodeInterface {
    ExprNodeInterface condition;
    BodyNode content;
    ArrayList<ElseIfNode> elifList;
    ElseNode elseNode;

    public IfNode(ExprNodeInterface e, BodyNode c, ArrayList<ElseIfNode> el, ElseNode els) {
        condition = e;
        content = c;
        elifList = el;
        elseNode = els;
    }

    public static IfNode parse(Stack<Token> tokens) throws Exception {
        Token toke = tokens.peek();
        if (toke.getTokenType() != TokenType.ID_KEYWORD || !toke.getToken().equals("If")) {
            throw new Exception("Syntax error\nIf expected at line " + toke.getLineNum());
        }
        tokens.pop();

        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.L_BRACKET) {
            throw new Exception("Syntax error:\nExpected Left Bracket but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        
        ExprNodeInterface e = ExprNodeInterface.parse(tokens);
        
        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.R_BRACKET) {
            throw new Exception("Syntax error:\nExpected Right Bracket but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();

        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.L_BRACE) {
            throw new Exception("Syntax error:\nExpected left brace but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        
        BodyNode b = BodyNode.parse(tokens);

        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.R_BRACE) {
            throw new Exception("Syntax error:\nExpected Right Brace but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        
        ArrayList<ElseIfNode> el = new ArrayList<>();
        
        // a list of 0-n elif conditions.
        toke = tokens.peek();
        while (toke.getTokenType() == TokenType.ID_KEYWORD && toke.getToken().equals( "Elseif")) {
            el.add(ElseIfNode.parse(tokens));
            toke = tokens.peek();
        }

        ElseNode els = null;
        // a single else condition.
        toke = tokens.peek();
        if (toke.getTokenType() == TokenType.ID_KEYWORD && toke.getToken().equals("Else")) {
            els = ElseNode.parse(tokens);
        }

        return new IfNode(e, b, el, els);
    }

    @Override
    public String convertToJott() {
        String output = "If [ " + condition.convertToJott() + " ] { " + content.convertToJott() + " } ";
        for (int i = 0; i < elifList.size(); i++) {
            output += elifList.get(i).convertToJott() + " ";
        }
        if (elseNode != null) {
            output += elseNode.convertToJott();
        }
        return output;

    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        for (ElseIfNode i: elifList) {
            if (i.validateTree() == false) {
                return false;
            }
        }
        if (content.validateTree() == false) {
            return false;
        }
        if (elseNode != null  && elseNode.validateTree() == false) {
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
