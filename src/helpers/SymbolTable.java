package helpers;

import java.util.HashMap;
import java.util.List;


import parserNodes.TypeNode;

public class SymbolTable {



    public static HashMap<String, HashMap<String,TypeNode>> vSymTbl = new HashMap<>();
    public static HashMap<String,List<TypeNode>> fSymTbl = new HashMap<>();
    public static String scope;



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
        if (!(vSymTbl.get(scope).get(vName) == null))
        {
            return false;
        }
        else
        {
            vSymTbl.get(scope).put(vName, type);
            return true;
        }
    }

    public static boolean varDefined(String vName)
    {
        return vSymTbl.get(scope).get(vName) != null;
    }

    public static boolean funcDefined(String fName)
    {
        return fSymTbl.get(fName) != null;
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
    }

}
