package pl.pollubmy.server.exceptions;

public class HelpInStudyNotFoundException extends RuntimeException {
    public HelpInStudyNotFoundException() {
    }

    public HelpInStudyNotFoundException(String message) {
        super(message);
    }

    public HelpInStudyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HelpInStudyNotFoundException(Throwable cause) {
        super(cause);
    }

    public HelpInStudyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
