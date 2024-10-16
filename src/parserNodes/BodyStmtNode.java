package parserNodes;
import provided.*;
import java.util.Stack;

public class BodyStmtNode implements JottTree {
    private static final Exception Exception = null;

    private JottTree bodyStmt; // This will hold any of the four possible node types (if_stmt, while_loop, etc.)

    public BodyStmtNode(JottTree bodyStmt) {
        this.bodyStmt = bodyStmt;
    }

    public static BodyStmtNode parse(Stack<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            throw new Exception("Unexpected end of tokens");
        }

        Token currentToken = tokens.peek(); // Peek at the current token to determine the type of body statement

        JottTree node;

        // Checking for the different possible statements based on the grammar
        if (currentToken.getTokenType().equals("ID_KEYWORD")) {
            String keyword = currentToken.getToken();

            if (keyword.equals("If")) {
                // Parse as if_stmt
                node = IfStmtNode.parse(tokens);
            } else if (keyword.equals("While")) {
                // Parse as while_loop
                node = WhileLoopNode.parse(tokens);
            } else { // might need other check
                // Parse as an assignment
                node = AsmtNode.parse(tokens);
            }

        } else if (currentToken.getTokenType().equals("COLON")) {
            // Parse as a function call (based on COLON being the start of func_call)
            node = FuncCallNode.parse(tokens);
        } else {
            throw new Exception("Unexpected token: " + currentToken.getTokenValue());
        }

        // Return a new BodyStmtNode with the parsed node inside
        return new BodyStmtNode(node);
    }


    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJott'");
    }


    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }}
