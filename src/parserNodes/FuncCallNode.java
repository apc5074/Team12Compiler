package parserNodes;
import java.util.ArrayList;
import java.util.Stack;

import helpers.SymbolTable;
import provided.*;

public class FuncCallNode implements BodyStatementNodeInterface {

    private IdNode id;
    private ParamNode args;

    public FuncCallNode(IdNode idNode, ParamNode params){
        id = idNode;
        args = params;
    }

    public String getFuncName()
    {
        return id.convertToJott();
    }

    public static FuncCallNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            throw new Exception("Syntax error:\nExpected FuncCallNode but no tokens left");
        }
        Token toke = tokens.peek();
        if (toke.getTokenType() != TokenType.FC_HEADER) {
            throw new Exception("Syntax error:\nExpected :: but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();

        // parses the function name
        IdNode id = IdNode.parse(tokens);

        // checks for L_bracket (parameters begin after)
        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.L_BRACKET) {
            throw new Exception("Syntax error:\nExpected Left Bracket but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();

        // loads a list of parameters.
        ParamNode args = ParamNode.parse(tokens);

        toke = tokens.peek();
        if (toke.getTokenType() != TokenType.R_BRACKET) {
            throw new Exception("Syntax error:\nExpected Right Bracket but got "+tokens.peek().getTokenType() + "\n" + tokens.peek().getFilename() + ".jott:" + tokens.peek().getLineNum());
        }
        tokens.pop();
        /*toke = tokens.peek();
        if (!toke.getToken().equals(";"))
        {
            throw new Exception("Syntax Error\nLine does not end in ; at\n" + toke.getLineNum());
        }
        tokens.pop();*/
        return new FuncCallNode(id, args);
    }

    @Override
    public String convertToJott() {
        return ("::" + id.convertToJott() + " [ " + args.convertToJott() + " ]");
    }

    @Override
    public boolean validateTree() {
        if(SymbolTable.funcDefined(id.getIdToken().getToken()))
        {
            if (id.getIdToken().getToken().equals("print"))
            {
                return args.validateTree();
            }
            SymbolTable.setScope(id.getIdToken().getToken());
            ArrayList<String> types = args.getArgTypes();
            if (types.isEmpty()) {
                if (SymbolTable.getFuncArgTypes(id.getIdToken().getToken()).isEmpty() )
                {
                    return true;
                } else {
                    System.out.println("Semantic error:\nFunction call mismatch.\nLine " + id.getLine());
                    return false;
                }
            }
            if(types.equals(SymbolTable.getFuncArgTypes(id.getIdToken().getToken())))
            {
                return (id.validateTree() && args.validateTree());
            }
        }
        System.out.println("Semantic error:\nFunction " + id.convertToJott() + " not declared\nLine " + id.getLine());
        return false;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
