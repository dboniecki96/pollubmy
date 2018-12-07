package pl.pollubmy.server.exceptions;

public class ForumPostNotFoundException extends RuntimeException {
    public ForumPostNotFoundException(String message) {
        super(message);
    }
}
