package io.yodo.whisper.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JWTTokenHelper tokenHelper;

    public JWTAuthenticationProvider(JWTTokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof JWTTokenAuthentication)) {
            throw new IllegalArgumentException("Unsupported authentication class " + authentication.getClass());
        }

        JWTTokenAuthentication jwtAuth = (JWTTokenAuthentication) authentication;
        String jwtToken = (String) authentication.getCredentials();

        User user = tokenHelper.verify(jwtToken);

        jwtAuth.setAuthenticated(true);
        jwtAuth.setPrincipal(user);

        log.debug("Authentication successful, returning " + jwtAuth);

        return jwtAuth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(JWTTokenAuthentication.class);
    }
}
