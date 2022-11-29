package tutoring.Project.comment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tutoring.Project.auth.entity.User;
import tutoring.Project.base.entity.BaseEntity;
import tutoring.Project.board.entity.Board;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne()
    private Board board;

    @ManyToOne()
    private User user;
}
