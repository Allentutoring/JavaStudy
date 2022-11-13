package tutoring.Project.auth.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutoring.Project.auth.entity.Role;
import tutoring.Project.auth.repository.RoleRepository;
import tutoring.Project.auth.repository.UserRepository;
import tutoring.Project.auth.entity.User;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.util.Converter;
import tutoring.Project.util.SecurityUtil;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Converter converter;


    @Transactional
    public User signup(UserDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 등록되어 있는 유저입니다.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        /*Users user = Users.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .enabled(true)
                .build();*/

        User user = new User();
        converter.convertDtoToEntity(dto, user);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);

        List<Role> roles = new ArrayList<>();
        Role roleUser = roleRepository.findByName("USER");
        Role roleADMIN = roleRepository.findByName("ADMIN");
        roles.add(roleUser);
        roles.add(roleADMIN);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public void withdraw() {
        Optional<User> user = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByEmail);
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