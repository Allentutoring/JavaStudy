package tutoring.Project.auth;

import tutoring.Project.base.BaseRepository;

import java.util.List;

public interface AuthoritiesRepository extends BaseRepository<Authority, Long> {

    List<Authority> findByEmail(String email);
}