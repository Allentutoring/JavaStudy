package tutoring.javastudy.comment.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.validation.Valid;
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
import tutoring.javastudy.comment.entity.Comment;
import tutoring.javastudy.comment.repository.CommentRepository;
import tutoring.javastudy.comment.request.CommentRequestDto;
import tutoring.javastudy.comment.response.CommentResponseDto;
import tutoring.javastudy.comment.service.CommentService;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/{board}/comment")
public class CommentController extends ResourcesController<Comment, CommentRepository> {
    
    private final Convertable converter;
    private final CommentService service;
    
    @GetMapping()
    @PreAuthorize("@commentPolicy.index(#user, #board)")
    public ResponseEntity<List<CommentResponseDto>> index(
        @AuthenticationPrincipal User user, @PathVariable("board") Board board
    )
    {
        return super.index(CommentResponseDto.class);
    }
    
    @GetMapping("/{comment}")
    public ResponseEntity<CommentResponseDto> show(@PathVariable("comment") Comment entity)
    throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        return super.show(entity, CommentResponseDto.class);
    }
    
    @Transactional
    @PreAuthorize("hasPermission('comment', 'store') and @commentPolicy.store(#user, #board)")
    @PostMapping()
    public ResponseEntity<CommentResponseDto> store(
        @Valid CommentRequestDto request,
        @PathVariable("board") Board board,
        @AuthenticationPrincipal User user
    )
    throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        request.setBoard(board);
        request.setUser(user);
        return super.store(Comment.class, request, CommentResponseDto.class);
    }
    
    @Transactional
    @PreAuthorize("hasPermission('comment', 'update') and @commentPolicy.update(#user, #board, #comment)")
    @PutMapping("/{comment}")
    public ResponseEntity<CommentResponseDto> update(
        @AuthenticationPrincipal User user,
        @PathVariable("board") Board board,
        @PathVariable("comment") Comment comment,
        @Valid CommentRequestDto request
    )
    throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        return super.update(comment, request, CommentResponseDto.class);
    }
    
    @Transactional
    @PreAuthorize("hasPermission('comment', 'delete') and @commentPolicy.delete(#user, #board, #comment)")
    @DeleteMapping("/{comment}")
    public ResponseEntity<CommentResponseDto> delete(
        @AuthenticationPrincipal User user,
        @PathVariable("board") Board board,
        @PathVariable("comment") Comment comment
    )
    throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException
    {
        return super.delete(comment, CommentResponseDto.class);
    }
    
    @Override
    protected CommentService getService()
    {
        return service;
    }
    
    protected Convertable getConverter()
    {
        return converter;
    }
}
