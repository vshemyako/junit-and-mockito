package advanced;

import org.junit.Assert;
import org.junit.Test;

/**
 * Verifies functionality of {@link StringUtils#reverse(String)} method
 */
public class StringUtilsTest {

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
    public void shouldReverseArbitraryString() {
        String reversed = StringUtils.reverse("qwerty");
        Assert.assertEquals("ytrewq", reversed);
    }
}
