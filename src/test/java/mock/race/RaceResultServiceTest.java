package mock.race;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Tests functionality of {@link RaceResultService} class
 */
@RunWith(JUnitParamsRunner.class)
public class RaceResultServiceTest {

    /**
     * Generates several random integral numbers to test multiple methods invocation result
     */
    private static Object[] getCountsOfMethodInvocation() {
        return new Object[]{
                ThreadLocalRandom.current().nextInt(100),
                ThreadLocalRandom.current().nextInt(50),
                ThreadLocalRandom.current().nextInt(25)
        };
    }

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
    @Parameters(method = "getCountsOfMethodInvocation")
    public void subscribedClientShouldReceiveMessage(int messagesNumber) {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);

        for (int loopIndex = messagesNumber; loopIndex > 0; loopIndex--) {
            raceResultService.send(categoryA, messageA);
        }

        // verify interactions
        Mockito.verify(clientA, Mockito.times(messagesNumber)).receive(messageA);
        Mockito.verify(logger, Mockito.times(messagesNumber)).log(messageA);
    }

    /**
     * Verifies that all subscribed clients receive message from a {@link RaceResultService}
     */
    @Test
    @Parameters(method = "getCountsOfMethodInvocation")
    public void allSubscribedClientsShouldReceiveMessages(int messagesNumber) {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientB);

        for (int loopIndex = messagesNumber; loopIndex > 0; loopIndex--) {
            raceResultService.send(categoryA, messageA);
        }

        // verify interactions
        Mockito.verify(clientA, Mockito.times(messagesNumber)).receive(messageA);
        Mockito.verify(clientB, Mockito.times(messagesNumber)).receive(messageA);
        Mockito.verify(logger, Mockito.times(2 * messagesNumber)).log(messageA);
    }

    /**
     * Verifies that even multiple subscribes result in only one received message
     */
    @Test
    @Parameters(method = "getCountsOfMethodInvocation")
    public void severalSubscribesOfSameClientShouldResultInOneReceivedMessage(int messagesNumber) {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientA);

        for (int loopIndex = messagesNumber; loopIndex > 0; loopIndex--) {
            raceResultService.send(categoryA, messageA);
        }

        // verify interactions
        Mockito.verify(clientA, Mockito.times(messagesNumber)).receive(messageA);
        Mockito.verify(logger, Mockito.times(messagesNumber)).log(messageA);
    }

    /**
     * Verifies that once removed client should not receive any messages
     */
    @Test
    @Parameters(method = "getCountsOfMethodInvocation")
    public void removedSubscriberShouldStopReceiveMessages(int messagesNumber) {
        // core functionality
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.removeSubscriber(categoryA, clientA);

        for (int loopIndex = messagesNumber; loopIndex > 0; loopIndex--) {
            raceResultService.send(categoryA, messageA);
        }

        // verify interactions
        Mockito.verify(clientA, Mockito.never()).receive(messageA);
        Mockito.verify(logger, Mockito.never()).log(messageA);
    }

    /**
     * Verifies that subscriber do not receive messages from categories he isn't
     * subscribed for
     */
    @Test
    @Parameters(method = "getCountsOfMethodInvocation")
    public void subscribedClientShouldReceiveOnlyMessageForChosenCategory(int messagesNumber) {
        raceResultService.addSubscriber(categoryA, clientA);

        for (int loopIndex = messagesNumber; loopIndex > 0; loopIndex--) {
            raceResultService.send(categoryB, messageA);
        }

        Mockito.verify(clientA, Mockito.never()).receive(messageA);
        Mockito.verify(logger, Mockito.never()).log(messageA);
    }

    /**
     * Verifies that subscriber will receive messages from different categories in case
     * he has chosen to receive them
     */
    @Test
    @Parameters(method = "getCountsOfMethodInvocation")
    public void subscribedClientShouldReceiveMessagesFromSeveralSubscribedCategories(int messagesNumber) {
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryB, clientA);

        for (int loopIndex = messagesNumber; loopIndex > 0; loopIndex--) {
            raceResultService.send(categoryA, messageA);
            raceResultService.send(categoryB, messageB);
        }

        Mockito.verify(clientA, Mockito.times(messagesNumber)).receive(messageA);
        Mockito.verify(clientA, Mockito.times(messagesNumber)).receive(messageB);
        Mockito.verify(logger, Mockito.times(messagesNumber)).log(messageA);
        Mockito.verify(logger, Mockito.times(messagesNumber)).log(messageB);
    }

    /**
     * Verifies that several subscribers receive only chosen messages
     */
    @Test
    @Parameters(method = "getCountsOfMethodInvocation")
    public void severalSubscribersReceiveMessageFromChosenCategories(int messagesNumber) {
        raceResultService.addSubscriber(categoryA, clientA);
        raceResultService.addSubscriber(categoryA, clientB);
        raceResultService.addSubscriber(categoryB, clientB);

        for (int loopIndex = messagesNumber; loopIndex > 0; loopIndex--) {
            raceResultService.send(categoryA, messageA);
            raceResultService.send(categoryB, messageB);
        }

        Mockito.verify(clientA, Mockito.times(messagesNumber)).receive(messageA);
        Mockito.verify(clientB, Mockito.times(messagesNumber)).receive(messageA);
        Mockito.verify(clientB, Mockito.times(messagesNumber)).receive(messageB);
        Mockito.verify(logger, Mockito.times(2 * messagesNumber)).log(messageA);
        Mockito.verify(logger, Mockito.times(messagesNumber)).log(messageB);
    }

    /**
     * Verifies that exception is thrown in case non-subscribed client tries to unsubscribe
     */
    @Test(expected = IllegalStateException.class)
    public void nonSubscribedClientShouldNotBeAbleToUnsubscribe() {
        raceResultService.removeSubscriber(categoryA, clientA);
    }
}