package tutoring.javastudy.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tutoring.javastudy.auth.entity.Role;
import tutoring.javastudy.auth.jwt.exception.InvalidJwtTokenException;

@Slf4j
@Component
public class JwtTokenProvider {
    
    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in
     * a microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    
    private Key jwtKey;
    
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 60 * 60 * 1000; // 1h
    
    // private final UserDetailsService userDetailsService;
    
    @PostConstruct
    protected void init()
    {
        jwtKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    
    public String createToken(String username, List<Role> appUserRoles)
    {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", appUserRoles);
        
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        
        return Jwts.builder()//
                   .setClaims(claims)//
                   .setIssuedAt(now)//
                   .setExpiration(validity)//
//            .signWith(SignatureAlgorithm.HS256, signKey)//
                   .signWith(jwtKey, SignatureAlgorithm.HS256)//
                   .compact();
    }
    
    public String getUsername(String token)
    {
        return Jwts.parserBuilder()
                   .setSigningKey(jwtKey)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
    
    public String resolveToken(HttpServletRequest req)
    {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }
    
    public boolean validateToken(String jwtToken)
    throws InvalidJwtTokenException
    {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                                     .setSigningKey(jwtKey)
                                     .build()
                                     .parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new InvalidJwtTokenException("Expired or invalid JWT token");
        }
    }
    
}
