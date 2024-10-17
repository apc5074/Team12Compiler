package provided;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.*;
import java.util.ArrayList;
import provided.Token;
import provided.TokenType;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/
public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
      ArrayList<Token> tokens = new ArrayList<>();
      int currentChar;
      int lineNumber = 1;
      StringBuilder tokenBuffer = new StringBuilder();
      TokenType currentTokenType = null;

      try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
		return null;
	}
}