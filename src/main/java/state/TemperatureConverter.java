package state;

/**
 * Utility class to perform temperature conversions for different measurement systems
 */
public class TemperatureConverter {

    private static final int VALUE_SHIFT = 32;
    private static final double VALUE_PROPORTION = 9.0 / 5.0;

    /**
     * Converts Celcius measurements to Fahrenheit
     *
     * @param temperature in Celcius
     * @return converted value
     */
    public static double celciusToFahrenheit(double temperature) {
        return temperature * VALUE_PROPORTION + VALUE_SHIFT;
    }

    /**
     * Converts Fahrenheit measurements to Celcius
     *
     * @param temperature in Fahrenheit
     * @return converted value
     */
    public static double fahrenheitToCelcius(double temperature) {
        return (temperature - VALUE_SHIFT) / VALUE_PROPORTION;
    }
}
