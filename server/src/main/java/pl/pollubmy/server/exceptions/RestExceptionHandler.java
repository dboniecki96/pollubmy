package pl.pollubmy.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> handleException(HttpServletRequest httpServletRequest, Exception ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

