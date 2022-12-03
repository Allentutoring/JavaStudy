package tutoring.javastudy.exception.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionResponse {
    private int status;
    private String message;
    private String path;
}
