package tutoring.javastudy.comment.service;

import org.springframework.stereotype.Service;
import tutoring.javastudy.base.service.BaseService;
import tutoring.javastudy.comment.entity.Comment;
import tutoring.javastudy.comment.repository.CommentRepository;

@Service
public class CommentService extends BaseService<Comment, CommentRepository> {
    
    public CommentService(CommentRepository repository)
    {
        super(repository);
    }
}
