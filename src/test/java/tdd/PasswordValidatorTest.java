package tdd;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static tdd.PasswordValidationRules.MINIMAL_LENGTH;

/**
 * Tests functionality of {@link PasswordValidator} class
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordValidatorTest {

    /**
     * Returns array of several passwords
     */
    private static Object[] getRandomPasswords() {
        return new Object[]{
                "basic", "expanded", "sophisticated"
        };
    }

    /**
     * Returns array of blank passwords
     */
    private static Object[] getBlankPasswords() {
        return new Object[]{
                null, "", "  "
        };
    }

    /**
     * Returns array of passwords of invalid length
     */
    private static Object[] getInvalidLengthPasswords() {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        return new Object[]{
                IntStream.range(0, randomizer.nextInt(1, MINIMAL_LENGTH))
                        .mapToObj(String::valueOf)
                        .reduce("", (first, second) -> first + second)
        };
    }

    /**
     * Returns array of passwords of valid length
     */
    private static Object[] getValidLengthPasswords() {
        ThreadLocalRandom randomizer = ThreadLocalRandom.current();
        return new Object[]{
                IntStream.rangeClosed(0, randomizer.nextInt(MINIMAL_LENGTH, MINIMAL_LENGTH * 2))
                        .mapToObj(String::valueOf)
                        .reduce("", (first, second) -> first + second)
        };
    }

    /**
     * Verifies that constructor sets {@link PasswordValidator#password} field correctly
     */
    @Test
    @Parameters(method = "getRandomPasswords")
    public void constructorShouldSetPassword(String password) {
        PasswordValidator passwordValidator = new PasswordValidator(password);
        Assert.assertEquals("Constructor doesn't set password field", password, passwordValidator.getPassword());
    }

    /**
     * Verifies that blank strings are treated as invalid arguments passed to the constructor
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getBlankPasswords")
    public void constructorShouldThrowExceptionOnEmptyPassword(String password) {
        new PasswordValidator(password);
    }

    /**
     * Verifies that validator returns false in case password is below minimal length restriction
     */
    @Test
    @Parameters(method = "getInvalidLengthPasswords")
    public void shouldReturnFalseOnPasswordLengthLessThanMinimal(String invalidLengthPassword) {
        PasswordValidator passwordValidator = new PasswordValidator(invalidLengthPassword);
        Assert.assertFalse("Length validation is wrong", passwordValidator.isLengthValid());
    }

    /**
     * Verifies that validator returns true in case password is of valid length
     */
    @Test
    @Parameters(method = "getValidLengthPasswords")
    public void shouldReturnTrueOnPasswordLengthGreaterThanMinimal(String validLengthPassword) {
        PasswordValidator passwordValidator = new PasswordValidator(validLengthPassword);
        Assert.assertTrue("Length validation is wrong", passwordValidator.isLengthValid());
    }
}
