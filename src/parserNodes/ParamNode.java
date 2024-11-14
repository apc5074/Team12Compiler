package parserNodes;
import provided.*;
import java.util.Stack;

import helpers.SymbolTable;

import java.util.ArrayList;

public class ParamNode implements JottTree {
    private ExprNodeInterface exprNode;
    private ArrayList<ParamNodeT> parameters;
    
    public ParamNode(ExprNodeInterface expression, ArrayList<ParamNodeT> paras) {
        exprNode = expression;
        parameters = paras;
    }

    public ParamNode() {
        exprNode = null;
        parameters = null;
    }

    public static ParamNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.size() == 0){
            throw new Exception("Syntax error:\nExpected ProgramNode but no tokens left");
        }
        if (tokens.peek().getTokenType() == TokenType.R_BRACKET) {
            return new ParamNode();
        }
        ExprNodeInterface toke = ExprNodeInterface.parse(tokens);
        if (toke == null) {
            throw new Exception("Syntax error:\nExpected valid ExprNode but got invalid expression");
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
        boolean validParams = true;
        for (ParamNodeT prm: parameters)
        {
            if (!prm.validateTree())
            {
                validParams = false;
                break;
            }
        }
        return exprNode.validateTree() && validParams;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    public ArrayList<String> getArgTypes()
    {
        ArrayList<String> argTypes = new ArrayList<>();
        argTypes.add(exprNode.getExprType());
        for (ParamNodeT arg: parameters)
        {
            argTypes.add(arg.getExprNode().getExprType());
        }
        return argTypes;

    }
}
