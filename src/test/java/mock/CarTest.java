package mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Basic interface to test functionality of Mockito testing framework
 */
public class CarTest {

    /**
     * Car instance simulation
     */
    private Car mockedCar;

    /**
     * Test fixture
     */
    @Before
    public void setUp() {
        mockedCar = Mockito.mock(Car.class);
    }

    /**
     * Dumb test actually =)
     */
    @Test
    public void mockedObjectShouldBeCar() {
        Assert.assertTrue(mockedCar instanceof Car);
    }

    /**
     * Verifies that default boolean value provided by Mockito is {@code false}
     */
    @Test
    public void defaultBooleanShouldBeFalse() {
        Assert.assertFalse(mockedCar.needsFuel());
    }

    /**
     * Verifies that default double value provided by Mockito is '0.0'
     */
    @Test
    public void defaultDoubleShouldBeZero() {
        Assert.assertEquals(0.0, mockedCar.getEngineTemperature(), 0.001);
    }

    /**
     * Verifies that default object reference provided by Mockito is 'null'
     */
    @Test
    public void defaultObjectReferenceShouldBeNull() {
        Assert.assertEquals(null, mockedCar.getName());
    }

    /**
     * Verifies that default value returned by Mockito can be changed
     */
    @Test
    public void defaultBooleanShouldBeTrue() {
        // changing default return value
        Mockito.when(mockedCar.needsFuel())
                .thenReturn(true);

        Assert.assertTrue(mockedCar.needsFuel());
    }

    /**
     * Verifies that method behaviour can be changed to throw desired exceptions
     */
    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionOnGetName() {
        Mockito.when(mockedCar.getName())
                .thenThrow(new IllegalStateException());

        mockedCar.getName();
    }

    /**
     * Verifies that methods of a mocked object was called
     */
    @Test
    public void shouldVerifyNeedsFuelWasCalled() {
        mockedCar.needsFuel();
        Mockito.verify(mockedCar).needsFuel();
    }
}