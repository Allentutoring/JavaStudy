package tutoring.javastudy.comment.request;

import lombok.Data;
import lombok.ToString;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.dto.BaseRequestDto;
import tutoring.javastudy.board.entity.Board;

@ToString
@Data
public class CommentRequestDto extends BaseRequestDto {

    private String title;
    private String content;
    private User user;
    private Board board;
}
