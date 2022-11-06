package tutoring.Project.auth;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tutoring.Project.base.BaseController;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(UserDto userDto) {
        System.out.println("sign up store");
        System.out.println(userDto.toString());
        return ResponseEntity.ok(userService.signup(userDto));
    }

}
