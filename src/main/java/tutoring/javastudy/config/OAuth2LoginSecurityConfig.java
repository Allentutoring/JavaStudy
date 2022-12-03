package tutoring.javastudy.config;

import lombok.RequiredArgsConstructor;
import tutoring.javastudy.auth.repository.UserRepository;

//@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2LoginSecurityConfig {
    private final UserRepository userRepository;

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .oauth2Login(oauth2 -> oauth2
                        .clientRegistrationRepository(this.clientRegistrationRepository())
                        .authorizedClientRepository(this.authorizedClientRepository())
                        .authorizedClientService(this.authorizedClientService())
                        .loginPage("/login")
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri(this.authorizationRequestBaseUri())
                                .authorizationRequestRepository(this.authorizationRequestRepository())
                                .authorizationRequestResolver(this.authorizationRequestResolver())
                        )
                        .redirectionEndpoint(redirection -> redirection
                                .baseUri(this.authorizationResponseBaseUri())
                        )
                        .tokenEndpoint(token -> token
                                .accessTokenResponseClient(this.accessTokenResponseClient())
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userAuthoritiesMapper(this.userAuthoritiesMapper())
                                .userService(this.oauth2UserService())
                                .oidcUserService(this.oidcUserService())
                        )
                );
        return http.build();
    }*/
}
