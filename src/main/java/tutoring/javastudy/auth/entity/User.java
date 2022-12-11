package tutoring.javastudy.auth.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tutoring.javastudy.base.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity implements UserDetails {
    
    @NotBlank
    @Email
    @Column(name = "email", length = 32, unique = true, nullable = false)
    private String email;
    
    @NotBlank
    @Column(name = "password", length = 256, nullable = false)
    private String password;
    
    @NotBlank
    @Column(name = "nickname", length = 16, nullable = false)
    private String nickname;
    @Column(name = "enabled")
    private boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return roles.stream()
                    .map((role -> role.getPrivileges()))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
    }
    
    @Override
    public String getPassword()
    {
        return this.password;
    }
    
    @Override
    public String getUsername()
    {
        return this.email;
    }
    
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isEnabled()
    {
        return this.enabled;
    }
    
}
