package tutoring.javastudy.exception.attribute;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import tutoring.javastudy.exception.BaseException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionAttribute implements ErrorAttributes {
    
    public static final String ERROR_INTERNAL_ATTRIBUTE =
        GlobalExceptionAttribute.class.getName() + ".ERROR";
    
    @Override
    public Map<String, Object> getErrorAttributes(
        WebRequest webRequest, ErrorAttributeOptions options
    ) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date());
        addStatus(errorAttributes, webRequest);
        addErrorMessage(errorAttributes, webRequest);
        addPath(errorAttributes, webRequest);
        return errorAttributes;
    }
    
    protected void addStatus(
        Map<String, Object> errorAttributes, WebRequest webRequest
    ) {
        Integer status = getAttribute(webRequest, RequestDispatcher.ERROR_STATUS_CODE);
        if (status == null) {
            errorAttributes.put("status", 999);
            errorAttributes.put("error", "None");
            return;
        }
        errorAttributes.put("status", status);
        
        try {
            Throwable throwable = getError(webRequest);
            if (throwable instanceof BaseException) {
                errorAttributes.put("status", ((BaseException) throwable).getHttpStatus().value());
            }
            
            errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
        } catch (Exception ex) {
            // Unable to obtain a reason
            errorAttributes.put("error", "Http Status " + status);
        }
    }
    
    protected void addErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest) {
        Throwable error = getError(webRequest);
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
            errorAttributes.put("exception", error.getClass().getName());
        }
        addExceptionErrorMessage(errorAttributes, webRequest, error);
    }
    
    protected void addExceptionErrorMessage(
        Map<String, Object> errorAttributes, WebRequest webRequest, Throwable error
    ) {
        errorAttributes.put("message", getMessage(webRequest, error));
    }
    
    /**
     * Returns the message to be included as the value of the {@code message} error attribute. By
     * default the returned message is the first of the following that is not empty:
     * <ol>
     * <li>Value of the {@link RequestDispatcher#ERROR_MESSAGE} request attribute.
     * <li>Message of the given {@code error}.
     * <li>{@code No message available}.
     * </ol>
     *
     * @param webRequest current request
     * @param error      current error, if any
     * @return message to include in the error attributes
     * @since 2.4.0
     */
    protected String getMessage(WebRequest webRequest, Throwable error) {
        Object message = getAttribute(webRequest, RequestDispatcher.ERROR_MESSAGE);
        if (!ObjectUtils.isEmpty(message)) {
            return message.toString();
        }
        if (error != null) {
            if (StringUtils.hasLength(error.getMessage())) {
                return error.getMessage();
            } else if (StringUtils.hasLength(error.getLocalizedMessage())) {
                return error.getLocalizedMessage();
            }
        }
        return "No message available";
        
    }
    
    protected void addPath(
        Map<String, Object> errorAttributes, RequestAttributes requestAttributes
    ) {
        String path = getAttribute(requestAttributes, RequestDispatcher.ERROR_REQUEST_URI);
        if (path != null) {
            errorAttributes.put("path", path);
        }
    }
    
    @Override
    public Throwable getError(WebRequest webRequest) {
        Throwable exception = getAttribute(webRequest, ERROR_INTERNAL_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(webRequest, RequestDispatcher.ERROR_EXCEPTION);
        }
        // store the exception in a well-known attribute to make it available to metrics
        // instrumentation.
        webRequest.setAttribute(ErrorAttributes.ERROR_ATTRIBUTE,
                                exception,
                                WebRequest.SCOPE_REQUEST
        );
        return exception;
    }
    
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
