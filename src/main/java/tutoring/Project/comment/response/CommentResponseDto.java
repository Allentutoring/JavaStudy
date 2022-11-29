package tutoring.Project.comment.response;

import lombok.Data;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.board.entity.Board;

@Data
public class CommentResponseDto extends BaseResponseDto<Board> {

    protected long id;
    protected String title;
    protected String content;
    protected String createdAt;


    public void bindEntity(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
    }
}
