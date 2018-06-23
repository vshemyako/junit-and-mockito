package state;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * {@link MoneyTest} class has a big drawback by providing hardcoded values within testing method
 * {@link MoneyTest#constructorShouldInitializeFields()}. There's a much more preferred way provided by separate
 * 'JUnitParams' library. Simple usage of it is presented by this class.
 */
@RunWith(JUnitParamsRunner.class)
public class MoneyParameterizedTest {

    /**
     * @return array of parameters for parameterized unit test
     */
    private static Object[] getMoneyParams() {
        return new Object[][]{
                {10, "PLZ"},
                {20, "BYR"}
        };
    }

    /**
     * Verifies that all fields of a {@link Money} instance are initialized
     * with the values provided to constructor
     *
     * @param amount   of currency to by set
     * @param currency type of {@link Money} instance
     */
    @Test
    @Parameters(method = "getMoneyParams")
    public void constructorShouldInitializeFields(int amount, String currency) {
        // initialize
        Money money = new Money(amount, currency);

        // verify
        assertEquals(amount, money.getAmount());
        assertEquals(currency, money.getCurrency());
    }
}
