package state;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests functionality of {@link StringReverser} class.
 */
@RunWith(JUnitParamsRunner.class)
public class StringReverserTest {

    /**
     * @return array of invalid parameters which are expected to result in exception being thrown
     */
    private static Object[] getInvalidStringParams() {
        return new Object[]{null};
    }

    /**
     * @return array of valid parameters to pass to {@link StringReverser} methods
     */
    private static Object[] getValidStringParams() {
        return new Object[]{"", "word", "string", "resurrection"};
    }

    /**
     * Tests verification of argument which are passed to {@link StringReverser#reverse(String)} method
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidStringParams")
    public void shouldThrowExceptionOnInvalidArgument(String word) {
        StringReverser.reverse(word);
    }

    /**
     * Tests reversing functionality of {@link StringReverser#reverse(String)} method
     */
    @Test
    @Parameters(method = "getValidStringParams")
    public void shouldReverseString(String word) {
        String reversedWord = new StringBuilder(word).reverse().toString();
        Assert.assertEquals(reversedWord, StringReverser.reverse(word));
    }
}
