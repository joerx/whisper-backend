package io.yodo.whisper.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JWTConfig {

    private String secret;

    private String issuer;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    protected String getSecret() {
        return secret;
    }

    protected String getIssuer() {
        return issuer;
    }
}
