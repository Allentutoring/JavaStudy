package tutoring.javastudy.board.policy;

import org.springframework.stereotype.Service;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.permission.impl.Policy;
import tutoring.javastudy.board.entity.Board;

@Service("boardPolicy")
public class BoardPolicy implements Policy {
    
    public boolean store()
    {
        return true;
    }
    
    public boolean update(User user, Board board)
    {
        return board.getUser().getId().equals(user.getId());
    }
    
    public boolean delete(User user, Board board)
    {
        return board.getUser().getId().equals(user.getId());
    }
}
