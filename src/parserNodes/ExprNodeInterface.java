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

        Token curToken = tokens.get(0);
        TokenType curTokenType = curToken.getTokenType();
        switch (curTokenType) {
            case TokenType.STRING:
                StringLiteralNode SLN = StringLiteralNode.parse(tokens);
                return SLN;
            case TokenType.ID_KEYWORD:
            case TokenType.FC_HEADER:
            case TokenType.NUMBER:
            case TokenType.MATH_OP:
                


        
            default:
                throw Exception;
            }
    };

    public String convertToJott();

}
