package tutoring.javastudy.auth.jwt.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidJwtTokenException extends RuntimeException {
    
    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
