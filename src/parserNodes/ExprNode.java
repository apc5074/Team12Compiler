package parserNodes;
import helpers.SymbolTable;
import java.util.Stack;
import provided.*;

public class ExprNode implements ExprNodeInterface{
    private OperandNode left;
    private OpNode op;
    private OperandNode right;
    private Token toke;
    private boolean onlyID;
    private String type;

    public ExprNode(OperandNode left, OpNode op, OperandNode right)
    {
        this.left = left;
        this.op = op;
        this.right = right;
        toke = null;
        onlyID = false;
    }

    public ExprNode (Token toke) {
        this.toke = toke;
        onlyID = true;
    }

    public static ExprNode parse(Stack<Token> tokens) throws Exception {
        Token toke = tokens.peek();
        if (toke.getTokenType() == TokenType.STRING) {
            tokens.pop();
            return new ExprNode(toke);
        }
        if (toke.getTokenType() == TokenType.ID_KEYWORD && (toke.getToken().equals("True") || toke.getToken().equals("False"))) {
            tokens.pop();
            return new ExprNode(toke);
        }
        try {
            OperandNode l = OperandNode.parse(tokens);
            OpNode opNode = OpNode.parse(tokens);
            OperandNode r = OperandNode.parse(tokens);
            return new ExprNode(l, opNode, r);
        } catch (Exception e) {
            throw new Exception("Syntax error:\nExpected correct Expression \n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
    }

    @Override
    public boolean validateTree() {
        if(onlyID && SymbolTable.varDefined(toke.getToken()))
        {
            type = SymbolTable.getVarType(toke.getToken()).getTypeName();
            return true;
        }
        else {
            if(left.validateTree() && right.validateTree())
            {
                if((left.getExprType().equals("Double") && right.getExprType().equals("Double")))
                {
                    type = "Double";
                    return true;
                }
                else if (left.getExprType().equals("Integer") && right.getExprType().equals("Integer"))
                {
                    type = "Integer";
                    return true;
                }
                else if(left.getExprType().equals("String") && right.getExprType().equals("String"))
                {
                    type = "String";
                    return true;
                }
            }
            System.out.println("Semantic error:\nType mismatch between " + left.getExprType() + " and " + right.getExprType() + "\nLine " + left.getLine());
            return false;
        }
    }

    @Override
    public int getLine() {
        return left.getLine();
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String convertToJott() {
        if (toke != null) {
            return toke.getToken();
        }
        return left.convertToJott() + " " + op.convertToJott() + " " + right.convertToJott();
    }


    @Override
    public String getExprType()
    {
        return type;
    }

    @Override
    public String getFilename() {
        return left.getFilename();
    }
    
}
