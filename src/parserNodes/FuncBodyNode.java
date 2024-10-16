package parserNodes;

import java.util.Stack;
import provided.JottTree;
import provided.Token;
import java.util.ArrayList;

public class FuncBodyNode implements JottTree {

    private ArrayList<VarDec> varDec; 
    private BodyNode body; 

    public FuncBodyNode(ArrayList<VarDec> varDec, BodyNode body) {
        this.varDec = varDec;
        this.body = body;
    }

    public static FuncBodyNode parse(Stack<Token> tokens) throws Exception {
        // TODO Auto-generated method stub
        if (tokens.isEmpty()) {
            throw new Exception("Unexpected end of input while parsing function body.");
        }
        Token curToken = tokens.peek();
        ArrayList<VarDec> vars = new ArrayList<>();
        while (isValidText(curToken))
        {
            VarDec varDec = VarDec.parse(tokens);
            vars.add(varDec);
        }
        BodyNode bodyStatementNode = BodyNode.parse(tokens);
        return new FuncBodyNode(vars, bodyStatementNode);
    }
    private static boolean isValidText(Token type) {
        return type.getToken().equals("Int") || type.getToken().equals("Double") 
            || type.getToken().equals("String") || type.getToken().equals("Boolean") || type.getToken().equals("Void");
    }
    @Override
    public String convertToJott() {
        String fbodyString = "";
        for (VarDec vardecs: varDec)
        {
            fbodyString += vardecs.convertToJott();
        }
        fbodyString += body.convertToJott();
        return fbodyString;
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
