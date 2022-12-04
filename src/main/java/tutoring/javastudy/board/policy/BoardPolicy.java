package tutoring.javastudy.board.policy;

import org.springframework.stereotype.Service;
import tutoring.javastudy.base.permission.impl.Policy;

@Service("boardPolicy")
public class BoardPolicy implements Policy {
    
    public boolean store()
    {
        return true;
    }
}
