package parserNodes;

import java.util.Stack;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface BodyStatementNodeInterface extends JottTree{

    Exception Exception = null;

    public static BodyStatementNodeInterface parse(Stack<Token> tokens) throws java.lang.Exception{
        if (tokens.empty())
        {
            throw Exception;
        }

        Token curToken = tokens.peek();
        TokenType curTokenType = curToken.getTokenType();
        if (curTokenType == TokenType.ID_KEYWORD)
        {
            if (curToken.getToken().equals("if"))
            {
                IfNode IN = IfNode.parse(tokens);
                return IN;
            }
            else if (curToken.getToken().equals("while"))
            {
                WhileLoopNode WN = WhileLoopNode.ParseWhileLoopNode(tokens);
                return WN;
            }
            else {

            }
        }
        else  {
            FuncCallNode FN = FuncCallNode.parseFunctionCallNode(tokens);
            return FN
        }
    };

    public String convertToJott();

}