package tutoring.Project.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.auth.entity.Role;
import tutoring.Project.auth.entity.User;
import tutoring.Project.auth.repository.RoleRepository;
import tutoring.Project.auth.repository.UserRepository;
import tutoring.Project.util.SecurityUtil;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    @Transactional
    public User signup(UserDto dto) {
        
        if (userRepository.findByEmail(dto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 등록되어 있는 유저입니다.");
        }
        
        List<Role> roles = new ArrayList<>();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        roles.add(roleUser);
        return userRepository.save(User.builder()
                                       .email(dto.getEmail())
                                       .nickname(dto.getNickname())
                                       .password(passwordEncoder.encode(dto.getPassword()))
                                       .enabled(true)
                                       .roles(roles)
                                       .build());
    }
    
    @Transactional
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    
    @Transactional
    public void withdraw() {
        Optional<User> user = SecurityUtil.getCurrentUsername()
                                          .flatMap(userRepository::findByEmail);
        userRepository.delete(user.get());
    }
    
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findByEmail);
    }
}