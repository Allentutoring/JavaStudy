package tutoring.javastudy.comment.response;

import lombok.Data;
import tutoring.javastudy.base.dto.BaseResponseDto;
import tutoring.javastudy.board.entity.Board;

@Data
public class BoardResponseDto extends BaseResponseDto<Board> {
    
    protected long id;
    protected String title;
    protected String content;
    protected String createdAt;
    
    protected UserResponseDto user;
    
    public BoardResponseDto(Board entity)
    {
        super(entity);
    }
    
    public void bindEntity(Board entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
        this.user = new UserResponseDto(entity.getUser());
    }
}
