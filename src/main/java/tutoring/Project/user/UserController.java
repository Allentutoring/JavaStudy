package tutoring.Project.user;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutoring.Project.auth.User;
import tutoring.Project.auth.UserDto;
import tutoring.Project.auth.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Optional<User>> info() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @DeleteMapping("/user")
    public void withdraw() {
        userService.withdraw();
    }
}
