package tutoring.Project.auth;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug(email);
        final Optional<User> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) throw new UsernameNotFoundException("User not found");
        User user = userEntity.get();

        return User.builder()
                .email(email)
                .password(user.getPassword())
                .nickname(user.getNickname())
                .enabled(true)
                .build();
    }

    public Collection<GrantedAuthority> getAuthorities(String email) {
        List<Authority> authList = authoritiesRepository.findByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authority authority : authList) {
            authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return authorities;
    }
}