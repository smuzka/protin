package zti.protin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

/**
 * Utility for handling JWT
 */
@Component
public class JwtUtil {
    /**
     * Secret key for JWT
     */
    @Value("${JWT_SECRET}")
    private String secret;

    /**
     * Extract expiration date from the token
     *
     * @param token - JWT token
     * @return expiration date of the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract email from the token
     *
     * @param token - JWT token
     * @return email from the token
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generate token for the email
     *
     * @param email - email to generate token for
     * @return JWT token
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //.setClaims("UserId", userId)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Validate the token
     *
     * @param token - JWT token
     * @param email - email to validate
     * @return true if the token is valid, false otherwise
     */
    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    /**
     * Check if the token is expired
     *
     * @param token - JWT token
     * @return true if the token is expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extract claim from the token
     *
     * @param token - JWT token
     * @param claimsResolver - function to resolve claims
     * @param <T> - type of the claim
     * @return claim from the token
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims from the token
     *
     * @param token - JWT token
     * @return all claims from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}

