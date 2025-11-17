package com.oikos.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.oikos.api.entity.User;
import com.oikos.api.exceptions.OikosErrorCatalog;
import com.oikos.api.exceptions.OikosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${JWT_SECRET}")
    private String SECRET_KEY;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException(
                new OikosException(OikosErrorCatalog.ERRO_AO_CRIAR_TOKEN)
            );
        }
    }
    public Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }
}
