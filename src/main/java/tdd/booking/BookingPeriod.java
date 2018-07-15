package tdd.booking;

import java.time.LocalTime;

/**
 * Defined a period within a day
 */
public class BookingPeriod {
    private final LocalTime from;
    private final LocalTime to;

    /**
     * Creates a period of time within a day
     *
     * @param from - initial period of time
     * @param to   - ending period of time
     */
    public BookingPeriod(LocalTime from, LocalTime to) {
        if (from == null) {
            throw new IllegalArgumentException("From point of time must not be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("To point of time must not be null");
        }

        // verifies that passed arguments are regular whole hours
        if (from.getMinute() > 0 || from.getSecond() > 0 || from.getNano() > 0) {
            throw new IllegalArgumentException("From point of time is not a regular whole hour");
        }
        if (to.getMinute() > 0 || to.getSecond() > 0 || to.getNano() > 0) {
            throw new IllegalArgumentException("To point of time is not a regular whole hour");
        }

        this.from = from;
        this.to = to;
    }

    /**
     * @return initial period of time related to this period
     */
    public LocalTime getFrom() {
        return this.from;
    }

    /**
     * @return ending period of time related to this period
     */
    public LocalTime getTo() {
        return this.to;
    }

    /**
     * Calculates hashCode value of this object
     *
     * @return calculated value
     */
    @Override
    public int hashCode() {
        return this.getFrom().hashCode() + this.getTo().hashCode();
    }

    /**
     * Compares this object with another
     *
     * @param another - object to compare for equality with this object
     * @return true in case objects are the same
     */
    @Override
    public boolean equals(Object another) {
        // cheap comparison
        if (this == another) {
            return true;
        }
        if ((another instanceof BookingPeriod)) {
            BookingPeriod anotherPeriod = (BookingPeriod) another;
            return this.getFrom().equals(anotherPeriod.getFrom())
                    && this.getTo().equals(anotherPeriod.getTo());
        }
        return false;
    }
}
