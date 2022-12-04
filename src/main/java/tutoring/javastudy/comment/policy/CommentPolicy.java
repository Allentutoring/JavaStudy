package tutoring.javastudy.comment.policy;

import org.springframework.stereotype.Service;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.permission.impl.Policy;
import tutoring.javastudy.board.entity.Board;
import tutoring.javastudy.comment.entity.Comment;

@Service("commentPolicy")
public class CommentPolicy implements Policy {
    
    public boolean index(User user, Board board)
    {
        return true;
    }
    
    public boolean store(User user, Board board)
    {
        return true;
    }
    
    public boolean update(User user, Board board, Comment comment)
    {
        return comment.getUser().getId().equals(user.getId());
    }
    
    public boolean delete(User user, Board board, Comment comment)
    {
        return comment.getUser().getId().equals(user.getId());
    }
}
