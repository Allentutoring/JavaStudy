package tutoring.javastudy.comment.response;

import lombok.Data;
import tutoring.javastudy.base.dto.BaseResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
public class CommentResponseDto extends BaseResponseDto<Comment> {
    
    protected long id;
    protected String content;
    protected String createdAt;
    
    protected UserResponseDto user = new UserResponseDto();
    protected BoardResponseDto board = new BoardResponseDto();
    
    public void bindEntity(Comment entity)
    {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
        this.user.bindEntity(entity.getUser());
        this.board.bindEntity(entity.getBoard());
    }
    
}
