package tutoring.javastudy.auth.custom.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.auth.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Override
    public User loadUserByUsername(String email)
    throws UsernameNotFoundException
    {
        final Optional<User> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userEntity.get();
        return user;
    }
    
}