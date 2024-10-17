package parserNodes;
import java.util.Stack;
import provided.*;

public class OperandNode implements ExprNodeInterface {
    private static final Exception Exception = null;

    private Token numToken;
    private Token idToken;
    private Boolean neg;
    private FuncCallNode funcCall;

    public OperandNode(FuncCallNode t) {
        funcCall = t;
    }

    public OperandNode(Token id) {
        idToken = id;
    }


    public OperandNode(boolean t, Token numToken) {
        neg = t;
        this.numToken = numToken;
    }

    public static OperandNode parse(Stack<Token> tokens) throws Exception
    {
        if (tokens.empty())
        {
            throw new Exception("Syntax error:\nExpected WhileLoopNode but no tokens left");
        }
        Token iToken = tokens.peek();

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
