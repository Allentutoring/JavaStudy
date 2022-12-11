package tutoring.javastudy.board.service;

import org.springframework.stereotype.Service;
import tutoring.javastudy.base.service.BaseService;
import tutoring.javastudy.board.entity.Board;
import tutoring.javastudy.board.repository.BoardRepository;

@Service
public class BoardService extends BaseService<Board, BoardRepository> {
    
    public BoardService(BoardRepository repository)
    {
        super(repository);
    }
    
}
