package mock.race;

/**
 * Logs application messages
 */
public interface Logger {

    /**
     * Outputs {@code message} to specified destination
     */
    void log(Message message);
}
