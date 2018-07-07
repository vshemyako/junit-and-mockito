package state;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Tests functionality of utility {@link TemperatureConverter} class
 */
@RunWith(JUnitParamsRunner.class)
public class TemperatureConverterClass {

    /**
     * @return celcius to fahrenheit pairs
     */
    private static Object[] getCelciusToFahrenheitParams() {
        return new Object[][]{
                {0, 32},
                {37, 98.6},
                {100, 212}
        };
    }

    /**
     * @return fahrenheit to celcius pairs
     */
    private static Object[] getFahrenheitToCelciusParams() {
        return new Object[][]{
                {32, 0},
                {98.6, 37},
                {212, 100}
        };
    }

    /**
     * Verifies celcius to fahrenheit conversion functionality
     *
     * @param celcius    measurements
     * @param fahrenheit measurements
     */
    @Test
    @Parameters(method = "getCelciusToFahrenheitParams")
    public void shouldConvertCelciusToFahrenheit(double celcius, double fahrenheit) {
        double convertedValue = TemperatureConverter.celciusToFahrenheit(celcius);
        assertEquals(fahrenheit, convertedValue, 0.01);
    }

    /**
     * Verifies fahrenheit to celcius conversion functionality
     *
     * @param fahrenheit measurements
     * @param celcius    measurements
     */
    @Test
    @Parameters(method = "getFahrenheitToCelciusParams")
    public void shouldConvertFahrenheitToCelcius(double fahrenheit, double celcius) {
        double convertedValue = TemperatureConverter.fahrenheitToCelcius(fahrenheit);
        assertEquals(celcius, convertedValue, 0.01);
    }
}
