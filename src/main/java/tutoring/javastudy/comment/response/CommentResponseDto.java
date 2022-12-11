package tutoring.javastudy.comment.response;

import java.util.List;
import lombok.Data;
import tutoring.javastudy.base.dto.BaseResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
public class CommentResponseDto extends BaseResponseDto<Comment> {
    
    protected long id;
    protected String content;
    protected String createdAt;
    
    protected UserResponseDto user;
    protected BoardResponseDto board;
    protected List<SubCommentResponseDto> subComments;
    
    public void bindEntity(Comment entity)
    {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
        this.user = new UserResponseDto(entity.getUser());
        this.board = new BoardResponseDto(entity.getBoard());
        if (entity.getSubComments() != null) {
            this.subComments = entity.getSubComments()
                                     .stream()
                                     .map(SubCommentResponseDto::new)
                                     .toList();
        }
    }
    
}
