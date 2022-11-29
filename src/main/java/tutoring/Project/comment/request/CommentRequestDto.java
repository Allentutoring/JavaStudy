package tutoring.Project.comment.request;

import lombok.Data;
import lombok.ToString;
import tutoring.Project.auth.entity.User;
import tutoring.Project.base.dto.BaseRequestDto;
import tutoring.Project.board.entity.Board;

@ToString
@Data
public class CommentRequestDto extends BaseRequestDto {

    private String title;
    private String content;
    private User user;
    private Board board;
}
