package tutoring.Project.auth.impl;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsWithId extends UserDetails {
    
    long getId();
}
