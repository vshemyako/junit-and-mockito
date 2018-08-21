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

    private Map<Category, Set<Client>> categoryToClients = new HashMap<>();

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
            clients.forEach(client -> client.receive(message));
        }
    }

    /**
     * Removes {@code client} from {@code clients} list of chosen {@code category}
     */
    public void removeSubscriber(Category category, Client client) {
        Set<Client> clients = categoryToClients.get(category);
        if (clients != null) {
            clients.remove(client);
        }
    }
}
