package tutoring.Project.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository repository;

    @PostMapping
    public String store(MemberDTO dto) {
        MemberEntity entity = dto.toEntity();
        MemberEntity saved = repository.save(entity);
        return "";
    }

}
