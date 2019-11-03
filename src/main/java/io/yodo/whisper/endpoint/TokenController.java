package io.yodo.whisper.endpoint;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.yodo.whisper.entity.TokenRequest;
import io.yodo.whisper.entity.TokenResponse;
import io.yodo.whisper.error.InvalidRequestException;
import io.yodo.whisper.security.JWTConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
public class TokenController {

    private final String jwtSecret;

    private final String jwtIssuer;

    private final Map<String, String> clients;

    public TokenController(JWTConfig config) {
        this.jwtSecret = config.getSecret();
        this.jwtIssuer = config.getIssuer();
        this.clients = config.getClients();
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody TokenRequest req) {
        // our custom error handler will map InvalidRequestException to a 400 Bad Request response
        if (!clients.containsKey(req.getClientId())) {
            throw new InvalidRequestException("Invalid client id or secret");
        }
        if (!clients.get(req.getClientId()).equals(req.getClientSecret())) {
            throw new InvalidRequestException("Invalid client id or secret");
        }

        // calculate expiry date 1 hour from now
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        Date exp = cal.getTime();

        // issue JWT
        Algorithm algo = Algorithm.HMAC256(jwtSecret);
        String token = JWT.create()
                .withIssuer(jwtIssuer)
                .withClaim("name", req.getClientId())
                .withArrayClaim("scope", new String[]{"user", "admin"})
                .withNotBefore(new Date())
                .withExpiresAt(exp)
                .sign(algo);

        return new TokenResponse(token);
    }
}
