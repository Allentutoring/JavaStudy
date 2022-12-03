package tutoring.javastudy.exception.resolver;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {
    
    @Override
    protected ModelAndView doResolveException(
        HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    )
    {
        try {
            if (ex instanceof IllegalArgumentException) {
                return handleIllegalArgument((IllegalArgumentException) ex, response, handler);
            }
        } catch (Exception handlerException) {
            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }
        return null;
    }
    
    private ModelAndView handleIllegalArgument(
        IllegalArgumentException ex, HttpServletResponse response, Object handler
    ) throws IOException
    {
        ModelAndView modelAndView = new ModelAndView();
        response.sendError(HttpServletResponse.SC_CONFLICT);
        return modelAndView;
    }
}
