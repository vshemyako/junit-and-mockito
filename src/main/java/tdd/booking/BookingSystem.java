package tdd.booking;

import java.util.HashSet;
import java.util.Set;

/**
 * Offers booking several hours for defined booking target
 */
public class BookingSystem {

    private final String bookingTarget;
    private final Set<BookingPeriod> bookedPeriods;

    /**
     * Initializes instance of {@link BookingSystem} class with {@code bookingTarget}
     *
     * @param bookingTarget - subject of booking system
     */
    public BookingSystem(String bookingTarget) {
        if (bookingTarget == null) {
            throw new IllegalArgumentException("Booking target must not be null");
        }
        if (bookingTarget.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking target must not be blank");
        }

        this.bookingTarget = bookingTarget;
        this.bookedPeriods = new HashSet<>();
    }

    /**
     * Return target of booking system
     */
    public String getBookingTarget() {
        return this.bookingTarget;
    }

    /**
     * Books specified period
     *
     * @return {@code true} in case {@code bookingPeriod} wasn't previously booked
     */
    public boolean bookPeriod(BookingPeriod bookingPeriod) {
        return bookedPeriods.add(bookingPeriod);
    }
}
