package cinema.exception;

public class SeatNotAvailableException extends RuntimeException {
    public SeatNotAvailableException() {
        super("The ticket has been already purchased!");
    }
}
