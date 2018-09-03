package advanced;

/**
 * Offers some utility functionality to work with strings
 */
public final class StringUtils {

    /**
     * Restrict creation of instances
     */
    private StringUtils() {
    }

    public static String reverse(String original) {
        if (original == null) {
            throw new IllegalArgumentException("Provided value must not be null");
        }
        if (original.isEmpty()) {
            return original;
        }

        StringBuilder reverser = new StringBuilder(original.length());
        for (int index = original.length() - 1; index >= 0; index--) {
            reverser.append(original.charAt(index));
        }
        return reverser.toString();
    }
}
