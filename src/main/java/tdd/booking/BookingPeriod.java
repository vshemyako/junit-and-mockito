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
}
