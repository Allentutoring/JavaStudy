package tutoring.javastudy.comment.response;

import lombok.Data;
import tutoring.javastudy.base.response.BaseResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
public class ParentCommentResponseDto extends BaseResponseDto<Comment> {
    
    protected long id;
    protected String content;
    protected String createdAt;
    
    public ParentCommentResponseDto(Comment entity)
    {
        super(entity);
    }
    
    @Override
    public void bindEntity(Comment entity)
    {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
    }
    
}
