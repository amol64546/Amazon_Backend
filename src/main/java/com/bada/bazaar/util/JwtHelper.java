package com.bada.bazaar.util;

import com.bada.bazaar.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtHelper {

  private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final int MINUTES = 60 * 24;

  public static String generateToken(
    User user) {
    var now = Instant.now();
    return Jwts.builder()
      .subject(user.getUsername())
      .issuedAt(Date.from(now))
      .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
      .claim("username", user.getUsername())
      .claim("userId", user.getId())
      .claim("role", user.getRole())
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public static String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public static Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private static Claims extractAllClaims(String token) {
    try {
      return Jwts
        .parser()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseSignedClaims(token)
        .getPayload();
    } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
      throw new RuntimeException("Access denied: " + e.getMessage());
    }
  }

  private static boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private static Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}