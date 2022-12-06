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
import tutoring.javastudy.comment.request.SubCommentRequestDto;
import tutoring.javastudy.comment.response.SubCommentResponseDto;
import tutoring.javastudy.comment.service.CommentService;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/{board}/comment/{comment}/sub-comment")
public class SubCommentController extends ResourcesController<Comment, CommentRepository> {

    private final Convertable converter;
    private final CommentService service;

    @GetMapping()
    @PreAuthorize("@subCommentPolicy.index(#user, #board, #comment)")
    public ResponseEntity<List<SubCommentResponseDto>> index(
        @AuthenticationPrincipal User user, @PathVariable("board") Board board,
        @PathVariable("comment") Comment comment
    ) {
        return super.index(SubCommentResponseDto.class);
    }

    @GetMapping("/{subComment}")
    public ResponseEntity<SubCommentResponseDto> show(@PathVariable("subComment") Comment entity)
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.show(entity, SubCommentResponseDto.class);
    }

    @Transactional
    @PreAuthorize("hasPermission('comment', 'store') and @subCommentPolicy.store(#user, #board, #comment)")
    @PostMapping()
    public ResponseEntity<SubCommentResponseDto> store(
        @Valid SubCommentRequestDto request,
        @PathVariable("board") Board board,
        @PathVariable("comment") Comment comment,
        @AuthenticationPrincipal User user
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        request.setBoard(board);
        request.setUser(user);
        return super.store(Comment.class, request, SubCommentResponseDto.class);
    }

    @Transactional
    @PreAuthorize("hasPermission('comment', 'update') and @subCommentPolicy.update(#user, #board, #comment, #subComment)")
    @PutMapping("/{subComment}")
    public ResponseEntity<SubCommentResponseDto> update(
        @AuthenticationPrincipal User user,
        @PathVariable("board") Board board,
        @PathVariable("comment") Comment comment,
        @PathVariable("subComment") Comment subComment,
        @Valid SubCommentRequestDto request
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.update(comment, request, SubCommentResponseDto.class);
    }

    @Transactional
    @PreAuthorize("hasPermission('comment', 'delete') and @subCommentPolicy.delete(#user, #board, #comment, #subComment)")
    @DeleteMapping("/{subComment}")
    public ResponseEntity<SubCommentResponseDto> delete(
        @AuthenticationPrincipal User user,
        @PathVariable("board") Board board,
        @PathVariable("comment") Comment comment,
        @PathVariable("subComment") Comment subComment
    )
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return super.delete(comment, SubCommentResponseDto.class);
    }

    @Override
    protected CommentService getService() {
        return service;
    }

    protected Convertable getConverter() {
        return converter;
    }
}
