package parserNodes;

import java.util.Stack;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface ExprNodeInterface extends JottTree{

    Exception Exception = null;

    public static ExprNodeInterface parse(Stack<Token> tokens) throws java.lang.Exception{
        if (tokens.empty())
        {
            throw Exception;
        }

        Token curToken = tokens.peek();
        TokenType curTokenType = curToken.getTokenType();
        if (curTokenType == TokenType.STRING)
        {
            StringLiteralNode SLN = StringLiteralNode.parse(tokens);
            return SLN;
        }
        if (curTokenType == TokenType.ID_KEYWORD)
        {
            if (curToken.getToken() == "True" || curToken.getToken() == "False")
            {
                BoolNode BN = BoolNode.parse(tokens);
                return BN;
            }
        }
        OperandNode left = OperandNode.parse(tokens);
        curToken = tokens.peek();
        if (curToken.getTokenType() != TokenType.REL_OP && curToken.getTokenType() != TokenType.MATH_OP)
        {
            return left;
        }
        OpNode op = new OpNode(curToken);
        OperandNode right = OperandNode.parse(tokens);
        return new ExprNode(left, op, right);
    };

    public String convertToJott();

}
