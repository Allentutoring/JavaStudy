package tutoring.javastudy.comment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import tutoring.javastudy.base.service.BaseService;
import tutoring.javastudy.comment.entity.Comment;
import tutoring.javastudy.comment.repository.CommentRepository;

@Service
public class SubCommentService extends BaseService<Comment, CommentRepository> {

    public SubCommentService(CommentRepository repository) {
        super(repository);
    }

    public List<Comment> index(Comment comment) {
        return comment.getSubComments();
    }
}
