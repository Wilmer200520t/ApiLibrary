package com.med.voll.api.general.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.med.voll.api.user.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(User user) {
        System.out.println(secret);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Instant getExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubjectFromToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null or empty");
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);

            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception);
        }

        if (verifier.getSubject() == null) {
            throw new RuntimeException("JWT verification failed");
        }

        return verifier.getSubject();
    }
}
