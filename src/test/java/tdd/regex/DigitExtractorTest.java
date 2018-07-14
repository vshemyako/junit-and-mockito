package tdd.regex;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests functionality of {@link DigitExtractor} class, specifically extraction of three-digits patterns
 */
public class DigitExtractorTest {

    private static final String THREE_DIGITS = "123";
    private static final String EMBEDDED_THREE_DIGITS = "qwe123rty";

    /**
     * Verifies that {@link DigitExtractor} extracts exactly the same three digits which were passed for extraction
     */
    @Test
    public void shouldReturnTheSameThreeDigit() {
        String extractedDigits = DigitExtractor.extractByThree(THREE_DIGITS);
        Assert.assertEquals(THREE_DIGITS, extractedDigits);
    }

    /**
     *
     */
    @Test
    public void shouldExtractThreeEmbeddedDigitsFromAString() {
        String extractedDigits = DigitExtractor.extractByThree(EMBEDDED_THREE_DIGITS);
        Assert.assertEquals(THREE_DIGITS, extractedDigits);
    }
}
