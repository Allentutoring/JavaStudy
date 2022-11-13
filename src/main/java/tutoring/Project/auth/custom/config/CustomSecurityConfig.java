package tutoring.Project.auth.custom.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tutoring.Project.auth.custom.provider.CustomAuthenticationProvider;
import tutoring.Project.auth.custom.service.CustomUserDetailsService;

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
