package state;

import java.util.Objects;

/**
 * In unit testing world {@link Money} class is counterpart of 'HelloWorld' introductory
 * class for the majority of programming languages
 */
public class Money {
    private final int amount;
    private final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * @return amount of stored {@code currency}
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return type of {@code currency}
     */
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof Money) {
            Money money = (Money) another;
            return Objects.equals(money.getCurrency(), this.getCurrency())
                    && money.getAmount() == this.getAmount();
        }
        return false;
    }
}
