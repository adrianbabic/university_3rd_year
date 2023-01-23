package www.security.config.JWTToken;

import www.security.config.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import www.exception.exceptions.JWTTokenException;

@Component("jwtToken")
public class JwtToken {
	private static final Logger logger = LoggerFactory.getLogger(JwtToken.class);
	
	@Value("tajna")
	private String secret;
	
	private int expirationMs = 50000000;
	
	public String generateTokenValue(Authentication auth) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + expirationMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public boolean validateJwtToken(String authToken) throws JWTTokenException {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
		} catch (Exception ex) {
			System.err.println("krivo validiranje");
			throw new JWTTokenException("JWT token error: " + ex.getMessage());
		}
		System.err.println("token proso validaciju");
		return true;
	}
	
	public String getUserEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	@Override
	public String toString() {
		return "JwtToken [secret=" + secret + ", expirationMs=" + expirationMs + "]";
	}

}
