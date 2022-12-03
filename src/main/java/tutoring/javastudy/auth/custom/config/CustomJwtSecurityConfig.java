package tutoring.javastudy.auth.custom.config;

import java.security.Key;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tutoring.javastudy.auth.custom.service.CustomUserDetailsService;
import tutoring.javastudy.auth.jwt.JwtSignKey;
import tutoring.javastudy.auth.jwt.JwtTokenFilter;
import tutoring.javastudy.auth.jwt.JwtTokenProvider;
import tutoring.javastudy.auth.repository.UserRepository;
import tutoring.javastudy.base.evalutator.CustomMethodSecurityExpressionHandler;
import tutoring.javastudy.base.evalutator.CustomPermissionEvaluator;
import tutoring.javastudy.util.modelmapper.Converter;
import tutoring.javastudy.util.modelmapper.impl.Convertable;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@EnableSpringDataWebSupport
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomJwtSecurityConfig extends GlobalMethodSecurityConfiguration {
    
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    
    /*
     * Route 설정 및 Custom AuthenticationProvider 설정
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable().httpBasic().disable()
//            .authorizeRequests()
            // 페이지 권한 설정
//            .antMatchers("/", "/api/sign/in", "/api/sign/up").permitAll()
//            .anyRequest().authenticated()
//            .and()
            .addFilterBefore(
                new JwtTokenFilter(jwtTokenProvider, userDetailsService),
                UsernamePasswordAuthenticationFilter.class
            ).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
    
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler()
    {
        CustomMethodSecurityExpressionHandler expressionHandler = new CustomMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return expressionHandler;
    }
    
    @Bean
    public Key jwtKey()
    {
        return new JwtSignKey();
    }
    
    /*
     * loadUserByUsername 함수를 이용하여 username(email) 에 해당하는 user 가 있는지 확인 하는 UserDetailService
     * */
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new CustomUserDetailsService(userRepository);
    }
    
    /*
     * 전역으로 password 방식 설정
     * */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    
    @Bean
    public Convertable convertable()
    {
        return new Converter(modelMapper());
    }
    
}
