package tutoring.Project.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tutoring.Project.auth.entity.User;
import tutoring.Project.base.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {
    
    @NotBlank
    @Column(name = "title", length = 256)
    private String title;
    
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    @NotNull
    @ManyToOne()
    private User user;
}
