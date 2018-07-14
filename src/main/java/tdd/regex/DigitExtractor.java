package tdd.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to extract digits from passed strings
 */
public class DigitExtractor {

    /**
     * Regex pattern to extract exactly three digits groups
     */
    private static final Pattern THREE_DIGIT_REGEX_PATTERN = Pattern.compile("(\\d{3})");

    /**
     * Extract three-digit groups from specified String, concatenating them with comma
     *
     * @param randomString - string to extract digits pairs from
     * @return extracted three-digit groups concatenated with comma
     */
    public static String extractByThree(String randomString) {
        String extractedDigits = null;
        Matcher matcher = THREE_DIGIT_REGEX_PATTERN.matcher(randomString);
        if (matcher.find()) {
            extractedDigits = matcher.group(0);
        }
        return extractedDigits;
    }
}
