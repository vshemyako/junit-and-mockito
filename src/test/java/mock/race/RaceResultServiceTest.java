package mock.race;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests functionality of {@link RaceResultService} class
 */
public class RaceResultServiceTest {

    private Logger logger;
    private RaceResultService raceResultService;
    private Client clientA;
    private Client clientB;
    private Message messageA;
    private Message messageB;
    private Category categoryA;
    private Category categoryB;

    /**
     * Reinitializes fields common by test methods
     */
    @Before
    public void setUp() {
        this.logger = Mockito.mock(Logger.class);
        this.raceResultService = new RaceResultService(logger);

        this.clientA = Mockito.mock(Client.class, "clientA");
        this.clientB = Mockito.mock(Client.class, "clientB");
        this.messageA = Mockito.mock(Message.class, "messageA");
        this.messageB = Mockito.mock(Message.class, "messageB");
        this.categoryA = Mockito.mock(Category.class, "categoryA");
        this.categoryB = Mockito.mock(Category.class, "categoryB");
    }

    /**
     * Verifies that non subscribed client does not receive message
     */
    @Test
    public void notSubscribedClientsShouldNotReceiveMessage() {
        // core functionality
        raceResultService.send(categoryA, messageA);

        // verify interactions
        Mockito.verify(clientA, Mockito.never()).receive(messageA);
        Mockito.verify(logger, Mockito.never()).log(messageA);
    }

    /**
     * Verifies that subscribed client will receive message from a {@link RaceResultService}
     */
    @Test
    public void subscribedClientShouldReceiveMessage() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.send(categoryA, messageA);

        // verify interactions
        Mockito.verify(clientA).receive(messageA);
        Mockito.verify(logger).log(messageA);
    }

    /**
     * Verifies that all subscribed clients receive message from a {@link RaceResultService}
     */
    @Test
    public void allSubscribedClientsShouldReceiveMessages() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientB);
        raceResultService.send(categoryA, messageA);

        // verify interactions
        Mockito.verify(clientA).receive(messageA);
        Mockito.verify(clientB).receive(messageA);
        Mockito.verify(logger, Mockito.times(2)).log(messageA);
    }

    /**
     * Verifies that even multiple subscribes result in only one received message
     */
    @Test
    public void severalSubscribesOfSameClientShouldResultInOneReceivedMessage() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.send(categoryA, messageA);

        // verify interactions
        Mockito.verify(clientA).receive(messageA);
        Mockito.verify(logger).log(messageA);
    }

    /**
     * Verifies that once removed client should not receive any messages
     */
    @Test
    public void removedSubscriberShouldStopReceiveMessages() {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.removeSubscriber(categoryA, clientA);
        raceResultService.send(categoryA, messageA);

        // verify interactions
        Mockito.verify(clientA, Mockito.never()).receive(messageA);
        Mockito.verify(logger, Mockito.never()).log(messageA);
    }

    /**
     * Verifies that subscriber do not receive messages from categories he isn't
     * subscribed for
     */
    @Test
    public void subscribedClientShouldReceiveOnlyMessageForChosenCategory() {
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.send(categoryB, messageA);

        Mockito.verify(clientA, Mockito.never()).receive(messageA);
        Mockito.verify(logger, Mockito.never()).log(messageA);
    }

    /**
     * Verifies that subscriber will receive messages from different categories in case
     * he has chosen to receive them
     */
    @Test
    public void subscribedClientShouldReceiveMessagesFromSeveralSubscribedCategories() {
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryB, clientA);
        raceResultService.send(categoryA, messageA);
        raceResultService.send(categoryB, messageB);

        Mockito.verify(clientA).receive(messageA);
        Mockito.verify(clientA).receive(messageB);
        Mockito.verify(logger).log(messageA);
        Mockito.verify(logger).log(messageB);
    }

    /**
     * Verifies that several subscribers receive only chosen messages
     */
    @Test
    public void severalSubscribersReceiveMessageFromChosenCategories() {
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientB);
        raceResultService.addSubscriber(categoryB, clientB);
        raceResultService.send(categoryA, messageA);
        raceResultService.send(categoryB, messageB);

        Mockito.verify(clientA).receive(messageA);
        Mockito.verify(clientB).receive(messageA);
        Mockito.verify(clientB).receive(messageB);
        Mockito.verify(logger, Mockito.times(2)).log(messageA);
        Mockito.verify(logger).log(messageB);
    }
}