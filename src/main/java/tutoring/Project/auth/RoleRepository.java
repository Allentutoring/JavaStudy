package tutoring.Project.auth;

import tutoring.Project.base.BaseRepository;

import java.util.List;

public interface RoleRepository extends BaseRepository<Roles, Long> {

    List<Role> findByName(Role name);

}