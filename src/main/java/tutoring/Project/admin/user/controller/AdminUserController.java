package tutoring.Project.admin.user.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoring.Project.admin.base.controller.AdminController;
import tutoring.Project.auth.entity.User;
import tutoring.Project.auth.repository.UserRepository;

@RestController
@RequestMapping("/admin/user")
@AllArgsConstructor
public class AdminUserController extends AdminController {
    private final UserRepository repository;

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<User>> allInfo() {
        return ResponseEntity.ok(repository.findAll());
    }

}