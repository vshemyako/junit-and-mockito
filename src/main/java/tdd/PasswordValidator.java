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
        if (password == null) {
            throw new IllegalArgumentException("Password to validate must not be null");
        }
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password to validate must not be empty");
        }

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
