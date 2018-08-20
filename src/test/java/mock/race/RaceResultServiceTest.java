package mock.race;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests functionality of {@link RaceResultService} class
 */
public class RaceResultServiceTest {

    /**
     * Verifies that subscribed client will receive message from a {@link RaceResultService}
     */
    @Test
    public void subscribedClientShouldReceiveMessage() {
        // SUT
        RaceResultService raceResultService = new RaceResultService();

        // mocking objects
        Client client = Mockito.mock(Client.class);
        Message message = Mockito.mock(Message.class);

        // core functionality
        raceResultService.addSubscriber(client);
        raceResultService.send(message);

        // verify interactions
        Mockito.verify(client).receive(message);
    }
}