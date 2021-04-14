package dp.home_food_order_center.security.jwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.*;

import java.util.*;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/27/2021
 */
@Component
public class JwtUtils {
    private static final Logger logger = LogManager.getLogger(JwtUtils.class);

    private final HashMap<String, Long> jwtBlackListTokens;

    private long clearListBefor = -1;
    //    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    //    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public JwtUtils() {
        this.jwtBlackListTokens = new HashMap<>();
    }

    public String generateJwtToken(Authentication authentication) {
        this.jwtSecret = UUID.randomUUID().toString();
        this.jwtExpirationMs = 3600000; //60 minutes
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Date expirationDate = new Date((new Date()).getTime() + jwtExpirationMs);
        if (clearListBefor == -1) {
            clearListBefor = expirationDate.getTime();
        }
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            long time = new Date().getTime();
            if (clearListBefor > time) {
                for (String key : jwtBlackListTokens.keySet()) {
                    if (jwtBlackListTokens.get(key) < clearListBefor) {
                        jwtBlackListTokens.remove(key);
                    }
                }

                clearListBefor = time;
            }

            return !jwtBlackListTokens.containsKey(authToken);
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage(), e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage(), e);
        } catch (ExpiredJwtException e) {
            jwtBlackListTokens.remove(authToken);
            logger.error("JWT token is expired: {}", e.getMessage(), e);
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage(), e);
        }

        return false;
    }

    public void addLogOutToken(String autToken) {
        jwtBlackListTokens.put(autToken, new Date().getTime());
    }
}