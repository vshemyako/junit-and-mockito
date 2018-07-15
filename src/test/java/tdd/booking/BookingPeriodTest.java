package tdd.booking;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

/**
 * Tests functionality of a {@link BookingPeriod} class
 */
public class BookingPeriodTest {

    @Test
    public void shouldInitializeFromAndToFields() {
        LocalTime from = LocalTime.of(1, 0);
        LocalTime to = LocalTime.of(2, 0);

        BookingPeriod bookingPeriod = new BookingPeriod(from, to);
        Assert.assertEquals("Constructor doesn't set 'from' point of time", from, bookingPeriod.getFrom());
        Assert.assertEquals("Constructor doesn't set 'to' point of time", to, bookingPeriod.getTo());
    }
}
