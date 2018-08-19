package mock.user;

/**
 * Accesses {@link User} data stored in a database
 */
public interface UserDAO {

    /**
     * Updates database record for provided {@link User}
     */
    void updateUser(User user);
}
