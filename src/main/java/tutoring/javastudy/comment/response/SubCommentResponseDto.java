package tutoring.javastudy.comment.response;

import lombok.Data;
import tutoring.javastudy.base.dto.BaseResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
public class SubCommentResponseDto extends BaseResponseDto<Comment> {

    protected long id;
    protected String content;
    protected String createdAt;
    public SubCommentResponseDto(Comment entity) {
        super(entity);
    }

    public void bindEntity(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
    }

}
