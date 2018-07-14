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

    private static Object[] getThreeDigitsExtractorPairs() {
        return new Object[][]{
                {"345, 678", "cdefg 345 12bbb33 678tt"},
                {"123, 234, 336", "123, 33 } as234... 336asd"}
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

    /**
     * Verifies that any string passed as argument will be converted to comma-whitespace separated list
     * of three-digit pairs
     *
     * @param expectedValue - value expected to convert to
     * @param source        - source string to test
     */
    @Test
    @Parameters(method = "getThreeDigitsExtractorPairs")
    public void shouldExtractAllThreeDigitsSeparatingThemWithComma(String expectedValue, String source) {
        String extractedDigits = DigitExtractor.extractByThree(source);
        Assert.assertEquals(expectedValue, extractedDigits);
    }
}
