package tutoring.javastudy.comment.controller;

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
import tutoring.javastudy.comment.entity.Comment;
import tutoring.javastudy.comment.repository.CommentRepository;
import tutoring.javastudy.comment.request.CommentRequestDto;
import tutoring.javastudy.comment.response.CommentResponseDto;
import tutoring.javastudy.comment.service.CommentService;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController extends ResourcesController<Comment, CommentRepository> {

    private final Convertable converter;
    private final CommentService service;

    @GetMapping("/{board}/comment/{comment}")
    public ResponseEntity<List<CommentResponseDto>> info() {
        return super.info(CommentResponseDto.class);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDto> show(@PathVariable("id") Comment entity)
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.show(entity, CommentResponseDto.class);
    }

    @Transactional
    @PreAuthorize("hasPermission(#board, 'comment_write')")
    @PostMapping("/board/{board}/comment")
    public ResponseEntity<CommentResponseDto> store(
        CommentRequestDto request,
        @PathVariable("board") Board board,
        @AuthenticationPrincipal User user
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        request.setBoard(board);
        request.setUser(user);
//        Comment comment = this.converter.convertDtoToEntity(request, Comment.class);
//        this.service.save(comment);
        return super.store(Comment.class, request, CommentResponseDto.class);
    }

    @Transactional
    @PreAuthorize("hasPermission(#comment, 'update')")
    @PutMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDto> update(
        @PathVariable("id") Comment comment,
        CommentRequestDto request
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.update(comment, request, CommentResponseDto.class);
    }

    @Transactional
    @PreAuthorize("hasPermission(#comment, 'delete')")
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDto> delete(@PathVariable("id") Comment comment)
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.delete(comment, CommentResponseDto.class);
    }

    @Override
    protected CommentService getService() {
        return service;
    }

    protected Convertable getConverter() {
        return converter;
    }
}
