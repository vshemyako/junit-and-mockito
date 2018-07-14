package tdd;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests functionality of {@link PasswordValidator} class
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordValidatorTest {

    /**
     * Return array of several passwords
     */
    private static Object[] getRandomPasswords() {
        return new Object[]{
                "basic", "expanded", "sophisticated"
        };
    }

    /**
     * Return array of blank passwords
     */
    private static Object[] getBlankPasswords() {
        return new Object[]{
                null, "", "  "
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
    public void shouldReturnFalseOnPasswordLengthLessThanMinimal() {
        PasswordValidator passwordValidator = new PasswordValidator("1234");
        Assert.assertFalse("Length validation is wrong", passwordValidator.isLengthValid());
    }

    /**
     * Verifies that validator returns true in case password is of valid length
     */
    @Test
    public void shouldReturnTrueOnPasswordLengthGreaterThanMinimal() {
        PasswordValidator passwordValidator = new PasswordValidator("12345");
        Assert.assertTrue("Length validation is wrong", passwordValidator.isLengthValid());
    }
}
