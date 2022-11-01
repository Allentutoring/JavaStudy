package tutoring.Project.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tutoring.Project.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "id", length = 50, unique = true, nullable = false)
    private String id;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

}
