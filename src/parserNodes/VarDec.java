package parserNodes;
import java.util.Stack;
import provided.*;

public class VarDec implements JottTree {
    private TypeNode typeNode;
    private IdNode IDNode;


    public VarDec(TypeNode typeNode, IdNode IDNode) {
        this.typeNode = typeNode;
        this.IDNode = IDNode;
    }

    public static VarDec parse(Stack<Token> tokens) throws Exception {
        
        TypeNode typeNode = TypeNode.parse(tokens);
        IdNode idNode = IdNode.parse(tokens);

        if (tokens.peek().getTokenType() == TokenType.SEMICOLON) {
            tokens.pop();
            return new VarDec(typeNode, idNode);
        } else {
            throw new Exception("Semicolon expected at line\n" + tokens.peek().getLineNum());
        }
    }

    @Override
    public String convertToJott() {
        return typeNode.convertToJott() + " " +  IDNode.convertToJott() +" ;";
    }

    @Override
    public boolean validateTree() {
        return true;
    }

    @Override
    public void execute() {
        // TODO Not Implemented
    }

    @Override
    public String toString() {
        // TODO Not implemented
        return "Not implemented";
    }


}
