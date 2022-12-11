package tutoring.javastudy.exception.handler;


import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tutoring.javastudy.auth.jwt.exception.InvalidJwtTokenException;
import tutoring.javastudy.exception.BaseException;

@Slf4j
// @RestControllerAdvice(annotations = {RestController.class, Component.class})
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler
    //extends ResponseEntityExceptionHandler
{
    
    public GlobalExceptionHandler(ErrorAttributes errorAttributes, ErrorAttributeOptions options)
    {
        super(errorAttributes, options);
    }
    
    @ExceptionHandler(Exception.class)
    public void handleException(
        HttpServletRequest request, HttpServletResponse response, Exception exception
    )
    throws IOException
    {
        response.sendError(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }
    
    @ExceptionHandler(value = {BaseException.class})
    public void handleException(
        HttpServletRequest request, HttpServletResponse response, BaseException exception
    )
    throws IOException
    {
        response.sendError(exception.getHttpStatus().value(), exception.getMessage());
    }
    
    @ExceptionHandler(value = {InvalidJwtTokenException.class})
    public void handleInvalidException(
        HttpServletRequest request, HttpServletResponse response, BaseException exception
    )
    throws IOException
    {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
    }
    
    @ExceptionHandler(value = BindException.class)
    public void handleValidationException(
        HttpServletRequest request, HttpServletResponse response, BindException exception
    )
    throws IOException
    {
        response.sendError(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage()
        );
    }
    
    @ExceptionHandler(value = MissingPathVariableException.class)
    public void handleNotFoundException(
        HttpServletRequest request,
        HttpServletResponse response,
        MissingPathVariableException exception
    )
    throws IOException
    {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
