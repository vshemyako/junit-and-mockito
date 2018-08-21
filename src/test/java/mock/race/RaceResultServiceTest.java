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

    /**
     * Reinitializes fields common by test methods
     */
    @Before
    public void setUp() {
        this.raceResultService = new RaceResultService();
        this.clientA = Mockito.mock(Client.class, "clientA");
        this.clientB = Mockito.mock(Client.class, "clientB");
        this.message = Mockito.mock(Message.class);
    }

    /**
     * Verifies that non subscribed client does not receive message
     */
    @Test
    public void notSubscribedClientsShouldNotReceiveMessage() {
        // core functionality
        raceResultService.send(message);

        // verify interactions
        Mockito.verify(clientA, Mockito.never()).receive(message);
    }

    /**
     * Verifies that subscribed client will receive message from a {@link RaceResultService}
     */
    @Test
    public void subscribedClientShouldReceiveMessage() {
        // core functionality
        raceResultService.addSubscriber(clientA);
        raceResultService.send(message);

        // verify interactions
        Mockito.verify(clientA).receive(message);
    }

    /**
     * Verifies that all subscribed clients receive message from a {@link RaceResultService}
     */
    @Test
    public void allSubscribedClientsShouldReceiveMessages() {
        // core functionality
        raceResultService.addSubscriber(clientA);
        raceResultService.addSubscriber(clientB);
        raceResultService.send(message);

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
        raceResultService.addSubscriber(clientA);
        raceResultService.addSubscriber(clientA);
        raceResultService.send(message);

        // verify interactions (default
        Mockito.verify(clientA).receive(message);
    }

    /**
     * Verifies that once removed client should not receive any messages
     */
    @Test
    public void removedSubscribedShouldStopReceiveMessages() {
        // core functionality
        raceResultService.addSubscriber(clientA);
        raceResultService.removeSubscriber(clientA);
        raceResultService.send(message);

        // verify interactions
        Mockito.verify(clientA, Mockito.never()).receive(message);
    }
}