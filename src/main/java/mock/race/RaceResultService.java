package mock.race;

/**
 * Service for sending race results messages to subscribers
 */
public class RaceResultService {

    private Client client;

    /**
     * Adds subscriber for later notification
     */
    public void addSubscriber(Client client) {
        this.client = client;
    }

    /**
     * Sends {@code message} to subscribed client
     */
    public void send(Message message) {
        client.receive(message);
    }
}
