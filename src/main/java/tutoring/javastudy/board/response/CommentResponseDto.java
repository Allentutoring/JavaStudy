package tutoring.javastudy.board.response;

import lombok.Data;
import tutoring.javastudy.base.response.BaseResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
public class CommentResponseDto extends BaseResponseDto<Comment> {
    
    protected long id;
    protected String content;
    protected String createdAt;
    protected UserResponseDto user;
    
    public CommentResponseDto(Comment entity)
    {
        super(entity);
    }
    
    public void bindEntity(Comment entity)
    {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.user = new UserResponseDto(entity.getUser());
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
    }
}
