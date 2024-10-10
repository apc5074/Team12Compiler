package parserNodes;
import provided.*;
import java.util.Stack;
import java.util.ArrayList;

public class ParamNode implements JottTree {
    private final static Exception e = null;
    private ExprNode exprNode;
    private ArrayList<ParamNodeT> parameters;
    
    public ParamNode(ExprNode expression, ArrayList<ParamNodeT> paras) {
        exprNode = expression;
        parameters = paras;
    }

    public ParamNode() {
        exprNode = null;
        parameters = null;
    }

    public static ParamNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.size() == 0){
            throw e;
        }
        if (tokens.peek().getTokenType() == TokenType.R_BRACKET) {
            return new ParamNode();
        }
        ExprNode toke = ExprNode.parse(tokens);
        if (toke == null) {
            throw e;
        }
        
        ArrayList<ParamNodeT> pramters = new ArrayList<ParamNodeT>();
        while (tokens.peek().getTokenType() == TokenType.COMMA) {
            pramters.add(ParamNodeT.parse(tokens));
        }
        return new ParamNode(toke, pramters);
    }

    @Override
    public String convertToJott() {
        if (exprNode != null) {
            String k = exprNode.convertToJott();
            for (int i = 0; i < parameters.size(); i++) {
                k += parameters.get(i).convertToJott();
            }
            return k;
        } else {
            return "";
        }

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
