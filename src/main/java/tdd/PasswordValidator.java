package tdd;

/**
 * Validates passwords strength
 */
public class PasswordValidator {

    /**
     * Password to validate
     */
    private String password;

    /**
     * Constructs instance of {@link PasswordValidator} class with associated {@code password} string
     *
     * @param password to validate
     */
    public PasswordValidator(String password) {
        this.password = password;
    }

    /**
     * Returns password associated with current instance of {@link PasswordValidator} class
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
}
