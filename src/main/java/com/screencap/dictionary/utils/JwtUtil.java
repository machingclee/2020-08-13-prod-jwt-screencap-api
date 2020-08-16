package com.screencap.dictionary.utils;

import java.time.LocalDate;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private String scretKey = "I_LOVE_JAVA";
    private Algorithm algorithm = Algorithm.HMAC256(scretKey);
    private JWTVerifier jwtVerifier = JWT.require(algorithm).build();

    public String generateToken(UserDetails userDetails) {
        return createToken("username", userDetails.getUsername());
    }

    public String extractUsername(String token) {
        DecodedJWT jwt = jwtVerifier.verify(token);
        String username = jwt.getClaim("username").asString();

        return username;
    }

    public Boolean isTokenExpired(String token) {
        DecodedJWT jwt = jwtVerifier.verify(token);
        Date expiryDate = jwt.getExpiresAt();

        return expiryDate.before(new Date());
    }

    public String createToken(String claimName, String claim) {

        Builder jwtBuilder = JWT.create();

        String token = jwtBuilder
            .withClaim(claimName, claim)
            .withIssuedAt(new Date())
            .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
            .sign(algorithm);

        return token;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        Boolean userIsCorrect = username.equals(userDetails.getUsername());
        Boolean isTokenExpired = isTokenExpired(token);

        return userIsCorrect && !isTokenExpired;
    }
}
