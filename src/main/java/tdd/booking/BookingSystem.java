package tdd.booking;

/**
 * Offers booking several hours for defined booking target
 */
public class BookingSystem {

    private final String bookingTarget;

    /**
     * Initializes instance of {@link BookingSystem} class with {@code bookingTarget}
     *
     * @param bookingTarget - subject of booking system
     */
    public BookingSystem(String bookingTarget) {
        this.bookingTarget = bookingTarget;
    }

    /**
     * Return target of booking system
     */
    public String getBookingTarget() {
        return this.bookingTarget;
    }
}
