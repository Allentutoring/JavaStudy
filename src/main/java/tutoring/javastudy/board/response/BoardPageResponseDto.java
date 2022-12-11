package tutoring.javastudy.board.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageImpl;
import tutoring.javastudy.base.response.BasePageResponseDto;
import tutoring.javastudy.board.entity.Board;

@Data
@SuperBuilder
public class BoardPageResponseDto extends BasePageResponseDto<Board, BoardResponseDto> {
    
    public BoardPageResponseDto(PageImpl<Board> pageImpl)
    {
        super(pageImpl);
    }
    
    @Override
    public BoardResponseDto bindEntity(Board entity)
    {
        return new BoardResponseDto(entity);
    }
}
