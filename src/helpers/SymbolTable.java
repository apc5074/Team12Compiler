package helpers;

import java.util.HashMap;
import java.util.List;


import parserNodes.TypeNode;

public class SymbolTable {

    public class SymTabs {
        public HashMap<String,TypeNode>  vSymTabl;
        public HashMap<String,List<TypeNode>> fSymTabl; 
    }

    public static HashMap<String, SymTabs> SymTbl = new HashMap<>();
    public static String scope;



    public static boolean addFunction(String fName, List<TypeNode> types)
    {
        if (!(SymTbl.get(scope).fSymTabl.get(fName) == null))
        {
            return false;
        }
        else
        {
            SymTbl.get(scope).fSymTabl.put(fName, types);
            return true;
        }
    }

    public static boolean addVar(String vName, TypeNode type)
    {
        if (!(SymTbl.get(scope).vSymTabl.get(vName) == null))
        {
            return false;
        }
        else
        {
            SymTbl.get(scope).vSymTabl.put(vName, type);
            return true;
        }
    }

    public static boolean varDefined(String vName)
    {
        return SymTbl.get(scope).vSymTabl.get(vName) != null;
    }

    public static boolean funcDefined(String fName)
    {
        return SymTbl.get(scope).fSymTabl.get(fName) != null;
    }

    public static TypeNode getFuncReturnType(String fName)
    {
        List<TypeNode> args = SymTbl.get(scope).fSymTabl.get(fName);
        return args.get(args.size()-1);
    }

    public static List<TypeNode> getFuncArgTypes(String fName) {
        List<TypeNode> args = SymTbl.get(scope).fSymTabl.get(fName);
        return args.subList(0, args.size() - 1);
    }

    public static TypeNode getVarType(String vName)
    {
        return SymTbl.get(scope).vSymTabl.get(vName);
    }

    public static void setScope(String scope)
    {
        SymbolTable.scope = scope;
    }

}
