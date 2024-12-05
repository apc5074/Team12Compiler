package parserNodes;
import java.util.Stack;

import helpers.SemanticException;
import helpers.SymbolTable;
import provided.*;

public class OperandNode implements ExprNodeInterface {

    private Token numToken;
    private static int line;
    private static String file;
    private String filePersonal;
    private int linePersonal;
    private Token idToken;
    private Boolean neg;
    private FuncCallNode funcCall = null;

    public OperandNode(FuncCallNode t) {
        funcCall = t;
        linePersonal = line;
        filePersonal = file;
    }

    public OperandNode(Token id) {
        idToken = id;
        linePersonal = line;
        filePersonal = file;
    }

    public OperandNode(boolean t, Token numToken) {
        neg = t;
        this.numToken = numToken;
        linePersonal = line;
        filePersonal = file;
    }

    public int getLine() {
        return linePersonal;
    }

    public String getFilename()
    {
        return idToken.getFilename();
    }

    public static OperandNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {

            throw new Exception("Syntax error:\nExpected WhileLoopNode but no tokens left");
        }
        Token iToken = tokens.peek();
        line = tokens.peek().getLineNum();
        file = tokens.peek().getFilename();
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
            if(SymbolTable.varDefined(idToken.getToken()))
            {
                return SymbolTable.varDefined(idToken.getToken());
            }
            else {
                SemanticException exception = new SemanticException(linePersonal, filePersonal, "Using undeclared variable.");
                exception.toString();
                return false;
            }
        }
        else if (funcCall != null) {
            if(funcCall.validateTree())
            {
                return true;
            }
            else {
                SemanticException exception = new SemanticException(linePersonal, filePersonal, "Function call not valid.");
                exception.toString();
                return false;
            }
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
            if (numToken.getToken().contains(".")) {
                return "Double";
            } else {
                return "Integer";
            }
        }
        else {
            if (!SymbolTable.funcDefined(funcCall.getFuncName())) {
                return "undefined function";
            }
            return SymbolTable.getFuncReturnType(funcCall.getFuncName()).getTypeName();
        }
    }

    public int getNum() {
        if(funcCall != null)
        {
            // needs to call function and get it 
            return 0;
        }
        else if (idToken != null)
        {
            return Integer.parseInt(idToken.getToken());
        }

        return 0;
    }
}
