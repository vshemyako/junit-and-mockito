package mock;

/**
 * Basic interface for mocking
 */
public interface Car {
    String getName();
    boolean needsFuel();
    double getEngineTemperature();
    void driveTo(String destination);
}
