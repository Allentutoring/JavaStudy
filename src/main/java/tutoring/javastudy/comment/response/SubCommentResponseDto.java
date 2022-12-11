package tutoring.javastudy.comment.response;

import lombok.Data;
import tutoring.javastudy.base.response.BaseResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
public class SubCommentResponseDto extends BaseResponseDto<Comment> {
    
    protected long id;
    protected String content;
    protected String createdAt;
    protected ParentCommentResponseDto parent;
    
    public SubCommentResponseDto(Comment entity)
    {
        super(entity);
    }
    
    public void bindEntity(Comment entity)
    {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
        this.parent = new ParentCommentResponseDto(entity.getParent());
    }
    
    public static class CommentResponseDto extends BaseResponseDto<Comment> {
        
        protected long id;
        protected String content;
        protected String createdAt;
        
        public CommentResponseDto(Comment entity)
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
    
}
