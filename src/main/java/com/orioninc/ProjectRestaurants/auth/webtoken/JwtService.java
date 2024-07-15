package com.orioninc.ProjectRestaurants.auth.webtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.time.Instant;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Base64.getDecoder;

@Service
public class JwtService {

  private static final String SECRET =
      "9BD3BF9695E54B688D158C4A3CB2D4FB7475BE81B17788BAC51BBE63862AD77947B0D593007D3B5A55F8A5486EA6E5786111A6CC2BBC098B8B4B3CD311A257B1";

  private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);

  public String generateToken(UserDetails userDetails) {
    Map<String, String> claims = new HashMap<>();
    claims.put("iss", "https://www.orioninc.com"); // a person or company
    claims.put("sub", userDetails.getUsername());
    //    claims.put("iat", Date.from(Instant.now()));

    return Jwts.builder()
        .claims(claims)
        .subject(userDetails.getUsername()) // Subject for whom token will generate
        .issuedAt(Date.from(Instant.now())) // When the token was issued or generated
        .expiration(
            Date.from(Instant.now().plusMillis(VALIDITY))) // Specify the duration of JWT token
        .signWith(generateKey())
        .compact(); // Convert to JSON format
  }

  private SecretKey generateKey() {
    byte[] decodedKey = Base64.getDecoder().decode(SECRET);

    return Keys.hmacShaKeyFor(decodedKey);
  }

  public String extractUsername(String jwt) {
    Claims claims = getClaims(jwt);

    return claims.getSubject();
  }

  private Claims getClaims(String jwt) {
    return Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(jwt)
            .getPayload();
  }

  public boolean isTokenValid(String jwt) {
    Claims claims = getClaims(jwt);
    return claims.getExpiration().after(Date.from(Instant.now()));
  }
}
