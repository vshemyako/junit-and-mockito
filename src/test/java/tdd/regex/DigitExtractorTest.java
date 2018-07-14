package tdd.regex;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests functionality of {@link DigitExtractor} class, specifically extraction of three-digits patterns
 */
@RunWith(JUnitParamsRunner.class)
public class DigitExtractorTest {

    private static final String THREE_DIGITS = "123";

    private static Object[] getStringsWithEmbeddedThreeDigitGroup() {
        return new Object[]{
                String.format("xyz%s", THREE_DIGITS),
                String.format("%sxyz", THREE_DIGITS),
                String.format("xyz%sxyz", THREE_DIGITS),
        };
    }

    /**
     * Verifies that {@link DigitExtractor} extracts exactly the same three digits which were passed for extraction
     */
    @Test
    public void shouldReturnTheSameThreeDigit() {
        String extractedDigits = DigitExtractor.extractByThree(THREE_DIGITS);
        Assert.assertEquals(THREE_DIGITS, extractedDigits);
    }

    /**
     * Verifies that {@link DigitExtractor} correctly extracts embedded three-digit pair
     */
    @Test
    @Parameters(method = "getStringsWithEmbeddedThreeDigitGroup")
    public void shouldExtractThreeEmbeddedDigitsFromAString(String embeddedDigitGroup) {
        String extractedDigits = DigitExtractor.extractByThree(embeddedDigitGroup);
        Assert.assertEquals(THREE_DIGITS, extractedDigits);
    }
}
