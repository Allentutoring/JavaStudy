## Custom

### Config

- auth.custom.config.CustomSecurityConfig   
  Config class로 Route, Bean, Provider 등을 설정

```java

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomSecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    /*
     * Route 설정 및 Custom AuthenticationProvider 설정
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // ...
        http.authenticationProvider(
            new CustomAuthenticationProvider(userDetailsService, passwordEncoder()));
        return http.build();
    }

    /*
     * loadUserByUsername 함수를 이용하여 username(email) 에 해당하는 user 가 있는지 확인 하는 UserDetailService
     * */
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    /*
     * 전역으로 password 방식 설정
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
```

### UserDetailsService

- auth.custom.service.CustomUserDetailService   
  UserDetailsService 를 implements 하여 username(email) 에 해당하는 user 를 load 하는 class

```java

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<Users> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Users user = userEntity.get();

        return Users.builder()
            .email(email)
            .password(user.getPassword())
            .nickname(user.getNickname())
            .enabled(true)
            .build();
    }
}
```

### AuthenticationProvider

- auth.custom.provider.CustomAuthenticationProvider
username(email) 과 password를 이용하여 로그인 여부를 설정

```java

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        log.info("authenticate");
        log.info("email : {}", email);

        Users user = (Users) userDetailsService.loadUserByUsername(email);

        // password 일치하지 않으면 throw exception
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            user.getEmail(), null, user.getAuthorities()
        );

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ClassUtils.isAssignable(UsernamePasswordAuthenticationToken.class, authentication);
    }
}

```