package tutoring.Project.board.entity;

import lombok.*;
import tutoring.Project.auth.entity.User;
import tutoring.Project.base.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Column(name = "title", length = 256)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
