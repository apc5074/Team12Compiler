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



    public boolean addFunction(String fName, List<TypeNode> types)
    {
        return false;
    }

    public boolean addVar(String vName, TypeNode type)
    {
        return true;
    }

    public TypeNode getFuncReturnType(String fName)
    {
        return null;
    }

    public List<TypeNode> getFuncArgTypes(String fName)
    {
        return null;
    }

    public TypeNode getVarType(String vName)
    {
        return null;
    }

}
