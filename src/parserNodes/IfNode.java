package parserNodes;
import helpers.SemanticException;
import java.util.ArrayList;
import java.util.Stack;
import provided.*;

public class IfNode implements BodyStatementNodeInterface {
    ExprNodeInterface condition;
    BodyNode content;
    ArrayList<ElseIfNode> elifList;
    ElseNode elseNode;
    boolean allReturn;
    int startLine;

    public IfNode(ExprNodeInterface e, BodyNode c, ArrayList<ElseIfNode> el, ElseNode els, int stt) {
        condition = e;
        content = c;
        elifList = el;
        elseNode = els;
        allReturn = false;
        startLine = stt;
    }

    @Override
    public boolean isIf() {
        return true;
    }

    @Override
    public boolean ifReturn() {
        return allReturn;
    }

    public static IfNode parse(Stack<Token> tokens) throws Exception {
        Token toke = tokens.peek();
        if (toke.getTokenType() != TokenType.ID_KEYWORD || !toke.getToken().equals("If")) {
            throw new Exception("Syntax error\nIf expected at line " + toke.getLineNum());
        }
        int stt = toke.getLineNum();
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

        return new IfNode(e, b, el, els, stt);
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
        if (content.hasReturn()) {
            allReturn = true;
        }
        // TODO Auto-generated method stub
        for (ElseIfNode i: elifList) {
            if (allReturn) {
                if (!i.hasReturn()) {
                    SemanticException e = new SemanticException(startLine, ProgramNode.filename, "If statement has return, not all elseifs have return.");
                    System.err.println(e);
                    return false;
                }
            } else {
                if (i.hasReturn()) {
                    
                    SemanticException e = new SemanticException(startLine, ProgramNode.filename, "ElseIf statement has return, if does not.");
                    System.err.println(e);
                    return false;
                }
            }
            if (i.validateTree() == false) {
                return false;
            }
        }
        if (content.validateTree() == false) {
            // does not need error output.
            return false;
        }
        if (elseNode != null) {
            if (elseNode.validateTree() == false) {
                // does not need error output.
                return false;
            }
            if (allReturn && !elseNode.hasReturn()) {
                SemanticException e = new SemanticException(startLine, ProgramNode.filename, "If statement has return, but else does not.");
                System.err.println(e);
                return false;
            }
            if (!allReturn && elseNode.hasReturn()) {
                SemanticException e = new SemanticException(startLine, ProgramNode.filename, "Else statement has return, but if does not.");
                System.err.println(e);
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute() {
        condition.execute();
        if ((boolean)condition.getValue())
        {
            condition.execute();
        }
        else
        {
            if (elifList != null)
            {
                for (ElseIfNode node: elifList)
                {
                    if (node.checkCondtion())
                    {
                        node.execute();
                        break;
                    }
                }
            }
            else if (elseNode != null)
            {
                elseNode.execute();
            }
        }
    }
    
}
