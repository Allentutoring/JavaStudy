package tutoring.Project.auth.controller;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.auth.entity.Users;
import tutoring.Project.auth.service.UserService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Optional<Users>> info() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signUp(UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @DeleteMapping("/user")
    public void withdraw() {
        userService.withdraw();
    }
}
