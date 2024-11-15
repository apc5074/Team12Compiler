package helpers;

public class SemanticException extends Exception {
    private int lineNumber;
    private String fileName;
    private String reason;

    // Constructor to initialize all fields
    public SemanticException(int lineNumber, String fileName, String reason) {
        this.lineNumber = lineNumber;
        this.fileName = fileName;
        this.reason = reason;
    }

    public SemanticException(String reason)
    {
        this.reason = reason;
    }

    // Override toString to provide a detailed error message
    @Override
    public String toString() {
        if(fileName == null)
        {
            return "Semantic Error:\n"+ reason + "\n";
        }
        else {
            return "Semantic Error:\n"+ reason + "\n" + fileName +":"+lineNumber;
        }
    }
}

