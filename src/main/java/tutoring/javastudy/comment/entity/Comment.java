package tutoring.javastudy.comment.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.board.entity.Board;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
    
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    @NotNull
    @ManyToOne()
    private Board board;
    
    @NotNull
    @ManyToOne()
    private User user;
    
    @NotNull
    @OneToMany()
    private List<Comment> comments;
}
