package tutoring.javastudy.exception.handler;


import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tutoring.javastudy.exception.BaseException;
import tutoring.javastudy.exception.response.ExceptionResponse;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends BaseExceptionHandler
    //extends ResponseEntityExceptionHandler
{
    
    public GlobalExceptionHandler(ErrorAttributes errorAttributes, ErrorAttributeOptions options)
    {
        super(errorAttributes, options);
    }
    
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        log.error("handleException",ex);
        ExceptionResponse response = new ExceptionResponse();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
    
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ExceptionResponse> handleException(
        HttpServletRequest request, HttpServletResponse response, BaseException exception
    )
    {
        return ResponseEntity.status(exception.getHttpStatus())
                             .body(ExceptionResponse.builder()
                                                    .status(exception.getHttpStatus().value())
                                                    .message(exception.getMessage())
                                                    .build());
    }
    
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(
        HttpServletRequest request, HttpServletResponse response, BindException exception
    )
    {
        Map<String, Object> attributes = this.getErrorAttributes(request, options);
        return this.response(ExceptionResponse.builder()
                                              .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                              .path((String) attributes.get("path"))
                                              .message(Objects.requireNonNull(exception.getBindingResult()
                                                                                       .getFieldError())
                                                              .getDefaultMessage())
                                              .build());
    }
    
    /*@ExceptionHandler(value
        = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
        RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }*/
}
