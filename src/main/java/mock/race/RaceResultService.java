package mock.race;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Service for sending race results messages to subscribers
 */
public class RaceResultService {

    private final Logger logger;
    private final Map<Category, Set<Client>> categoryToClients;

    public RaceResultService(Logger logger) {
        this.logger = logger;
        this.categoryToClients = new HashMap<>();
    }

    /**
     * Adds subscriber for chosen {@code category} for later notification
     */
    public void addSubscriber(Category category, Client client) {
        Function<Category, Set<Client>> mappingFunction = chosenCategory -> new HashSet<>();
        Set<Client> clients = categoryToClients.computeIfAbsent(category, mappingFunction);
        clients.add(client);
    }

    /**
     * Sends {@code message} to subscribed clients of chosen {@code category}
     */
    public void send(Category category, Message message) {
        Set<Client> clients = categoryToClients.get(category);
        if (clients != null) {
            clients.forEach(client -> {
                client.receive(message);
                logger.log(message);
            });
        }
    }

    /**
     * Removes {@code client} from {@code clients} list of chosen {@code category}
     */
    public void removeSubscriber(Category category, Client client) {
        Set<Client> clients = categoryToClients.get(category);
        if (clients == null) {
            throw new IllegalStateException("Can't unsubscribe non-subscribed client");
        }
        boolean unsubscribed = clients.remove(client);
        if(!unsubscribed) {
            throw new IllegalStateException("Failed to unsubscribe client");
        }
    }
}
