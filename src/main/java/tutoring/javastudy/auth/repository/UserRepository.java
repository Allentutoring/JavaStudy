package tutoring.javastudy.auth.repository;

import java.util.Optional;
import tutoring.javastudy.auth.entity.User;
import tutoring.javastudy.base.repository.BaseRepository;

public interface UserRepository extends BaseRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
}