package tutoring.javastudy.comment.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tutoring.javastudy.base.repository.BaseRepository;
import tutoring.javastudy.comment.entity.Comment;

public interface CommentRepository extends BaseRepository<Comment, Long> {
    
    PageImpl<Comment> findAllByParent(Comment parent, Pageable pageable);
}
