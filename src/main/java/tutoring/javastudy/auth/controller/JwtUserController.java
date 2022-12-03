package tutoring.javastudy.auth.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoring.javastudy.auth.dto.UserDto;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.auth.repository.UserRepository;
import tutoring.javastudy.auth.role.IsCurrentUser;
import tutoring.javastudy.auth.service.UserService;
import tutoring.javastudy.base.controller.BaseController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JwtUserController extends BaseController<User, UserRepository> {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Optional<User>> info() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    // @PreAuthorize("isAuthenticated() && #user.id == authentication.principal.id")
    @IsCurrentUser
    @GetMapping("/user/{user}")
    public ResponseEntity<User> show(@PathVariable("user") User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping("/sign/up")
    public ResponseEntity<User> signUp(UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @DeleteMapping()
    public void withdraw() {
        userService.withdraw();
    }
}
