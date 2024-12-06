package parserNodes;
import helpers.SemanticException;
import helpers.SymbolTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import provided.*;

public class FuncCallNode implements BodyStatementNodeInterface {

    private IdNode id;
    private ParamNode args;
    private Object returnValue;

    public FuncCallNode(IdNode idNode, ParamNode params){
        id = idNode;
        args = params;
    }

    @Override
    public boolean isIf() {
        return false;
    }
    @Override
    public boolean ifReturn() {
        return false;
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
            ArrayList<String> types = args.getArgTypes();
            SymbolTable.setScope(id.getIdToken().getToken());
            if (types.isEmpty()) {
                if (SymbolTable.getFuncArgTypes(id.getIdToken().getToken()).isEmpty() )
                {
                    SymbolTable.exitScope();
                    return true;
                } else {
                    SemanticException e = new SemanticException(id.getLine(), ProgramNode.filename, "Function call mismatch.");
                    System.err.println(e.toString());
                    return false;
                }
            }
            List<TypeNode> blahblahblah = SymbolTable.getFuncArgTypes(id.getIdToken().getToken());
            if (blahblahblah.size() != types.size()){
                SemanticException e = new SemanticException(id.getLine(), ProgramNode.filename, "Function " +
                    id.convertToJott() + " called with incorrect number of arguments.");
                    System.err.println(e.toString());
                    return false;
            }
            for (int i = 0; i < types.size(); i++)
            {
                if (!types.get(i).equals(blahblahblah.get(i).getTypeName()))
                {
                    SemanticException e = new SemanticException(id.getLine(), ProgramNode.filename, "Function " +
                    id.convertToJott() + " called with incorrect variable types.");
                    System.err.println(e.toString());
                    return false;
                }
            }
                boolean ah = (id.validateTree() && args.validateTree());
                SymbolTable.exitScope();
                return ah;
        }
        SemanticException e = new SemanticException(id.getLine(), ProgramNode.filename, "Function " + id.convertToJott() + " not declared.");
        System.err.println(e.toString());
        return false;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    @Override
    public void execute() {
        args.execute();
        ArrayList<Object> vals = args.getArgValues();
        SymbolTable.setScope(getFuncName());
        FuncDefNode calledFunc = SymbolTable.getfuncDef(this.getFuncName());
        FuncDefParams prms = calledFunc.getParams();
        List<TypeNode> tps = prms.getList();
        ArrayList<String> names = prms.getFuncParamNames();
        for (int i = 0; i < tps.size(); i++)
        {
            SymbolTable.addVarVal(names.get(i), tps.get(i));
            SymbolTable.updateVarVal(names.get(i), String.valueOf(vals.get(i))); // Aidan may be dumb
        }

        switch (id.getIdToken().getToken())
        {
            case "print": 
                System.out.println(vals.get(0)); break;
            case "concat":
                this.returnValue = String.valueOf(vals.get(0)) + String.valueOf(vals.get(1)); break;
            case "length": 
                this.returnValue = String.valueOf(vals.get(0)).length(); break;
            default:
                calledFunc.execute();
                this.returnValue = calledFunc.getValue();
            
        }
        SymbolTable.exitScope();
    }
}
