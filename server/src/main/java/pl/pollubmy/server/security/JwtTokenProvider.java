package pl.pollubmy.server.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.pollubmy.server.exceptions.IncorrectJwtException;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        JwtUserPrincipal jwtUserPrincipal = (JwtUserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(jwtUserPrincipal.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            throw new IncorrectJwtException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new IncorrectJwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new IncorrectJwtException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new IncorrectJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new IncorrectJwtException("JWT claims string is empty.");
        }
    }
}