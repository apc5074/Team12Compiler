package parserNodes;
import java.util.ArrayList;
import provided.*;
import java.util.Stack;

public class IfNode implements JottTree {
    ExprNode condition;
    BodyNode content;
    ArrayList<ElseIfNode> elifList;
    ElseNode elseNode;

    public IfNode(ExprNode e, BodyNode c, ArrayList<ElseIfNode> el, ElseNode els) {
        condition = e;
        content = c;
        elifList = el;
        elseNode = els;
    }

    public static IfNode parse(Stack<Token> tokens) throws Exception {
        Token toke = tokens.pop();
        if (toke.getTokenType() != TokenType.ID_KEYWORD || !toke.getToken().equals("If")) {
            throw new Exception("Syntax error\nIf expected at line " + toke.getLineNum());
        }
        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.L_BRACKET) {
            throw new Exception("Syntax error\n\"[\" expected at line " + toke.getLineNum());
        }
        ExprNode e = ExprNode.parse(tokens);
        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.R_BRACKET) {
            throw new Exception("Syntax error\n\"]\" expected at line " + toke.getLineNum());
        }
        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.L_BRACE) {
            throw new Exception("Syntax error\n\"{\" expected at line " + toke.getLineNum());
        }
        BodyNode b = BodyNode.parse(tokens);
        toke = tokens.pop();
        if (toke.getTokenType() != TokenType.R_BRACE) {
            throw new Exception("Syntax error\n\"}\" expected at line " + toke.getLineNum());
        }
        ArrayList<ElseIfNode> el = new ArrayList<ElseIfNode>();
        // a list of 0-n elif conditions.
        while (tokens.peek().getTokenType() == TokenType.ID_KEYWORD && tokens.peek().getToken() == "Elseif") {
            el.add(ElseIfNode.parse(tokens));
        }
        ElseNode els;
        // a single else condition.
        if (tokens.peek().getTokenType() == TokenType.ID_KEYWORD && tokens.peek().getToken() == "Else") {
            els = ElseNode.parse(tokens);
        }
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
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
