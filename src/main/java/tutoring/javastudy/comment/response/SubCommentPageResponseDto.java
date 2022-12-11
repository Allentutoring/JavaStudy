package tutoring.javastudy.comment.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageImpl;
import tutoring.javastudy.base.response.BasePageResponseDto;
import tutoring.javastudy.comment.entity.Comment;

@Data
@SuperBuilder
public class SubCommentPageResponseDto extends BasePageResponseDto<Comment, SubCommentResponseDto> {
    
    public SubCommentPageResponseDto(PageImpl<Comment> pageImpl)
    {
        super(pageImpl);
    }
    
    @Override
    public SubCommentResponseDto bindEntity(Comment entity)
    {
        return new SubCommentResponseDto(entity);
    }
}
