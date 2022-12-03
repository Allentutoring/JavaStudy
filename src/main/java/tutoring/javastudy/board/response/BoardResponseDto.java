package tutoring.javastudy.board.response;

import lombok.Data;
import tutoring.javastudy.base.dto.BaseResponseDto;
import tutoring.javastudy.board.entity.Board;

@Data
public class BoardResponseDto extends BaseResponseDto<Board> {
    
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