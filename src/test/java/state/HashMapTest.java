package state;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests to verify implementation details of {@link java.util.HashMap} class.
 */
@RunWith(JUnitParamsRunner.class)
public class HashMapTest {

    /**
     * Tests subject
     */
    private Map<String, Integer> wordToNumber;
    private Map<Integer, String> numberToWord;

    /**
     * Creates fresh clean map before each unit test
     */
    @Before
    public void setUp() {
        wordToNumber = new HashMap<>();
        numberToWord = new HashMap<>();
    }

    /**
     * Composes two-dimensional array of key-value pairs used to test map functionality
     *
     * @return two-dimensional array of parameters
     */
    private static Object[][] getStringToIntMapParams() {
        return new Object[][]{
                {"one", 1},
                {"two", 2},
                {"three", 3},
                {"four", 4}
        };
    }

    /**
     * Composes array of key-value pairs
     *
     * @return two-dimensional array
     */
    private static Object[][] getIntToStringMapParams() {
        return new Object[][]{
                {1, "one"},
                {2, "two"},
                {3, "three"},
                {4, "four"}
        };
    }

    /**
     * Composes null-to-value pairs
     *
     * @return two-dimensional array
     */
    private static Object[][] getNullToStringMapParams() {
        return new Object[][]{
                {null, "hell"},
                {null, "heaven"}
        };
    }

    /**
     * Verifies that {@code value} associated with {@code key} can be easily retrieved using
     * the same key
     *
     * @param key   of a map bucket
     * @param value associated with a {@code key}
     */
    @Test
    @Parameters(method = "getStringToIntMapParams")
    public void shouldRetrieveWhatWasPut(String key, Integer value) {
        wordToNumber.put(key, value);
        assertEquals(value, wordToNumber.get(key));
    }

    /**
     * Verifies that subsequent {@code value} associated with the {@code key} should replace already
     * stored, associated with the same {@code key}, value
     *
     * @param key   of a map entry
     * @param value associated with a {@code key}
     */
    @Test
    @Parameters(method = "getStringToIntMapParams")
    public void shouldReplaceExistingValue(String key, Integer value) {
        Integer dummyValue = value * 2;
        wordToNumber.put(key, dummyValue);
        wordToNumber.put(key, value);
        assertEquals(value, wordToNumber.get(key));
    }

    /**
     * Verifies that {@link Map#clear()} method removes all key-value pairs stored in a {@link Map}
     *
     * @param key   of a map entry
     * @param value associated with a {@code key}
     */
    @Test
    @Parameters(method = "getIntToStringMapParams")
    public void shouldClearMap(Integer key, String value) {
        // let's increase number of map entries
        Integer positiveValue = Math.abs(key);
        for (; positiveValue > 0; positiveValue--) {
            numberToWord.put(positiveValue, value);
        }

        numberToWord.clear();
        assertTrue(numberToWord.isEmpty());
    }

    /**
     * Verifies that null values could be used as a {@code key}
     *
     * @param key   of a map entry
     * @param value associated with a {@code key}
     */
    @Test
    @Parameters(method = "getNullToStringMapParams")
    public void shouldAllowUseNullAsAKey(Integer key, String value) {
        assertNull(key);
        numberToWord.put(key, value);
        assertEquals(value, numberToWord.get(key));
    }
}
