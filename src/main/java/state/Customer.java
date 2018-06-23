package state;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to demonstrate 'Test fixture' approach offered by 'JUnit' library
 */
public class Customer {

    /**
     * This field will be specifically targeted on tests
     */
    private final List<String> clients;

    /**
     * Creates an instance of {@link Customer} class
     */
    public Customer() {
        this.clients = new ArrayList<>();
    }

    /**
     * Adds subsequent client to a customer's list
     *
     * @param client to add
     * @return true in case client was added to list
     */
    public boolean addClient(String client) {
        return clients.add(client);
    }

    /**
     * @param client previously added
     * @return true if client was already added to collection
     */
    public boolean containsClient(String client) {
        return clients.contains(client);
    }

    /**
     * @return list of associated clients
     */
    public List<String> getClients() {
        return clients;
    }
}
