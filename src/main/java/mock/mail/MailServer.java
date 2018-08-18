package mock.mail;

public interface MailServer {
    void send(String receiver, String msgContent);
}
