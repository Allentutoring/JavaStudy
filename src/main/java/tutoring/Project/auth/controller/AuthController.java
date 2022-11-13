package tutoring.Project.auth.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tutoring.Project.base.controller.BaseController;
import tutoring.Project.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/signin")
    public ResponseEntity<String> signIn(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtil.signOut(request, response);
        return ResponseEntity.badRequest().body("You need to login");
    }

    @GetMapping("/signout")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtil.signOut(request, response);
        return "redirect:/";
    }

}
