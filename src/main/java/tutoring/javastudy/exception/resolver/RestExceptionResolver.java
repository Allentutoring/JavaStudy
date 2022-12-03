package tutoring.javastudy.exception.resolver;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Component
public class RestExceptionResolver implements HandlerExceptionResolver, Ordered {
    
    @Nullable
    private List<HandlerExceptionResolver> resolvers;
    
    private int order = Ordered.LOWEST_PRECEDENCE;
    
    @Override
    public ModelAndView resolveException(
        HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    )
    {
        if (this.resolvers != null) {
            for (HandlerExceptionResolver handlerExceptionResolver : this.resolvers) {
                ModelAndView mav = handlerExceptionResolver.resolveException(
                    request,
                    response,
                    handler,
                    ex
                );
                if (mav != null) {
                    return mav;
                }
            }
        }
        return null;
    }
    
    @Override
    public int getOrder()
    {
        return this.order;
    }
}
