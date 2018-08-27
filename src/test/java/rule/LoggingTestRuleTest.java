package rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * Verifies that custom {@link LoggingTestRule} will be invoked during test
 * method processing
 */
public class LoggingTestRuleTest {

    /**
     * Rule will be invoked before each test method
     */
    @Rule
    public TestRule testRule = new LoggingTestRule();

    @Test
    public void shouldBeIntercepted() {
        System.out.println("Test method execution itself");
    }
}
