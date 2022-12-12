package tutoring.javastudy.auth.jwt.filter;

import static tutoring.javastudy.exception.attribute.GlobalExceptionAttribute.ERROR_INTERNAL_ATTRIBUTE;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import tutoring.javastudy.auth.jwt.exception.InvalidJwtTokenException;
import tutoring.javastudy.exception.attribute.GlobalExceptionAttribute;

@Slf4j
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {
    
    private final ErrorAttributeOptions errorAttributeOptions;
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        FilterChain filterChain
    )
        throws IOException, ServletException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (InvalidJwtTokenException e) {
            setErrorResponse(httpServletRequest, httpServletResponse, e);
        }
    }
    
    public void setErrorResponse(
        HttpServletRequest request, HttpServletResponse response, Throwable throwable
    )
        throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        request.setAttribute(ERROR_INTERNAL_ATTRIBUTE, throwable);
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED.value());
        GlobalExceptionAttribute attribute = new GlobalExceptionAttribute();
        WebRequest webRequest = new ServletWebRequest(request);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(
            response.getOutputStream(),
            attribute.getErrorAttributes(webRequest, errorAttributeOptions)
        );
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
