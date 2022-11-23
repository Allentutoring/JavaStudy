package tutoring.Project.board.response;

import lombok.Data;
import tutoring.Project.base.dto.BaseResponseDto;
import tutoring.Project.board.entity.Board;

@Data
public class BoardResponseDto extends BaseResponseDto<Board> {

    private long id;
    private String title;
    private String content;


    @Override
    public void bindEntity(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
