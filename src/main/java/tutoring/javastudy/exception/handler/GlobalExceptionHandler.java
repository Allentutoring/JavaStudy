package tutoring.javastudy.exception.handler;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        log.error("handleException",ex);
        ExceptionResponse response = new ExceptionResponse();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
    
    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res, Exception exception) throws IOException
    {
        res.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse res) throws IOException
    {
        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    }
    
    @ExceptionHandler(BindException.class)
    public String handleValidationException(HttpServletResponse res) throws IOException
    {
        res.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
        return "123";
        // return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("123");
    }
}
