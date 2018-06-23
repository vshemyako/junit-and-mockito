package state;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Sometimes it's necessary to prepare some data / variables before running a test. Later on it's important to make some
 * after test cleaning. These requirements are fulfilled by JUnit testing framework.
 */
public class CustomerFixtureTest {

    /**
     * This field is needed to be initialized before each unit test
     */
    private Customer customer;

    /**
     * Those might stay preinitialized
     */
    private String firstClient = "James";
    private String secondClient = "Clark";

    /**
     * Initializes each field before each unit test
     */
    @Before
    public void setUp() {
        customer = new Customer();
    }

    /**
     * Tests whether a {@link Customer#clients} is empty after initialization
     */
    @Test
    public void shouldInitializeToEmptyList() {
        assertTrue(customer.getClients().isEmpty());
    }

    /**
     * Tests whether it's possible to add a client to underlying list of customer's clients
     */
    @Test
    public void shouldAllowToAddClient() {
        customer.addClient(firstClient);
        assertTrue(customer.containsClient(firstClient));
    }

    /**
     * Tests whether it's possible to add several clients to underlying list of customer's clients
     */
    @Test
    public void shouldAllowToAddClients() {
        customer.addClient(firstClient);
        customer.addClient(secondClient);
        assertTrue(customer.containsClient(firstClient));
        assertTrue(customer.containsClient(secondClient));
    }
}
