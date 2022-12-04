package tutoring.javastudy.exception.controller;

import java.util.List;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;

public class ExceptionController extends AbstractErrorController {
    
    public ExceptionController(ErrorAttributes errorAttributes)
    {
        super(errorAttributes);
    }
    
    public ExceptionController(
        ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers
    )
    {
        super(errorAttributes, errorViewResolvers);
    }
}
