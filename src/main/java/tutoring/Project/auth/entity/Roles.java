package tutoring.Project.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tutoring.Project.auth.Role;
import tutoring.Project.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 36)
    private Role name;
}
