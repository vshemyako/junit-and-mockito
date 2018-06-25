package state;

import java.util.ArrayList;
import java.util.List;

/**
 * Util class which provides functionality for string reversion
 */
public class StringReverser {

    /**
     * Reverses {@code word} provided string
     */
    public static String reverse(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Provided string must not be null");
        }

        List<String> tempArray = new ArrayList<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            tempArray.add(word.substring(i, i + 1));
        }
        StringBuilder reversedString = new StringBuilder(word.length());
        for (int i = tempArray.size() - 1; i >= 0; i--) {
            reversedString.append(tempArray.get(i));
        }
        return reversedString.toString();
    }
}
