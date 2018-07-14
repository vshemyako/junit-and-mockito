package tdd.booking;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests booking functionality offered by {@link BookingSystem} class
 */
@RunWith(JUnitParamsRunner.class)
public class BookingSystemTest {

    private static final String BOOKING_TARGET = "Hotel California";

    private static Object[] getInvalidBookingTargets() {
        return new Object[]{
                null, "", "  "
        };
    }

    /**
     * Verifies that constructor sets booking target on instance initialization
     */
    @Test
    public void constructorShouldSetBookingTarget() {
        BookingSystem bookingSystem = new BookingSystem(BOOKING_TARGET);
        Assert.assertEquals("Constructor doesn't set booking target",
                BOOKING_TARGET, bookingSystem.getBookingTarget());
    }

    /**
     * Verifies that constructor doesn't allow to create instance with invalid
     * booking target
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidBookingTargets")
    public void constructorShouldThrowExceptionOnBlankBookingTarget(String bookingTarget) {
        new BookingSystem(bookingTarget);
    }
}
