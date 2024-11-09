package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Node;

import parserNodes.TypeNode;

public class SymbolTable {

    public class SymTabs {
        public HashMap<String,TypeNode>  vSymTabl;
        public HashMap<String,List<TypeNode>> fSymTabl; 
    }

    public static HashMap<String, SymTabs> SymTbl = new HashMap<>();
    public static String scope;



    public boolean addFunction(String fName, List<TypeNode> types)
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

    public boolean addVar(String vName, TypeNode type)
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

    public TypeNode getFuncReturnType(String fName)
    {
        return SymTbl.get(scope).fSymTabl.get(fName).getLast();
    }

    public List<TypeNode> getFuncArgTypes(String fName) {
        List<TypeNode> args = SymTbl.get(scope).fSymTabl.get(fName);
        return args.subList(0, args.size() - 1);
    }

    public TypeNode getVarType(String vName)
    {
        return SymTbl.get(scope).vSymTabl.get(vName);
    }

    public void setScope(String scope)
    {
        SymbolTable.scope = scope;
    }

}
