package mock.user;

/**
 * {@link UserService} implementation class to work with {@link User} instances
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private SecurityService securityService;

    /**
     * Constructs valid instance of {@link UserServiceImpl} class
     */
    public UserServiceImpl(UserDAO userDAO, SecurityService securityService) {
        if (userDAO == null) {
            throw new IllegalArgumentException("Data access object not provided!");
        }
        if (securityService == null) {
            throw new IllegalArgumentException("Security service not provided!");
        }
        this.userDAO = userDAO;
        this.securityService = securityService;
    }

    /**
     * Stores {@code user} password to a database, which is preliminary cyphered using md5 algorithms
     */
    @Override
    public void assignPassword(User user) throws Exception {
        String md5Password = securityService.md5(user.getPassword());
        user.setPassword(md5Password);
        userDAO.updateUser(user);
    }
}
