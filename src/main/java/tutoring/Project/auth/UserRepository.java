package tutoring.Project.auth;

import tutoring.Project.base.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findOneWithAuthoritiesById(String id);

}