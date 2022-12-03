package tutoring.javastudy.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private int status;
    private String message;
    private String path;
}
