package tutoring.Project.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tutoring.Project.auth.service.CustomUserDetailsService;
import tutoring.Project.jwt.JwtTokenFilterConfigurer;
import tutoring.Project.jwt.JwtTokenProvider;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/", "/api/signin", "/api/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                // 로그인 실행
                .formLogin()
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
                // 로그인 실행 api 주소 설정
                // .loginPage("/api/signin")
                .loginProcessingUrl("/api/signin")
                .defaultSuccessUrl("/api/user", true)
                // .failureUrl("/api/signin")
                .usernameParameter("email")
                .passwordParameter("password")
//                .permitAll()
                // 로그아웃 설정
                .and()
                .logout()
                .logoutUrl("api/signout")
                .deleteCookies("JSESSIONID")
                // .logoutSuccessHandler(logoutSuccessHandler());
                .permitAll();
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        return http.build();
    }

    /*@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        return authenticationManager;
    }*/

    /**
     * 전역으로 password 방식 설정
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
