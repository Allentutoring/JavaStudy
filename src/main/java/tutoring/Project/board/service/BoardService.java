package tutoring.Project.board.service;

import org.springframework.stereotype.Service;
import tutoring.Project.base.service.BaseService;
import tutoring.Project.board.entity.Board;
import tutoring.Project.board.repository.BoardRepository;

@Service
public class BoardService extends BaseService<Board, BoardRepository> {

    public BoardService(BoardRepository repository) {
        super(repository);
    }
}
