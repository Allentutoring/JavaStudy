package tutoring.javastudy.comment.request;

import lombok.Data;
import lombok.ToString;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.dto.BaseRequestDto;
import tutoring.javastudy.board.entity.Board;
import tutoring.javastudy.comment.entity.Comment;

@ToString
@Data
public class SubCommentRequestDto extends BaseRequestDto {

    private String title;
    private String content;
    private User user;
    private Board board;
    private Comment comment;
}