package tdd.booking;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalTime;

/**
 * Tests booking functionality offered by {@link BookingSystem} class
 */
@RunWith(JUnitParamsRunner.class)
public class BookingSystemTest {

    private static final String BOOKING_TARGET = "Hotel California";
    private static final BookingPeriod VALID_PERIOD;

    static {
        VALID_PERIOD = new BookingPeriod(LocalTime.of(1, 0), LocalTime.of(2, 0));
    }

    private static Object[] getInvalidBookingTargets() {
        return new Object[]{
                null, "", "  "
        };
    }

    private BookingSystem bookingSystem;

    /**
     * Test fixture element - initializes fields required by test before each test invocation
     */
    @Before
    public void setUp() {
        this.bookingSystem = new BookingSystem(BOOKING_TARGET);
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

    /**
     * Verifies that non previously booked {@link BookingSystem} should allow to book
     * any valid {@link BookingPeriod}
     */
    @Test
    public void shouldAllowBookingAnyPeriodOnFreshBookingSystem() {
        boolean booked = bookingSystem.bookPeriod(VALID_PERIOD);
        Assert.assertTrue(booked);
    }

    /**
     * Verifies that previously booked period cannot be booked twice
     */
    @Test
    public void shouldForbidBookingTheSamePeriodTwice() {
        bookingSystem.bookPeriod(VALID_PERIOD);
        boolean booked = bookingSystem.bookPeriod(VALID_PERIOD);
        Assert.assertFalse(booked);
    }
}
