package tutoring.Project.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tutoring.Project.auth.dto.UserDto;
import tutoring.Project.auth.entity.User;
import tutoring.Project.auth.exception.UserNotFoundException;
import tutoring.Project.auth.jwt.JwtTokenProvider;
import tutoring.Project.auth.repository.UserRepository;
import tutoring.Project.base.controller.BaseController;
import tutoring.Project.util.SecurityUtil;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/sign/in")
    public ResponseEntity<String> signIn(UserDto dto) throws UserNotFoundException {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
    }

    @GetMapping("/sign/out")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtil.signOut(request, response);
        return "redirect:/";
    }

}
