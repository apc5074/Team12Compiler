package parserNodes;

import helpers.SemanticException;
import helpers.SymbolTable;
import java.util.ArrayList;
import java.util.Stack;
import provided.JottTree;
import provided.Token;

public class FuncBodyNode implements JottTree {

    private ArrayList<VarDec> varDec; 
    private BodyNode body; 
    private int startLine;

    public FuncBodyNode(ArrayList<VarDec> varDec, BodyNode body, int stt) {
        this.varDec = varDec;
        this.body = body;
        startLine = stt;
    }

    public static FuncBodyNode parse(Stack<Token> tokens) throws Exception {
        
        if (tokens.isEmpty()) {
            throw new Exception("Syntax error:\nExpected FuncBodyNode but no tokens left");
        }
        Token curToken = tokens.peek();
        int stt = curToken.getLineNum();
        ArrayList<VarDec> vars = new ArrayList<>();
        while (isValidText(curToken))
        {
            VarDec varDec = VarDec.parse(tokens);
            vars.add(varDec);
            curToken = tokens.peek();
        }
        BodyNode bodyStatementNode = BodyNode.parse(tokens);
        return new FuncBodyNode(vars, bodyStatementNode, stt);
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
        boolean t_f = body.validateTree();
        if (t_f == false) {
            return t_f;
        }
        if (!body.hasReturn() && SymbolTable.getFuncReturnType(SymbolTable.scope) != null) {
            SemanticException e = new SemanticException(startLine, ProgramNode.filename, "Function " +
            SymbolTable.scope + " missing return.");
            System.err.println(e.toString());
            return false;
        }
        return t_f;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
