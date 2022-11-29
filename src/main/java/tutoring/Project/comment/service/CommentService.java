package tutoring.Project.comment.service;

import org.springframework.stereotype.Service;
import tutoring.Project.base.service.BaseService;
import tutoring.Project.comment.entity.Comment;
import tutoring.Project.comment.repository.CommentRepository;

@Service
public class CommentService extends BaseService<Comment, CommentRepository> {

    public CommentService(CommentRepository repository) {
        super(repository);
    }
}
