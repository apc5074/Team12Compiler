package parserNodes;
import java.util.Stack;
import provided.*;

public class VarDec implements JottTree {
    private static TypeNode typeNode;
    private static IdNode IDNode;


    public VarDec(TypeNode typeNode, IdNode IDNode) {
        this.typeNode = typeNode;
        this.IDNode = IDNode;
    }

    public static VarDec parse(Stack<Token> tokens) throws Exception {
        IdNode idNode = IdNode.parse(tokens);
        TypeNode typeNode = TypeNode.parse(tokens);

        return new VarDec(typeNode, idNode);
    }

    @Override
    public String convertToJott() {
        return typeNode.convertToJott() + " " +  IDNode.convertToJott();
    }

    @Override
    public boolean validateTree() {
        return true;
    }

    @Override
    public void execute() {
        // Not Implemented
    }

    @Override
    public String toString() {
        return "Not implemented";
    }


}
