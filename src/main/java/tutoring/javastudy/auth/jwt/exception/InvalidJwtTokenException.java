package tutoring.javastudy.auth.jwt.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import tutoring.javastudy.exception.BaseException;

@Getter
@Setter
public class InvalidJwtTokenException extends BaseException {
    
    protected HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    
    public InvalidJwtTokenException(String message)
    {
        this.message = message;
    }
}
