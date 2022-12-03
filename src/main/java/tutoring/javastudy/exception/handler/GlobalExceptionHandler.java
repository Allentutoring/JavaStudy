package tutoring.javastudy.exception.handler;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tutoring.javastudy.exception.BaseException;
import tutoring.javastudy.exception.response.ExceptionResponse;

@Slf4j
@Order(1)
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler
    //extends ResponseEntityExceptionHandler
{
    
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        log.error("handleException",ex);
        ExceptionResponse response = new ExceptionResponse();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
    
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handleException(
        HttpServletRequest request,
        HttpServletResponse response,
        BaseException exception
    )
    {
        return ResponseEntity.status(exception.getHttpStatus())
                             .body(ExceptionResponse.builder()
                                                    .status(exception.getHttpStatus().value())
                                                    .message(exception.getMessage())
                                                    .build());
    }
    
    @ExceptionHandler(BindException.class)
    public Map<String, String> handleValidationException(HttpServletResponse res, BindException exception)
        throws IOException
    {
        res.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "validation");
        Map<String, String> errorAttributes = new HashMap<>();
        errorAttributes.put("code", "BOARD_NOT_FOUND");
        errorAttributes.put("message", exception.getMessage());
        return errorAttributes;
    }
    
    /*@ExceptionHandler(value
        = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
        RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }*/
}
