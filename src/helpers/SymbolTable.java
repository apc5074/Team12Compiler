package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import parserNodes.TypeNode;
import provided.Token;
import provided.TokenType;

public class SymbolTable {


    // stored as a dict in a dict: (fname, (vname, type)
    public static HashMap<String, HashMap<String,TypeNode>> vSymTbl = new HashMap<>();
    // stored as a dict in a dict: (fname, (vname, *VALUE*))
    public static HashMap<String, HashMap<String, FunctionVariable>> vValTable = new HashMap<>();
    // stored as a single dict: (fname, [param1, param2, ..., parami, return])
    public static HashMap<String,List<TypeNode>> fSymTbl = new HashMap<>();
    public static String scope;
    public static Stack<String> scopeStack = new Stack<>();

    public static void addPrimativeFunctions()
    {
        fSymTbl.put("print", null);

        ArrayList<TypeNode> concatTypes = new ArrayList<>();
        concatTypes.add(new TypeNode(new Token("String", "Built-in", 0, TokenType.STRING)));
        concatTypes.add(new TypeNode(new Token("String", "Built-in", 0, TokenType.STRING)));
        concatTypes.add(new TypeNode(new Token("String", "Built-in", 0, TokenType.STRING)));
        fSymTbl.put("concat", concatTypes);

        ArrayList<TypeNode> lengthTypes = new ArrayList<>();
        lengthTypes.add(new TypeNode(new Token("String", "Built-in", 0, TokenType.STRING)));
        lengthTypes.add(new TypeNode(new Token("Integer", "Built-in", 0, TokenType.NUMBER)));
        fSymTbl.put("length", lengthTypes);
    }

    public static boolean addScope(String name)
    {
        if (vSymTbl.containsKey(name))
        {
            return false;
        }
        vSymTbl.put(name, new HashMap<>());
        return true;
    }
    
    public static boolean addFunction(String fName, List<TypeNode> types)
    {
        if (fSymTbl.containsKey(fName))
        {
            return false;
        }
        else
        {
            fSymTbl.put(fName,types);
            return true;
        }
    }

    public static boolean addVar(String vName, TypeNode type)
    {
        if (vSymTbl.containsKey(scope) && vSymTbl.get(scope).containsKey(vName))
        {
            return false;
        }
        else
        {
            if (!vSymTbl.containsKey(scope)) {
                return false;
            }
            vSymTbl.get(scope).put(vName, type);
            return true;
        }
    }

    public static boolean varDefined(String vName)
    {
        return vSymTbl.containsKey(scope);
    }
    
    // testing function
    public static void printAllFunctions() {
        for (String i:fSymTbl.keySet()) {
            System.out.println(i);
        }
    }

    public static boolean funcDefined(String fName)
    {
        return fSymTbl.containsKey(fName);
    }

    public static TypeNode getFuncReturnType(String fName)
    {
        List<TypeNode> args = fSymTbl.get(fName);
        return args.get(args.size()-1);
    }

    public static List<TypeNode> getFuncArgTypes(String fName) {
        List<TypeNode> args = fSymTbl.get(fName);
        return args.subList(0, args.size() - 1);
    }

    public static TypeNode getVarType(String vName)
    {
        return vSymTbl.get(scope).get(vName);
    }

    public static void setScope(String scope)
    {
        SymbolTable.scope = scope;
        scopeStack.add(scope);
    }

    public static void exitScope()
    {
        scopeStack.pop();
        String scope = scopeStack.peek();
        SymbolTable.scope = scope;
    }

}
