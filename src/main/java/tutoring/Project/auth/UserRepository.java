package tutoring.Project.auth;

import tutoring.Project.base.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String email);
}