package tutoring.javastudy.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class BaseException extends Exception {
    
    protected String message;
    protected HttpStatus httpStatus;
    
    public BaseException(String message)
    {
        super(message);
    }
    
    public BaseException(String message, HttpStatus httpStatus)
    {
        this(message);
        this.httpStatus = httpStatus;
    }
}
