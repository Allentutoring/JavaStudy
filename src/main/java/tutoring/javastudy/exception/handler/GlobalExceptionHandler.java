package tutoring.javastudy.exception.handler;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import tutoring.javastudy.exception.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultErrorAttributes {
    
    @Override
    public Map<String, Object> getErrorAttributes(
        WebRequest webRequest,
        ErrorAttributeOptions options
    )
    {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.remove("trace");
        return errorAttributes;
    }
    
    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res, Exception exception) throws IOException
    {
        res.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    
    @ExceptionHandler(CustomException.class)
    public void handleCustomException(
        HttpServletResponse res, CustomException ex
    ) throws IOException
    {
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse res) throws IOException
    {
        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    }
    
    @ExceptionHandler(ValidationException.class)
    public void handleValidationException(HttpServletResponse res, Exception exception) throws IOException
    {
        res.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage());
    }
}
