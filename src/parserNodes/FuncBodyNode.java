package parserNodes;

import java.util.ArrayList;
import java.util.Stack;
import provided.JottTree;
import provided.Token;

public class FuncBodyNode implements JottTree {

    private ArrayList<VarDec> varDec; 
    private BodyNode body; 

    public FuncBodyNode(ArrayList<VarDec> varDec, BodyNode body) {
        this.varDec = varDec;
        this.body = body;
    }

    public static FuncBodyNode parse(Stack<Token> tokens) throws Exception {
        
        if (tokens.isEmpty()) {
            throw new Exception("Syntax error:\nExpected FuncBodyNode but no tokens left");
        }
        Token curToken = tokens.peek();
        ArrayList<VarDec> vars = new ArrayList<>();
        while (isValidText(curToken))
        {
            VarDec varDec = VarDec.parse(tokens);
            vars.add(varDec);
            curToken = tokens.peek();
        }
        BodyNode bodyStatementNode = BodyNode.parse(tokens);
        return new FuncBodyNode(vars, bodyStatementNode);
    }
    private static boolean isValidText(Token type) {
        return type.getToken().equals("Integer") || type.getToken().equals("Double") 
            || type.getToken().equals("String") || type.getToken().equals("Boolean");
    }
    @Override
    public String convertToJott() {
        String fbodyString = "";
        for (VarDec vardecs: varDec)
        {
            fbodyString += vardecs.convertToJott() + " ";
        }
        fbodyString += body.convertToJott();
        return fbodyString;
    }

    @Override
    public boolean validateTree() {
        for (VarDec v: varDec) {
            if (v.validateTree() == false) {
                // does not need error output.
                return false;
            }
        }
        return body.validateTree();
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
