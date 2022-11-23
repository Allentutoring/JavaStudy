package tutoring.Project.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.base.controller.ResourcesController;
import tutoring.Project.board.entity.Board;
import tutoring.Project.board.request.BoardRequestDto;
import tutoring.Project.board.response.BoardResponseDto;
import tutoring.Project.board.service.BoardService;
import tutoring.Project.util.Converter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController extends ResourcesController<Board, BoardRequestDto, BoardResponseDto> {

    private final Converter converter;
    private final BoardService service;

    /*@IsCurrentEntity
    @GetMapping("/{entity}")
    public ResponseEntity<BoardResponseDto> show(@PathVariable("entity") Board entity) {
        BoardResponseDto response = new BoardResponseDto();
        response.bindEntity(entity);
        return ResponseEntity.ok(response);
    }*/

    @Override
    protected BoardService getService() {
        return service;
    }

    @Override
    protected Converter getConverter() {
        return converter;
    }
}
