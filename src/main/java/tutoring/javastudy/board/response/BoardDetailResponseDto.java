package tutoring.javastudy.board.response;

import java.util.List;
import lombok.Data;
import tutoring.javastudy.base.response.BaseResponseDto;
import tutoring.javastudy.board.entity.Board;

@Data
public class BoardDetailResponseDto extends BaseResponseDto<Board> {
    
    protected String title;
    protected String content;
    
    protected List<CommentResponseDto> comments;
    
    public BoardDetailResponseDto(Board board)
    {
        super(board);
    }
    
    @Override
    public void bindEntity(Board entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        if (entity.getComments() != null) {
            this.comments = entity.getComments().stream().map(CommentResponseDto::new).toList();
        }
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
    }
}
