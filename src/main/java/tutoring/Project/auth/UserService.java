package tutoring.Project.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutoring.Project.util.SecurityUtil;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    @Transactional
    public User signup(UserDto userDto) {

        if (userRepository.findOneWithAuthoritiesById(userDto.getId()).orElse(null) != null) {
            throw new RuntimeException("이미 등록되어 있는 유저입니다.");
        }

        User user = User.builder()
                .id(userDto.getId())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .build();
        userRepository.delete(user);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesById);
    }
}