package com.bada.bazaar.util;

import static com.bada.bazaar.constant.CommonConstant.AUTHORIZATION;
import static com.bada.bazaar.constant.CommonConstant.BEARER;
import static com.bada.bazaar.constant.CommonConstant.MINUTES;
import static com.bada.bazaar.constant.CommonConstant.ROLE;
import static com.bada.bazaar.constant.CommonConstant.SECRET_KEY;
import static com.bada.bazaar.constant.CommonConstant.USERNAME;
import static com.bada.bazaar.constant.CommonConstant.USER_ID;

import com.bada.bazaar.entity.User;
import com.bada.bazaar.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtHelper {


  public static String generateToken(
    User user) {
    var now = Instant.now();
    return Jwts.builder()
      .subject(user.getUsername())
      .issuedAt(Date.from(now))
      .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
      .claim(USERNAME, user.getUsername())
      .claim(USER_ID, user.getId())
      .claim(ROLE, user.getRole())
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  private static Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public static String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public static String extractToken(HttpServletRequest request) {
    String token = null;
    String authHeader = request.getHeader(AUTHORIZATION);
    if (authHeader != null && authHeader.startsWith(BEARER)) {
      token = authHeader.substring(7);
    }
    return token;
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

  public static User getUserInfo(HttpServletRequest request) {
    String token = extractToken(request);
    Claims claims = extractAllClaims(token);
    return User.builder()
      .id((Integer) claims.get(USER_ID))
      .username(claims.getSubject())
      .role(Role.valueOf((String) claims.get(ROLE)))
      .build();
  }
}