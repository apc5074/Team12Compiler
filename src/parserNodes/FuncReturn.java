package parserNodes;
import provided.*;
import java.util.Stack;

import helpers.SymbolTable;

public class FuncReturn implements JottTree {
    Token toke;
    boolean voided;
    Object result;

    public FuncReturn() {
        voided = true;
    }

    public FuncReturn(Token t) {
        toke = t;
    }

    public FuncReturn parse(Stack<Token> tokens) throws Exception {
        Token next = tokens.peek();
        if (tokens.size() == 0 && next.getTokenType() == TokenType.ID_KEYWORD) {
            String t = next.getToken();
            if (t == "Double" || t == "Integer" || t == "String" || t == "Boolean") {
                return new FuncReturn(tokens.pop());
            } else {
                throw new Exception("Syntax error:\nExpected correct type but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
            }
        } else {
            if (tokens.size() == 0 && tokens.peek().getTokenType() != TokenType.L_BRACE) {
                return new FuncReturn();
            } else {
                throw new Exception("Syntax error:\nExpected no tokens \n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
            }
        }
    }

    @Override
    public String convertToJott() {
        if (voided) {
            return ("Void");
        } else {
            return toke.getToken();
        }
    }

    @Override
    public boolean validateTree() {
        return true;
    }

    @Override
    public void execute() {
        
    }

    public Object getValue() {
        result = SymbolTable.getVarVal(toke.getToken());
        return result;
    }
}
