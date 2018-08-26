package feature;

import com.googlecode.catchexception.CatchException;
import feature.custom.ThrowableAssertion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Lists possible ways of exception handling.
 * <p>
 * Note: with new version of JUnit 5 - it's became much easier to test for expected exceptions.
 */
public class ExceptionHandling {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Car systemUnderTest;

    /**
     * Let's imitate behavior of arbitrary object
     */
    @Before
    public void setUp() {
        systemUnderTest = Mockito.mock(Car.class);
        Mockito.doThrow(IllegalArgumentException.class)
                .when(systemUnderTest)
                .drive(0);
    }

    /**
     * Shows how to verify that exception has been thrown with a {@link Test} annotation.
     * This approach is not possible with JUnit version 5 (expected method was removed)
     */
    @Test(expected = IllegalArgumentException.class)
    public void handleExceptionWithTestAnnotation() {
        systemUnderTest.drive(0);
    }

    /**
     * Shows how to verify that exception has been thrown with a {@link ExpectedException} rule
     */
    @Test
    public void handleExceptionWithRule() {
        exception.expect(IllegalArgumentException.class);
        systemUnderTest.drive(0);
    }

    /**
     * Shows how to verify that exception has been thrown with try-catch block and
     * {@link Assert#fail()} method
     */
    @Test
    public void handleExceptionWithFail() {
        try {
            systemUnderTest.drive(0);
            // if method flow goes here - test will fail
            Assert.fail("This line should not be reached");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Shows how specific type of exception can be verified with third-party
     * CatchException library
     */
    @Test
    public void handleExceptionWithCatchExceptionLibrary() {
        CatchException.catchException(systemUnderTest).drive(0);
        Assert.assertTrue(CatchException.caughtException() instanceof IllegalArgumentException);
    }

    /**
     * Shows usefulness of lambda expression which can easily substitute CatchException
     * library with several lines of code. Idea was borrowed from 'Rafał Borowiec' on codeleak.pl
     */
    @Test
    public void handleExceptionWithCustomClass() {
        ThrowableAssertion.assertThrown(() -> systemUnderTest.drive(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Non-meaningful interface just to display and test exception handling mechanism
     * in JUnit
     */
    private interface Car {
        void drive(int distance);
    }
}
