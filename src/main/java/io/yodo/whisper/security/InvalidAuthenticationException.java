package io.yodo.whisper.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.AuthenticationException;

public class InvalidAuthenticationException extends AuthenticationException {
    public InvalidAuthenticationException(String msg) {
        super(msg);
    }

    public InvalidAuthenticationException(JWTVerificationException cause) {
        super(cause.getMessage(), cause);
    }
}
