package io.yodo.whisper.security;

import org.springframework.security.core.AuthenticationException;

class InvalidAuthenticationException extends AuthenticationException {
    InvalidAuthenticationException(String msg) {
        super(msg);
    }

    InvalidAuthenticationException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
