package state;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of a {@link Money} class and its functionality
 */
public class MoneyTest {

    /**
     * Verifies that all fields of a {@link Money} instance are initialized
     * with the values provided to constructor
     */
    @Test
    public void constructorShouldInitializeFields() {
        int amount = 25;
        String currency = "BYR";

        // initialize
        Money money = new Money(amount, currency);

        // verify
        assertEquals(amount, money.getAmount());
        assertEquals(currency, money.getCurrency());
    }
}
