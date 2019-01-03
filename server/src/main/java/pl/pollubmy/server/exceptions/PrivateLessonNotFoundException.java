package pl.pollubmy.server.exceptions;

public class PrivateLessonNotFoundException extends RuntimeException {

    public PrivateLessonNotFoundException(String message) {
        super(message);
    }
}
