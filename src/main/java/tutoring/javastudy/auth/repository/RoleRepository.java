package tutoring.javastudy.auth.repository;

import tutoring.javastudy.auth.entity.Role;
import tutoring.javastudy.base.repository.BaseRepository;

public interface RoleRepository extends BaseRepository<Role, Long> {

    Role findByName(String name);

}