package feature;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Sometimes it's necessary to skip execution of some tests. For this purpose {@link Ignore}
 * annotation might be used
 */
public class IgnoredTests {

    @Ignore
    @Test
    public void shouldNotBePrinted() {
        System.out.println("This message should not be printed");
    }
}
