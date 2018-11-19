package pl.pollubmy.server.exceptions;

public class IncorrectJwtException extends RuntimeException{
    public IncorrectJwtException() {
    }

    public IncorrectJwtException(String message) {
        super(message);
    }

    public IncorrectJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectJwtException(Throwable cause) {
        super(cause);
    }

    public IncorrectJwtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
