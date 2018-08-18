package mock.mail;

public interface TemplateEngine {
    String prepareMessage(Template template, Client client);
}


