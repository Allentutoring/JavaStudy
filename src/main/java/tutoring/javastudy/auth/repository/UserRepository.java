package tutoring.javastudy.auth.repository;

import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.repository.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}