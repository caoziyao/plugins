package request;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
