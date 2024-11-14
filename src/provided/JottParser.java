package provided;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;

import helpers.SymbolTable;
import parserNodes.ProgramNode;

public class JottParser {
  SymbolTable symblTab = new SymbolTable();


    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     * @throws Exception 
     */
    public static JottTree parse(ArrayList<Token> tokens) {
      //return ProgramNode.parse(tokens);
      try {
        return ProgramNode.parse(tokens);
      } catch (Exception e) {
        System.out.println(e);
        return null;
      }
    }

}
