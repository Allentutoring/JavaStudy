package tutoring.Project.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tutoring.Project.base.controller.BaseController;
import tutoring.Project.util.SecurityUtil;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController extends BaseController {

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
