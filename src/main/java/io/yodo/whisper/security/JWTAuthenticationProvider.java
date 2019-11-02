package io.yodo.whisper.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String jwtSecret;

    private final String jwtIssuer;

    public JWTAuthenticationProvider(JWTConfig config) {
        log.debug("JWT auth provider with issuer " + config.getIssuer());
        this.jwtSecret = config.getSecret();
        this.jwtIssuer = config.getIssuer();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof JWTTokenAuthentication)) {
            throw new IllegalArgumentException("Unsupported authentication class " + authentication.getClass());
        }

        log.debug("Decoding token");

        JWTTokenAuthentication jwtAuth = (JWTTokenAuthentication) authentication;
        String jwtToken = (String) authentication.getCredentials();

        DecodedJWT dec = verifyToken(jwtToken);

        log.debug("Token decoded OK");

        String username = dec.getClaim("name").asString();
        List<String> scopes = dec.getClaim("scope").asList(String.class);
        Set<GrantedAuthority> authorities =  scopes.stream().map(s ->
                new SimpleGrantedAuthority("SCOPE_"+s))
                .collect(Collectors.toSet());

        User user = new User(username, "", authorities);

        log.debug("Extracted user details " + user);

        jwtAuth.setAuthenticated(true);
        jwtAuth.setPrincipal(user);

        return jwtAuth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(JWTTokenAuthentication.class);
    }

    private DecodedJWT verifyToken(String token) {
        try {
            Algorithm algo = Algorithm.HMAC256(jwtSecret);
            JWTVerifier ver = JWT.require(algo)
                    .withIssuer(jwtIssuer)
                    .build();
            return ver.verify(token);
        } catch (JWTVerificationException ex) {
            throw new InvalidAuthenticationException(ex);
        }
    }
}
