package tutoring.Project.board.response;

import lombok.Data;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.board.entity.Board;
import tutoring.Project.util.DateTimeUtil;

@Data
public class BoardResponseDto extends BaseResponseDto<Board> {
    
    private DateTimeUtil dateTimeUtil;
    
    {
        dateTimeUtil = new DateTimeUtil();
    }
    
    protected long id;
    protected String title;
    protected String content;
    protected String createdAt;
    
    
    @Override
    public void bindEntity(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = dateTimeUtil.getDateTime(entity.getCreatedAt());
    }
}
