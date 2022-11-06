package tutoring.Project.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tutoring.Project.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "authority")
    private String authority;
}