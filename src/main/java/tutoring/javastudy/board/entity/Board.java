package tutoring.javastudy.board.entity;

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
import lombok.ToString;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.entity.BaseEntity;
import tutoring.javastudy.comment.entity.Comment;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {
    
    @Column(name = "title", length = 256, nullable = false)
    private String title;
    
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @NotNull
    @ManyToOne()
    private User user;
    
    @OneToMany()
    private List<Comment> comments;
}
