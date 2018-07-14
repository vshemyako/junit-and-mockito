package tdd;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests functionality of {@link PasswordValidator} class
 */
public class PasswordValidatorTest {

    /**
     * Verifies that constructor sets {@link PasswordValidator#password} field correctly
     */
    @Test
    public void constructorShouldSetPassword() {
        PasswordValidator passwordValidator = new PasswordValidator("password");
        Assert.assertEquals("Constructor doesn't set password field", "password", passwordValidator.getPassword());
    }
}
