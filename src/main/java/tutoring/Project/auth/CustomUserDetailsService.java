package tutoring.Project.auth;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug(email);
        final Optional<Users> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) throw new UsernameNotFoundException("User not found");
        Users user = userEntity.get();

        return Users.builder()
                .email(email)
                .password(user.getPassword())
                .nickname(user.getNickname())
                .enabled(true)
                .build();
    }

}