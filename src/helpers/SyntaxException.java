package helpers;

public class SyntaxException extends Exception {
    private int lineNumber;
    private String fileName;
    private String reason;

    // Constructor to initialize all fields
    public SyntaxException(int lineNumber, String fileName, String reason) {
        this.lineNumber = lineNumber;
        this.fileName = fileName;
        this.reason = reason;
    }

    // Override toString to provide a detailed error message
    @Override
    public String toString() {
        return "Syntax Error:/n"+ reason + "/n" + fileName +".jott:"+lineNumber;
    }
}

