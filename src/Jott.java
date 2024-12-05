import java.util.ArrayList;
import provided.*;

public class Jott {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main.java <filename.jott>");
            return;
        }

        ArrayList<Token> tokens = JottTokenizer.tokenize(args[0]);
        if (tokens == null) {
            return;
        }
        JottTree tree = JottParser.parse(tokens);
        if (tree == null) {
            return;
        }
        if(tree.validateTree()) 
        {
            tree.execute();
        }
        // Yay!!!
        // the program exits automatically if it is invalidated so no NEED To check the output!!!

        // now we need to complete the execute phase and EXECUTE it
    }
}