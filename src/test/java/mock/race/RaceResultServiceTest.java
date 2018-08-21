package mock.race;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests functionality of {@link RaceResultService} class
 */
public class RaceResultServiceTest {

    private RaceResultService raceResultService;
    private Client clientA;
    private Client clientB;
    private Message message;
    private Category categoryA;
    private Category categoryB;

    /**
     * Reinitializes fields common by test methods
     */
    @Before
    public void setUp() {
        this.raceResultService = new RaceResultService();
        this.clientA = Mockito.mock(Client.class, "clientA");
        this.clientB = Mockito.mock(Client.class, "clientB");
        this.message = Mockito.mock(Message.class, "message");
        this.categoryA = Mockito.mock(Category.class, "categoryA");
        this.categoryB = Mockito.mock(Category.class, "categoryB");
    }

    /**
     * Verifies that non subscribed client does not receive message
     */
    @Test
    public void notSubscribedClientsShouldNotReceiveMessage() {
        // core functionality
        raceResultService.send(categoryA, message);

        // verify interactions
        Mockito.verify(clientA, Mockito.never()).receive(message);
    }

    /**
     * Verifies that subscribed client will receive message from a {@link RaceResultService}
     */
    @Test
    public void subscribedClientShouldReceiveMessage() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.send(categoryA, message);

        // verify interactions
        Mockito.verify(clientA).receive(message);
    }

    /**
     * Verifies that all subscribed clients receive message from a {@link RaceResultService}
     */
    @Test
    public void allSubscribedClientsShouldReceiveMessages() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientB);
        raceResultService.send(categoryA, message);

        // verify interactions
        Mockito.verify(clientA).receive(message);
        Mockito.verify(clientB).receive(message);
    }

    /**
     * Verifies that even multiple subscribes result in only one received message
     */
    @Test
    public void severalSubscribesOfSameClientShouldResultInOneReceivedMessage() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.send(categoryA, message);

        // verify interactions (default
        Mockito.verify(clientA).receive(message);
    }

    /**
     * Verifies that once removed client should not receive any messages
     */
    @Test
    public void removedSubscribedShouldStopReceiveMessages() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.removeSubscriber(categoryA, clientA);
        raceResultService.send(categoryA, message);

        // verify interactions
        Mockito.verify(clientA, Mockito.never()).receive(message);
    }
}