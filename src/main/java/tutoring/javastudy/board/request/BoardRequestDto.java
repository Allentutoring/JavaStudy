package tutoring.javastudy.board.request;

import lombok.Data;
import lombok.ToString;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.request.BaseRequestDto;

@ToString
@Data
public class BoardRequestDto extends BaseRequestDto {
    
    private String title;
    private String content;
    private User user;
}
