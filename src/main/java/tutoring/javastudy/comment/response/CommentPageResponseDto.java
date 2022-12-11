package tutoring.javastudy.comment.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageImpl;
import tutoring.javastudy.base.response.BasePageResponseDto;
import tutoring.javastudy.board.response.CommentResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
@SuperBuilder
public class CommentPageResponseDto extends BasePageResponseDto<Comment, CommentResponseDto> {
    
    public CommentPageResponseDto(PageImpl<Comment> pageImpl)
    {
        super(pageImpl);
    }
    
    @Override
    public CommentResponseDto bindEntity(Comment entity)
    {
        return new CommentResponseDto(entity);
    }
}
