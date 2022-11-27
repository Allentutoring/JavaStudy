package tutoring.Project.auth.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.auth.entity.User;
import tutoring.Project.auth.role.IsCurrentUser;
import tutoring.Project.auth.service.UserService;

@Slf4j
//@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/user")
    public ResponseEntity<Optional<User>> info() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }
    
    // @PreAuthorize("isAuthenticated() && #user.id == authentication.principal.id")
    @IsCurrentUser
    @GetMapping("/user/{user}")
    public ResponseEntity<User> othersInfo(
        Authentication authentication,
        @PathVariable("user") User user
    ) {
        
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/sign/up")
    public ResponseEntity<User> signUp(UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }
    
    @DeleteMapping("/user")
    public void withdraw() {
        userService.withdraw();
    }
}
