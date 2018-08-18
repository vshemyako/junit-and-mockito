package mock.mail;

/**
 * Manager class to send messages to the {@link Client}
 */
public class Messenger {

    private TemplateEngine templateEngine;
    private MailServer mailServer;

    /**
     * Initializes required fields
     */
    public Messenger(TemplateEngine templateEngine, MailServer mailServer) {
        this.templateEngine = templateEngine;
        this.mailServer = mailServer;
    }

    /**
     * Sends composed mail message to the specified {@code client}
     *
     * @param client   - message to send to
     * @param template - which is used to compose mail body
     */
    public void sendMessage(Client client, Template template) {
        String msgContent = templateEngine.prepareMessage(template, client);
        String receiver = client.getMail();
        this.mailServer.send(receiver, msgContent);
    }
}
