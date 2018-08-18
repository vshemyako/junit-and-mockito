package mock.mail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests functionality of a {@link Messenger} class
 */
public class MessengerTest {

    private static final String EMAIL_MESSAGE = "Lectures will start at 8:00 am.";
    private static final String RECIPIENT = "recipient@mail.com";

    /**
     * In terms of unit testing this 'testing double' is a 'dummy' which is simply passed
     * to another object
     */
    private Template template;

    /**
     * In terms of unit testing this 'testing double' is a 'stub' which provides indirect inputs
     * to the SUT
     */
    private TemplateEngine templateEngine;

    /**
     * 'Stub' as well as 'dummy' - simply passed as an argument and also provides indirect input
     */
    private Client client;

    /**
     * Test spy to accept indirect output of SUT
     */
    private MailServer mailServer;

    @Before
    public void setUp() {
        this.template = Mockito.mock(Template.class);
        this.templateEngine = Mockito.mock(TemplateEngine.class);
        this.client = Mockito.mock(Client.class);
        this.mailServer = Mockito.mock(MailServer.class);
    }

    /**
     * Verifies functionality of {@link Messenger#sendMessage(Client, Template)} method
     */
    @Test
    public void shouldSendMessage() {
        // stub mocks
        Mockito.when(templateEngine.prepareMessage(template, client)).thenReturn(EMAIL_MESSAGE);
        Mockito.when(client.getMail()).thenReturn(RECIPIENT);

        // construct SUT
        Messenger messenger = new Messenger(templateEngine, mailServer);
        messenger.sendMessage(client, template);

        // verify
        Mockito.verify(mailServer).send(RECIPIENT, EMAIL_MESSAGE);
    }
}