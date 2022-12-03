package tutoring.javastudy.admin.user.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tutoring.javastudy.admin.base.controller.AdminController;
import tutoring.javastudy.admin.user.role.IsAdmin;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.auth.repository.UserRepository;

@RestController
@RequestMapping("/admin/user")
@AllArgsConstructor
public class AdminUserController extends AdminController {

    private final UserRepository repository;

    @IsAdmin
    @GetMapping("/all")
    public ResponseEntity<List<User>> allInfo() {
        return ResponseEntity.ok(repository.findAll());
    }

}
