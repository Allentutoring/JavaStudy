package tutoring.Project.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.base.controller.BaseController;
import tutoring.Project.board.entity.Board;
import tutoring.Project.board.service.BoardService;
import tutoring.Project.util.Converter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController extends BaseController<Board> {
    private final Converter converter;
    private final BoardService service;


    @Override
    protected BoardService getService() {
        return service;
    }

    @Override
    protected Converter getConverter() {
        return converter;
    }
}
