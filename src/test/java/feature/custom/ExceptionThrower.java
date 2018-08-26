package feature.custom;

@FunctionalInterface
public interface ExceptionThrower {

    /**
     * Might be used to 'wrap' any method that is expected to throw an exception
     */
    void throwException() throws Throwable;
}
