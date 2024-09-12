package provided;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.util.ArrayList;
import java.util.Scanner;

import helpers.State;

public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
		File file = new File(filename);
    try (Scanner scanner = new Scanner(file)) {
      int lineNum = 1;
      State state = State.START;
      String curTokenString = "";
      ArrayList<Token> tokens = new ArrayList<Token>();
      while (scanner.hasNextLine())
      {
          String line = scanner.nextLine();
          char[] curChars = line.toCharArray();

          for (int i = 0; i < curChars.length; i++)
          {
              switch (state)
              {
                  case START:
                    char curChar = curChars[i];
                    curTokenString = "";
                    switch (curChar)
                    {
                      case ',':
                        curTokenString+= curChar;
                        tokens.add(new Token(curTokenString, filename, lineNum, TokenType.COMMA));
                        break;
                      case '#':
                        state = State.COMMENT;
                        break;
                      case ']':
                        curTokenString+= curChar;
                        tokens.add(new Token(curTokenString, filename, lineNum, TokenType.R_BRACKET));
                        break;
                      case '[':
                        curTokenString+= curChar;
                        tokens.add(new Token(curTokenString, filename, lineNum, TokenType.L_BRACKET));
                        break;
                      case '}':
                        curTokenString+= curChar;
                        tokens.add(new Token(curTokenString, filename, lineNum, TokenType.R_BRACE));
                        break;
                      case '{':
                        curTokenString+= curChar;
                        tokens.add(new Token(curTokenString, filename, lineNum, TokenType.L_BRACE));
                        break;
                      case '=':
                        state = State.ASSIGN;
                        curTokenString+= curChar;
                        break;
                      case '>':
                      case '<':
                          state = State.relOp;
                          curTokenString+= curChar;
                          break;
                      case '/':
                      case '+':
                      case '-':
                      case '*':
                        curTokenString += curChar;
                        tokens.add(new Token(curTokenString, filename, lineNum, TokenType.MATH_OP));
                        break;
                      case ';':
                        curTokenString+= curChar;
                        tokens.add(new Token(curTokenString, filename, lineNum, TokenType.SEMICOLON));
                        break;
                      case '.':
                        state = State.DOT;
                        curTokenString+= curChar;
                        break;
                      case ':':
                        state = State.COLON;
                        curTokenString+= curChar;
                        break;
                      case '!':
                        state = State.EXPLIMATION;
                        curTokenString+= curChar;
                        break;
                      case '"':
                        state = State.QUOTE;
                        curTokenString+= curChar;
                        break;
                      default:
                        if (Character.isLetter(curChar))
                        {
                          state = State.LETTER;
                          curTokenString+= curChar;
                        }
                        else if (Character.isDigit(curChar))
                        {
                          state = State.NUM;
                          curTokenString+= curChar;
                        }
                        break;
                    }
                      break;
                  case COMMENT:
                    curChar = curChars[i];
                    while (i != curChars.length && curChar != '\n')
                    {
                      i++;
                    }
                    state = State.START;
                    break;
                  case ASSIGN:
                    curChar = curChars[i];
                    if (curChar == '=')
                    {
                      curTokenString+= curChar;
                      tokens.add(new Token(curTokenString, filename, lineNum, TokenType.REL_OP));
                      state = State.START;
                    }
                    else
                    {
                      tokens.add(new Token(curTokenString, filename, lineNum, TokenType.ASSIGN));
                      state = State.START;
                      i--;
                    }
                    break;
                case COLON:
                  curChar = curChars[i];
                  if (curChar == ':')
                  {
                    curTokenString+= curChar;
                    tokens.add(new Token(curTokenString, filename, lineNum, TokenType.FC_HEADER));
                    state = State.START;
                  }
                  else
                  {
                    tokens.add(new Token(curTokenString, filename, lineNum, TokenType.COLON));
                    state = State.START;
                    i--;
                  }
                  break;
                case NUM:
                  curChar = curChars[i];
                  if (Character.isDigit(curChar))
                  {
                    curTokenString+= curChar;
                    state = State.NUM;
                  } else if (curChar == '.') {
                    curTokenString+= curChar;
                    state = State.DIGIT;
                  }
                  else
                  {
                    tokens.add(new Token(curTokenString, filename, lineNum, TokenType.NUMBER));
                    state = State.START;
                    i--;
                  }
                  break;
                case DIGIT:
                  curChar = curChars[i];
                  if (Character.isDigit(curChar))
                  {
                    curTokenString+= curChar;
                    state = State.DIGIT;
                  }
                  else
                  {
                    tokens.add(new Token(curTokenString, filename, lineNum, TokenType.NUMBER));
                    state = State.START;
                    i--;
                  }
                  break;
                case DOT:
                    curChar = curChars[i];
                    if (Character.isDigit(curChar))
                    {
                      curTokenString+= curChar;
                      state = State.DIGIT;
                    }
                    else
                    {
                      System.err.println("Error: unexpected character " + curChar + " in line " + lineNum);
                      return null;
                    }                    
                  break;
                case EXPLIMATION:
                  curChar = curChars[i];

                  if (curChar == '=')
                  {
                    curTokenString+= curChar;
                    tokens.add(new Token(curTokenString, filename, lineNum, TokenType.REL_OP));
                    state = State.START;
                  }
                  else
                  {
                    System.err.println(curTokenString + " is not an operator in line " + lineNum);
                    return null;
                  }
                  break;
                case LETTER:
                  curChar = curChars[i];
                  if (Character.isLetter(curChar) || Character.isDigit(curChar))
                  {
                    curTokenString+= curChar;
                    state = State.LETTER;
                  }
                  else
                  {
                    tokens.add(new Token(curTokenString, filename, lineNum, TokenType.ID_KEYWORD));
                    state = State.START;
                    i--;
                  }
                  break;
                case QUOTE:
                  curChar = curChars[i];
                  if (Character.isLetter(curChar) || Character.isDigit(curChar) || curChar == ' ')
                  {
                    curTokenString+= curChar;
                    state = State.QUOTE;
                  }
                  else if (curChar == '"')
                  {
                    curTokenString+= curChar;
                    tokens.add(new Token(curTokenString, filename, lineNum, TokenType.STRING));
                    state = State.START;
                  }
                  else
                  {
                    System.err.println("Error: unexpected character " + curChar + " in line " + lineNum);
                    return null;
                  }
                  if (i == curChars.length - 1 && curChar != '"') {
                    System.err.println("Error: missing closing quote for string starting at line " + lineNum);
                    return null;
                 } 
                  break;
                case relOp:
                  curChar = curChars[i];
                    if (curChar == '=')
                    {
                      curTokenString+= curChar;
                      tokens.add(new Token(curTokenString, filename, lineNum, TokenType.REL_OP));
                      state = State.START;
                    }
                    else
                    {
                      tokens.add(new Token(curTokenString, filename, lineNum, TokenType.REL_OP));
                      state = State.START;
                      i--;
                    }
                  break;
                default:
                  break;
              }
          }
          lineNum++;    
      }
      lineNum--;
      switch (state) {
        case START:
          break;
        case ASSIGN:
          tokens.add(new Token(curTokenString, filename, lineNum, TokenType.ASSIGN));
          break;
        case relOp:
          tokens.add(new Token(curTokenString, filename, lineNum, TokenType.REL_OP));
          break;
        case LETTER:
          tokens.add(new Token(curTokenString, filename, lineNum, TokenType.ID_KEYWORD));
          break;
        case NUM:
        case DIGIT:
          tokens.add(new Token(curTokenString, filename, lineNum, TokenType.NUMBER));
          break;
        case COLON:
          tokens.add(new Token(curTokenString, filename, lineNum, TokenType.COLON)); 
          break;
        default:
          return null;
      }
      scanner.close();
      return tokens;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
}