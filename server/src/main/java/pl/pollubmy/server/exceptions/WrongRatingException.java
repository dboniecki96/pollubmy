package pl.pollubmy.server.exceptions;

public class WrongRatingException extends RuntimeException {
    public WrongRatingException(String message) {
        super(message);
    }
}
