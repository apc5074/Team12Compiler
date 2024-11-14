package helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import parserNodes.TypeNode;

public class SymbolTable {


    // stored as a dict in a dict: (fname, (vname, type)
    public static HashMap<String, HashMap<String,TypeNode>> vSymTbl = new HashMap<>();
    // stored as a single dict: (fname, [param1, param2, ..., parami, return])
    public static HashMap<String,List<TypeNode>> fSymTbl = new HashMap<>();
    public static String scope;
    public static Stack<String> scopeStack = new Stack<>();



    public static boolean addScope(String name)
    {
        if (vSymTbl.get(name)!= null)
        {
            return false;
        }
        vSymTbl.put(name, new HashMap<>());
        return true;
    }
    
    public static boolean addFunction(String fName, List<TypeNode> types)
    {
        if (!(fSymTbl.get(fName) == null))
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

    public static boolean funcDefined(String fName)
    {
        return fSymTbl.containsKey(scope);
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
        String scope = scopeStack.pop();
        SymbolTable.scope = scope;
    }

}
