package ru.dolzhenkoms.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenUtils {

    @Value("${medicwiki.app.jwtSecret}")
    private String secret;

    @Value("${medicwiki.app.jwtExpirationMs}")
    private Long lifetime;

    public String generateToken(UserDetails userDetails) {
        final var claims = new HashMap<String, Object>();

        claims.put("roles", Collections.emptyList());

        final var now = new Date();
        final var expirationDate = new Date(now.getTime() + lifetime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public List<String> getRoles(String token) {
        return getAllClaims(token).get("roles", List.class);
    }
}
