package feature.matcher;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.Matchers.hasEntry;

/**
 * It's useful to combine default matchers of Hamcrest library with matchers of
 * Mockito library
 */
public class CombinedUsageOfMatchers {

    private static final String KEY = "name";
    private static final String VALUE = "Adolf";

    private UserDAO userDAO;
    private User user;

    @Before
    public void setUp() {
        this.userDAO = Mockito.mock(UserDAO.class);
        this.user = Mockito.mock(User.class);

        // verifies that map has entry
        Matcher<Map<? extends String, ? extends String>> entryMatcher = hasEntry(KEY, VALUE);
        Map<? extends String, ? extends String> mockedArgument = Matchers.argThat(entryMatcher);
        // mocking method call
        Mockito.when(this.userDAO.getUser(mockedArgument))
                .thenReturn(this.user);
    }

    /**
     * Testing functionality of custom matcher:
     * User have to be returned only in case map with appropriate key/value pairs
     * is passed
     */
    @Test
    public void shouldReturnUserWithSpecifiedProperties() {
        User retrievedUser = userDAO.getUser(Collections.singletonMap(KEY, VALUE));
        Assert.assertEquals(user, retrievedUser);
    }

    private interface UserDAO {
        User getUser(Map<? extends String, ? extends String> properties);
    }

    private interface User {
    }
}
