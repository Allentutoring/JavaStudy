package tutoring.Project.auth.repository;

import tutoring.Project.auth.entity.Role;
import tutoring.Project.base.repository.BaseRepository;

public interface RoleRepository extends BaseRepository<Role, Long> {

    Role findByName(String name);

}