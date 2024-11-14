import provided.*;
import parserNodes.*;
import java.util.ArrayList;

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
        if (!tree.validateTree()) {
            return;
        }
        // Yay!!!

        // now we need to complete the execute phase and EXECUTE it
        // tree.execute();
    }
}