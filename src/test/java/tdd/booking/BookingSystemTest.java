package tdd.booking;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests booking functionality offered by {@link BookingSystem} class
 */
public class BookingSystemTest {

    /**
     * Verifies that constructor sets booking target on instance initialization
     */
    @Test
    public void constructorShouldSetBookingTarget() {
        BookingSystem bookingSystem = new BookingSystem("Hotel California");
        Assert.assertEquals("Constructor doesn't set booking target",
                "Hotel California", bookingSystem.getBookingTarget());
    }
}
