package state;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Demonstrates possibilities to test exceptional cases which might occur and are expected to occur in developer's code.
 */
@RunWith(JUnitParamsRunner.class)
public class MoneyExceptionThrownTest {

    private static final int VALID_AMOUNT = 33;
    private static final String VALID_CURRENCY = "BYR";

    /**
     * @return array of amount parameters which are expected to result in exception being thrown
     */
    private static Object[] getInvalidAmountParams() {
        return new Object[]{-1, -10, -100};
    }

    /**
     * @return array of currency parameters which are expected to result in exception being thrown
     */
    private static Object[] getInvalidCurrencyParams() {
        return new Object[]{null};
    }

    /**
     * Tests constructor behavior of a {@link Money} class.
     * Some invalid values provided to constructor have to result in {@link IllegalAccessException} being thrown.
     *
     * @param amount of currency to be set
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidAmountParams")
    public void constructorShouldThrowExceptionOnInvalidAmount(int amount) {
        new Money(amount, VALID_CURRENCY);
    }

    /**
     * Tests constructor behavior of a {@link Money} class.
     * Some invalid values of currency are expected to end up with {@link IllegalArgumentException} being thrown.
     *
     * @param currency type of {@link Money} instance
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidCurrencyParams")
    public void constructorShouldThrowExceptionOnInvalidCurrency(String currency) {
        new Money(VALID_AMOUNT, currency);
    }
}