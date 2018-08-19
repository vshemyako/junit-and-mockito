package mock.user;

/**
 * Performs different operations upon {@link User} instances
 */
public interface UserService {

    /**
     * Assigns password to chosen {@code user}
     */
    void assignPassword(User user) throws Exception;
}
