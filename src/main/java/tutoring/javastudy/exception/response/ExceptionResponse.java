package tutoring.javastudy.exception.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ExceptionResponse {
    
    private int status;
    private String message;
    private String path;
    private Date timestamp;
}
