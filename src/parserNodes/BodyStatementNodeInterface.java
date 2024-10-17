package parserNodes;

import java.util.Stack;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface BodyStatementNodeInterface extends JottTree{

    public static BodyStatementNodeInterface parse(Stack<Token> tokens) throws Exception{
        if (tokens.empty())
        {
            throw new Exception("Syntax error:\nExpected BodyStatementNode but no tokens left");
        }

        Token curToken = tokens.peek();
        TokenType curTokenType = curToken.getTokenType();
        if (curTokenType == TokenType.ID_KEYWORD)
        {
            if (curToken.getToken().equals("If"))
            {
                IfNode IN = IfNode.parse(tokens);
                return IN;
            }
            else if (curToken.getToken().equals("While"))
            {
                WhileLoopNode WN = WhileLoopNode.parse(tokens);
                return WN;
            }
            else {
                AsmtNode AN = AsmtNode.parse(tokens);
                return AN;
            }
        }
        else  {
            FuncCallNode FN = FuncCallNode.parse(tokens);
            if (tokens.peek().getTokenType() == TokenType.SEMICOLON) {
                tokens.pop();
                return FN;
            } else {
                throw new Exception ("Syntax Error: \n Missing semicolon at line\n" + tokens.peek().getLineNum());
            }
        }
    };

    public String convertToJott();

}
