package mock.user;

/**
 * Denotes a single user of a system
 */
public interface User {

    /**
     * Sets {@code password} for particular user
     */
    void setPassword(String password);

    /**
     * Returns password previously assigned to particular user
     */
    String getPassword();
}
