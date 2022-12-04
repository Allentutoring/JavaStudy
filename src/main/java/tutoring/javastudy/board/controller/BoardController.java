package tutoring.javastudy.board.controller;

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
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.controller.ResourcesController;
import tutoring.javastudy.board.entity.Board;
import tutoring.javastudy.board.repository.BoardRepository;
import tutoring.javastudy.board.request.BoardRequestDto;
import tutoring.javastudy.board.response.BoardResponseDto;
import tutoring.javastudy.board.service.BoardService;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController extends ResourcesController<Board, BoardRepository> {
    
    private final Convertable convertable;
    private final BoardService service;
    
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> index()
    {
        return super.index(BoardResponseDto.class);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> show(
        @PathVariable("id") Board entity
    )
    throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        return super.show(entity, BoardResponseDto.class);
    }
    
    @Transactional
    @PreAuthorize("hasPermission('board', 'store') and @boardPolicy.store()")
    @PostMapping
    public ResponseEntity<BoardResponseDto> store(
        BoardRequestDto board, @AuthenticationPrincipal User user
    )
    throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        board.setUser(user);
        return super.store(Board.class, board, BoardResponseDto.class);
    }
    
    @Transactional
    // @IsCurrentEntity
    @PreAuthorize("hasPermission('board', 'update') and @boardPolicy.update(#user, #board)")
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> update(
        @AuthenticationPrincipal User user, @PathVariable("id") Board board, BoardRequestDto request
    )
    throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        return super.update(board, request, BoardResponseDto.class);
    }
    
    @Transactional
    @PreAuthorize("hasPermission('board', 'delete') and @boardPolicy.delete(#user, #board)")
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> delete(
        @AuthenticationPrincipal User user, @PathVariable("id") Board board
    )
    throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        return super.delete(board, BoardResponseDto.class);
    }
    
    @Override
    protected BoardService getService()
    {
        return service;
    }
    
    protected Convertable getConverter()
    {
        return convertable;
    }
}
