package tutoring.javastudy.exception.handler;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import tutoring.javastudy.exception.response.ExceptionResponse;

@Slf4j
@RequiredArgsConstructor
public class BaseExceptionHandler {
    
    protected final ErrorAttributes errorAttributes;
    protected final ErrorAttributeOptions options;
    
    protected Map<String, Object> getErrorAttributes(
        HttpServletRequest request, ErrorAttributeOptions options
    )
    {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, options);
    }
    
    protected ResponseEntity<ExceptionResponse> response(ExceptionResponse exceptionResponse)
    {
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }
}
