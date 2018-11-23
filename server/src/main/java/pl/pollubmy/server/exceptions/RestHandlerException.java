package pl.pollubmy.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RestHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            UserFoundException.class,
            UserNotFoundException.class,
            UserNotLoginException.class,
            WrongRequestException.class})
    public ResponseEntity<?> handleExceptionUserFound(HttpServletRequest httpServletRequest, Exception ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

