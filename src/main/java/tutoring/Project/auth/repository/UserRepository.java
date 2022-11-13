package tutoring.Project.auth.repository;

import tutoring.Project.auth.entity.User;
import tutoring.Project.base.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}