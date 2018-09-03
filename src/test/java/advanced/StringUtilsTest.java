package advanced;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Verifies functionality of {@link StringUtils#reverse(String)} method
 */
@RunWith(JUnitParamsRunner.class)
public class StringUtilsTest {

    private Object[][] getOriginalReversedStringPairs() {
        return new Object[][]{
                {"123", "321"},
                {"qwerty", "ytrewq"},
                {"Grodno", "ondorG"},
                {"goog", "goog"}
        };
    }

    /**
     * Verifies that {@code null} value provided to {@link StringUtils#reverse(String)} method
     * will result in exception being thrown
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullParam() {
        StringUtils.reverse(null);
    }

    /**
     * Verifies that empty string passed for reversion will be properly reversed
     */
    @Test
    public void shouldReverseEmptyString() {
        String reversed = StringUtils.reverse("");
        Assert.assertEquals("", reversed);
    }

    /**
     * Verifies that arbitrary string will be properly reversed
     */
    @Test
    @Parameters(method = "getOriginalReversedStringPairs")
    public void shouldReverseArbitraryString(String original, String reversed) {
        Assert.assertEquals(reversed, StringUtils.reverse(original));
    }
}
