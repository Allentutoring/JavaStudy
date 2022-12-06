package tutoring.javastudy.comment.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.dto.BaseRequestDto;
import tutoring.javastudy.board.entity.Board;

@ToString
@Data
public class CommentRequestDto extends BaseRequestDto {

    @NotBlank
    private String content;
    private User user;
    private Board board;
}
