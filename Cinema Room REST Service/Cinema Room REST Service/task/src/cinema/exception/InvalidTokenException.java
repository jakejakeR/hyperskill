package cinema.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Wrong token!");
    }
}
