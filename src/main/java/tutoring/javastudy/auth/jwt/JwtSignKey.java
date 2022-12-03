package tutoring.javastudy.auth.jwt;

import java.security.Key;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtSignKey implements Key {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Override
    public String getAlgorithm() {
        return "RSA";
    }

    @Override
    public String getFormat() {
        return "PKCS#8";
    }

    @Override
    public byte[] getEncoded() {
        return Base64.getEncoder().encode(secretKey.getBytes());
    }
}
