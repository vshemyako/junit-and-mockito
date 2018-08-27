package rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Demonstrates how to implement custom {@link TestRule} to alter
 * test code flow
 */
public class LoggingTestRule implements TestRule {

    /**
     * Abstract class instead of interface -> impossible to use lambdas...
     */
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("Before test method execution");
                base.evaluate();
                System.out.println("After test method execution");
            }
        };
    }
}
