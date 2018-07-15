package tdd.booking;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalTime;

/**
 * Tests functionality of a {@link BookingPeriod} class
 */
@RunWith(JUnitParamsRunner.class)
public class BookingPeriodTest {

    /**
     * @return pairs of from-to periods
     */
    private static Object[] getFromToPeriods() {
        return new Object[][]{
                {LocalTime.of(1, 0), LocalTime.of(2, 0)},
                {LocalTime.of(4, 0), LocalTime.of(10, 0)},
                {LocalTime.of(17, 0), LocalTime.of(23, 0)},
        };
    }

    /**
     * @return illegal pairs of from-to periods
     */
    private static Object[] getIllegalPeriods() {
        return new Object[][]{
                {null, LocalTime.of(2, 0)},
                {LocalTime.of(4, 0), null},
                {null, null},
        };
    }

    /**
     * Verifies that during object construction {@code from} and {@code to} periods are set
     */
    @Test
    @Parameters(method = "getFromToPeriods")
    public void constructorShouldInitializeFromAndToFields(LocalTime from, LocalTime to) {
        BookingPeriod bookingPeriod = new BookingPeriod(from, to);
        Assert.assertEquals("Constructor doesn't set 'from' point of time", from, bookingPeriod.getFrom());
        Assert.assertEquals("Constructor doesn't set 'to' point of time", to, bookingPeriod.getTo());
    }

    /**
     * Verifies that constructor throws {@link IllegalArgumentException} in case either or both of arguments
     * passed are null values
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getIllegalPeriods")
    public void constructorShouldThrowExceptionOnNullArguments(LocalTime from, LocalTime to) {
        new BookingPeriod(from, to);
    }
}
