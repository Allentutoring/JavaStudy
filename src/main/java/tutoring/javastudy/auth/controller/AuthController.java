package tutoring.javastudy.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tutoring.javastudy.auth.dto.UserDto;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.auth.exception.UserNotFoundException;
import tutoring.javastudy.auth.jwt.JwtTokenProvider;
import tutoring.javastudy.auth.repository.UserRepository;
import tutoring.javastudy.base.controller.BaseController;
import tutoring.javastudy.util.SecurityUtil;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/sign/in")
    public ResponseEntity<String> signIn(UserDto dto) throws UserNotFoundException {
        User userDetails = (User) userDetailsService.loadUserByUsername(dto.getEmail());

        // password 일치하지 않으면 throw exception
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }
        return ResponseEntity.ok(
            jwtTokenProvider.createToken(userDetails.getEmail(), userDetails.getRoles()));
    }

    @GetMapping("/sign/out")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtil.signOut(request, response);
        return "redirect:/";
    }

}
