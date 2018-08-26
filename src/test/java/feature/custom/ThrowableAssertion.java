package feature.custom;

import org.hamcrest.Matchers;
import org.junit.Assert;

/**
 * It's possible to test specific type of thrown exception with custom class
 */
public class ThrowableAssertion {

    private final Throwable caughtException;

    public ThrowableAssertion(Throwable exception) {
        this.caughtException = exception;
    }

    /**
     * Wraps caught exception with {@link ThrowableAssertion} object or, in case
     * no exception has been thrown: throws {@link AssertionError} itself
     */
    public static ThrowableAssertion assertThrown(ExceptionThrower thrower) {
        try {
            thrower.throwException();
        } catch (Throwable throwable) {
            return new ThrowableAssertion(throwable);
        }
        throw new AssertionError("Exception wasn't thrown, but was expected");
    }

    /**
     * Checks that caught exception is of desired type. Implemented in 'fluent' style.
     */
    public ThrowableAssertion isInstanceOf(Class<? extends Throwable> expectedType) {
        Assert.assertThat(caughtException, Matchers.instanceOf(expectedType));
        return this;
    }
}
