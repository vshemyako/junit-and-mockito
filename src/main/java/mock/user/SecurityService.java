package mock.user;

/**
 * Performs security related operations upon password info
 */
public interface SecurityService {

    /**
     * Returns hash value for provided {@code password} using md5 algorithm
     */
    String md5(String password);
}
