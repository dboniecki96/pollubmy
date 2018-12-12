package pl.pollubmy.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            UserFoundException.class,
            UserNotFoundException.class,
            UserNotLoginException.class,
            WrongRequestException.class,
            PrivateLessonNotFoundException.class,
            ForumPostNotFoundException.class,
            WrongRatingException.class,
            CommentNotFoundException.class,
    })
    public ResponseEntity<?> handleException(HttpServletRequest httpServletRequest, Exception ex) {
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<?> handleFileNotFoundInDatabase(HttpServletRequest httpServletRequest, Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

