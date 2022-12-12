package tutoring.javastudy.board.response;

import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;
import tutoring.javastudy.base.response.BaseResponseDto;
import tutoring.javastudy.board.entity.Board;

@Data
public class BoardDetailResponseDto extends BaseResponseDto<Board> {
    
    protected String title;
    protected String content;
    protected BoardPageResponseDto list;
    
    protected List<CommentResponseDto> comments;
    
    public BoardDetailResponseDto(Board board)
    {
        super(board);
    }
    
    public BoardDetailResponseDto(Board board, Page<Board> pageImpl)
    {
        super(board);
        this.list = new BoardPageResponseDto(pageImpl);
    }
    
    @Override
    public void bindEntity(Board entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.comments = entity.getComments()
                              .stream()
                              .map(CommentResponseDto::new)
                              .toList();
        this.createdAt = this.dateTimeUtil.getDateTime(entity.getCreatedAt());
    }
}
