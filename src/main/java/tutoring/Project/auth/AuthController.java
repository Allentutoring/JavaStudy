package tutoring.Project.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/signin")
    public String signInPage() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        System.out.println("sign up page");
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(UserDto userDto) {
        System.out.println("sign up store");
        System.out.println(userDto.toString());
        return ResponseEntity.ok(userService.signup(userDto));
    }

}
