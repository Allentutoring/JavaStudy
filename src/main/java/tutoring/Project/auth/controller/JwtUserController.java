package tutoring.Project.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.auth.entity.User;
import tutoring.Project.auth.role.IsCurrentUser;
import tutoring.Project.auth.service.UserService;
import tutoring.Project.base.controller.BaseController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class JwtUserController extends BaseController<User> {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Optional<User>> info() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    // @PreAuthorize("isAuthenticated() && #user.id == authentication.principal.id")
    @IsCurrentUser
    @GetMapping("/{user}")
    public ResponseEntity<User> show(@PathVariable("user") User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> signUp(UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @DeleteMapping()
    public void withdraw() {
        userService.withdraw();
    }
}
