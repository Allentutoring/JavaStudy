package tutoring.javastudy.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import tutoring.javastudy.base.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Privilege extends BaseEntity implements GrantedAuthority {

    @Column(length = 36)
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
