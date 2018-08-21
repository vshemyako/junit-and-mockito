package mock.race;

import java.util.HashSet;
import java.util.Set;

/**
 * Service for sending race results messages to subscribers
 */
public class RaceResultService {

    private Set<Client> clients = new HashSet<>();

    /**
     * Adds subscriber for later notification
     */
    public void addSubscriber(Client client) {
        clients.add(client);
    }

    /**
     * Sends {@code message} to subscribed client
     */
    public void send(Message message) {
        clients.forEach(client -> client.receive(message));
    }

    /**
     * Removes {@code client} from {@code clients} list
     */
    public void removeSubscriber(Client client) {
        clients.remove(client);
    }
}
