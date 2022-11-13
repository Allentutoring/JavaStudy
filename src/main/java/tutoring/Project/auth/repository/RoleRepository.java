package tutoring.Project.auth.repository;

import tutoring.Project.auth.Role;
import tutoring.Project.auth.entity.Roles;
import tutoring.Project.base.repository.BaseRepository;

import java.util.List;

public interface RoleRepository extends BaseRepository<Roles, Long> {

    List<Role> findByName(Role name);

}