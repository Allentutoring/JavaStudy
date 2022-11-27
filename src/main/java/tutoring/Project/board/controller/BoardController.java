package tutoring.Project.board.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.auth.entity.User;
import tutoring.Project.auth.role.IsCurrentEntity;
import tutoring.Project.base.controller.ResourcesController;
import tutoring.Project.board.entity.Board;
import tutoring.Project.board.request.BoardRequestDto;
import tutoring.Project.board.response.BoardResponseDto;
import tutoring.Project.board.service.BoardService;
import tutoring.Project.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController extends ResourcesController<Board> {
    
    private final Convertable convertable;
    private final BoardService service;
    
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> info() {
        return super.info(BoardResponseDto.class);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> show(
        @PathVariable("id") Board entity
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.show(entity, BoardResponseDto.class);
    }
    
    @Transactional()
    @PreAuthorize("hasPermission(#board, 'write')")
    @PostMapping
    public ResponseEntity<BoardResponseDto> store(
        BoardRequestDto board,
        @AuthenticationPrincipal User user
    )
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        board.setUser(user);
        return super.store(Board.class, board, BoardResponseDto.class);
    }
    
    @Transactional
    @IsCurrentEntity
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> update(
        @PathVariable("id") Board entity,
        BoardRequestDto request
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.update(entity, request, BoardResponseDto.class);
    }
    
    @Transactional
    @IsCurrentEntity
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> delete(@PathVariable("id") Board entity)
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.delete(entity, BoardResponseDto.class);
    }
    
    @Override
    protected BoardService getService() {
        return service;
    }
    
    protected Convertable getConvertable() {
        return convertable;
    }
}
