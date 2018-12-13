package pl.pollubmy.server.exceptions;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<?> handleFileNotFoundInDatabaseException(HttpServletRequest httpServletRequest, Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class, IllegalStateException.class})
    public ResponseEntity<?> handleTooBigFileException(Exception ex) {
        return new ResponseEntity<>("File is too big to upload", HttpStatus.PAYLOAD_TOO_LARGE);
    }
}

