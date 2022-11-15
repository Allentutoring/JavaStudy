## Custom

### Reference

[PreAuthorize to Annotation](https://www.baeldung.com/spring-security-method-security)

### @EnableGlobalMethodSecurity

- MethodSecurity는 말그대로 메소드 수준에서 권한을 제어할 수 있도록 해준다!   
  특정 메소드마다 실행할 수 있는 역할을 제한할 수 있도록 해줌.   
  단순히 허용, 거부 하는 것 보다 더 복잡한 규칙을 적용할 수 있음.

- GlobalMethodSecurity 사용을 활성화 하는 @EnableGlobalMethodSecurity 어노테이션을 추가
    - prePostEnabled - Spring Security의 @PreAuthorize, @PreFilter /@PostAuthorize, @PostFilter어노테이션
      활성화 여부
    - securedEnabled - @Secured어노테이션 활성화 여부
    - jsr250Enabled - @RoleAllowed 어노테이션 사용 활성화 여부

### @RolesAllowed vs @Secured

- @Secured and @RolesAllowed are the same. They do the same operation in Spring.
- But @RolesAllowed - Standard annotation of Java.   
  Java has defined Java Specification Request, basically change requests for the Java language,
  libraries and other components. For the development of annotations, they have provided JSR 250.
  @RolesAllowed is included in it. This link contains further info in JSR 250
- @Secured - Spring security annotation

### @Secured vs @PreAuthorize

- @EnableGlobalMethodSecurity 어노테이션의 속성으로 securedEnabled 를 true @Secured 어노테이션을, prePostEnabled를
  true @PreAuthorize와 @PostAuthorize를 사용할 수 있음.
- @Secured 는 표현식 사용할 수 없고
- @PreAuthroize 는 표현식 사용 가능
- 표현식 종류
    - hasRole([role]) : 현재 사용자의 권한이 파라미터의 권한과 동일한 경우 true
    - hasAnyRole([role1,role2]) : 현재 사용자의 권한디 파라미터의 권한 중 일치하는 것이 있는 경우 true
    - principal : 사용자를 증명하는 주요객체(User)를 직접 접근할 수 있다.
    - authentication : SecurityContext에 있는 authentication 객체에 접근 할 수 있다.
    - permitAll : 모든 접근 허용
    - denyAll : 모든 접근 비허용
    - isAnonymous() : 현재 사용자가 익명(비로그인)인 상태인 경우 true
    - isRememberMe() : 현재 사용자가 RememberMe 사용자라면 true
    - isAuthenticated() : 현재 사용자가 익명이 아니라면 (로그인 상태라면) true
    - isFullyAuthenticated() : 현재 사용자가 익명이거나 RememberMe 사용자가 아니라면 true
- @PostAuthorize 는 returnObject 예약어로 메서드의 리턴 객체에 접근할수 있다.
    - @PreAuthorize("returnObject.name == #username")
    - @PreAuthorize("@webSecurity.checkAuthority(authentication,#username)")
- @PreAuthorize 는 파라미터에 접근하기 위해 '#파라미터명'을 사용하여 객체에 접근할수 있다.

### Config

- @EnableGlobalMethodSecurity(securedEnabled = true) 어노테이션을 설정합니다.
- Controller Method 에 @PreAuthorize, @PostAuthorize, @Secured 등을 사용할 수 있습니다

```java

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomSecurityConfig {

}
```

### UserDetailsService

- loadUserByUsername 함수에서 user.getAuthorities() 를 통해 사용자 권한을 넘겨줍니다.

```java
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userEntity.get();
        return user;
    }
}
```

### AuthenticationProvider

- authenticate 함수에서 token 을 생성할 때 UserDetailsService 의 getAuthorities() 함수를 통해 권한을 넘겨줍니다.

```java
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        // ...

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, userDetails.getId(), userDetails.getAuthorities()
        );

        return authenticationToken;
    }

}

```

### Controller

- @PreAuthorize 를 이용하여 Controller 내부 코드를 실행하기 전에 표현식을 실행하여 코드 실행 여부를 파악 합니다
- authentication.credential 에 id 를 넣으려고 했으나 지워진 상태로 넘어와서 principal 에 User entity 객체를 담아서 사용하게 되었습니다.

```java
public class UserController {

    private final UserService userService;

    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> othersInfo(Authentication authentication,
        @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

}

```