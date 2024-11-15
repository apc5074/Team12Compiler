package parserNodes;
import java.util.Stack;

import helpers.SymbolTable;
import provided.*;

public class OperandNode implements ExprNodeInterface {

    private Token numToken;
    private static int line;
    private int linePersonal;
    private Token idToken;
    private Boolean neg;
    private FuncCallNode funcCall = null;

    public OperandNode(FuncCallNode t) {
        funcCall = t;
        linePersonal = line;
    }

    public OperandNode(Token id) {
        idToken = id;
        linePersonal = line;
    }

    public OperandNode(boolean t, Token numToken) {
        neg = t;
        this.numToken = numToken;
        linePersonal = line;
    }

    public int getLine() {
        return linePersonal;
    }

    public static OperandNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {

            throw new Exception("Syntax error:\nExpected WhileLoopNode but no tokens left");
        }
        Token iToken = tokens.peek();
        line = tokens.peek().getLineNum();
        if (iToken.getTokenType() == TokenType.FC_HEADER) {
            FuncCallNode node = FuncCallNode.parse(tokens);
            return new OperandNode(node);
        } else if (iToken.getTokenType() == TokenType.MATH_OP && iToken.getToken().equals("-")) {
            tokens.pop();
            if (tokens.peek().getTokenType() == TokenType.NUMBER) {
                return new OperandNode(true, tokens.pop());
            }
        } else if (iToken.getTokenType() == TokenType.NUMBER) {
            return new OperandNode(false, tokens.pop());
        } else if (iToken.getTokenType() == TokenType.ID_KEYWORD) {
            return new OperandNode(tokens.pop());
        }
        throw new Exception("Syntax error:\nExpected correct operand type but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        if (neg != null) {
            if (neg) {
                return "- " + numToken.getToken();
            }
            return numToken.getToken();
        }
        if (funcCall != null) {
            return funcCall.convertToJott();
        }
        return idToken.getToken();
    }

    @Override
    public boolean validateTree() {
        if(idToken != null)
        {
            return SymbolTable.varDefined(idToken.getToken());
        }
        else if (funcCall != null) {
            return funcCall.validateTree();
        }
        else {
            return true;
        }
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String getExprType()
    {
        if(idToken != null)
        {
            return SymbolTable.getVarType(idToken.getToken()).getTypeName();
        }
        else if (numToken != null)
        {
            if(Double.parseDouble(numToken.getToken()) % 1 != 0)
            {
                return "Double";
            }
            else {
                return "Integer";
            }
        }
        else {
            return SymbolTable.getVarType(funcCall.getFuncName()).getTypeName();
        }
    }
}
