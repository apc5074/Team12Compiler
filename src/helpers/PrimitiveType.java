package helpers;

public class PrimitiveType<T> {
    private T value;

    // Constructor
    public PrimitiveType(T value) {
        if (value instanceof String || value instanceof Double ||
            value instanceof Integer || value instanceof Boolean) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Unsupported type. Only String, Double, Integer, or Boolean are allowed.");
        }
    }

    // Method to get the value
    public T getValue() {
        return value;
    }
}
