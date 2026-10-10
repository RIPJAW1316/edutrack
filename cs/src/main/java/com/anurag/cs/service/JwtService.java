package com.anurag.cs.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
      // Set JWT_SECRET in any deployed environment; the default is for local development only
      @Value("${jwt.secret:mysecretkey12345678mysecretkey12345678}")
      private String secretKey;

      private SecretKey getKey() {
            return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
      }

      public String generateToken(String email) {
            return Jwts.builder()
                        .subject(email)
                        .issuedAt(new Date())
                        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                        .signWith(getKey())
                        .compact();
      }

      public String extractUsername(String token) {
            return Jwts.parser()
                        .verifyWith(getKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject();// extract your email
      }

}
