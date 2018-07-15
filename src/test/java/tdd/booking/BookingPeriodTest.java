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
     * Verifies that during object construction {@code from} and {@code to} periods are set
     */
    @Test
    @Parameters(method = "getFromToPeriods")
    public void shouldInitializeFromAndToFields(LocalTime from, LocalTime to) {
        BookingPeriod bookingPeriod = new BookingPeriod(from, to);
        Assert.assertEquals("Constructor doesn't set 'from' point of time", from, bookingPeriod.getFrom());
        Assert.assertEquals("Constructor doesn't set 'to' point of time", to, bookingPeriod.getTo());
    }
}
