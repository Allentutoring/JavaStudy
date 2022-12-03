package tutoring.javastudy.exception.attribute;

import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class GlobalExceptionAttribute extends DefaultErrorAttributes {
    
    @Override
    public Map<String, Object> getErrorAttributes(
        WebRequest webRequest, ErrorAttributeOptions options
    )
    {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.remove("trace");
        // errorAttributes.remove("errors");
        if (errorAttributes.get("errors") != null) {
            errorAttributes.get("errors");
//            Collection<String, Map<String, String>> erros = errorAttributes.get("errors");
//            errorAttributes.put("message", erros.getFieldError().getDefaultMessage());
        }
        
        return errorAttributes;
    }
    
}
