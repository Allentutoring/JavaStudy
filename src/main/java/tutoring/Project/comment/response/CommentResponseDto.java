package tutoring.Project.comment.response;

import lombok.Data;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.comment.entity.Comment;

@Data
public class CommentResponseDto extends BaseResponseDto<Comment> {

    protected long id;
    protected String content;
    protected String createdAt;


    public void bindEntity(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
    }
}
