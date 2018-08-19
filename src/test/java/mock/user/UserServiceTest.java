package mock.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests functionality of {@link UserServiceImpl} class
 */
public class UserServiceTest {

    private static final String USER_PASSWORD = "password";
    private static final String MD5_USER_PASSWORD = "5f4dcc3b5aa765d61d8327deb882cf99";

    private User user;
    private UserDAO userDAO;
    private SecurityService securityService;

    @Before
    public void setUp() {
        user = Mockito.mock(User.class);
        userDAO = Mockito.mock(UserDAO.class);
        securityService = Mockito.mock(SecurityService.class);
    }

    /**
     * Verifies that {@link UserServiceImpl#assignPassword(User)} method
     * transforms password of {@link User} using md5 algorithms and stores
     * it in database.
     */
    @Test
    public void shouldAssignTransformedPasswordToUser() {
        // stubbing
        Mockito.when(user.getPassword()).thenReturn(USER_PASSWORD);
        Mockito.when(securityService.md5(USER_PASSWORD)).thenReturn(MD5_USER_PASSWORD);

        // main logic
        String password = user.getPassword();
        String md5Password = securityService.md5(password);
        user.setPassword(md5Password);
        userDAO.updateUser(user);

        // verification
        Mockito.verify(user).getPassword();
        Mockito.verify(securityService).md5(USER_PASSWORD);
        Mockito.verify(user).setPassword(MD5_USER_PASSWORD);
        Mockito.verify(userDAO).updateUser(user);
    }
}
