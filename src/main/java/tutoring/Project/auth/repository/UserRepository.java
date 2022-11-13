package tutoring.Project.auth.repository;

import tutoring.Project.auth.entity.Users;
import tutoring.Project.base.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);
}