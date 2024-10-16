package parserNodes;

import java.util.Stack;
import provided.JottTree;
import provided.Token;

public class FuncBodyNode implements JottTree {

    private VarDec varDec; 
    private BodyStatementNode body; 

    public FuncBodyNode(VarDec varDec, BodyStatementNode body) {
        this.varDec = varDec;
        this.body = body;
    }

    public static FuncBodyNode parse(Stack<Token> tokens) throws Exception {
        // TODO Auto-generated method stub
        if (tokens.isEmpty()) {
            throw new Exception("Unexpected end of input while parsing function body.");
        }
        
        VarDec varDec = VarDec.parse(tokens);
        BodyStatementNode bodyStatementNode = BodyStatementNode.parse(tokens);
        return new FuncBodyNode(varDec, bodyStatementNode);
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return varDec.convertToJott() + " " + body.convertToJott();
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
