package ch.bbw.usertracker.jwt;

import ch.bbw.usertracker.entity.Role;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-length:86400000}")
	private long validityInMilliseconds; // 24h
	
	private Key key;
	
	@PostConstruct
	protected void init() {
		key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String createToken(String email, Role role) {
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("role", role);
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}
	
	public String getEmail(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws().parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = (Jws<Claims>) Jwts.parser().setSigningKey(key).parse(String.valueOf(token));
			return !claims.getBody().getExpiration().before(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			throw new JwtException("Expired or invalid JWT token" + e.getMessage());
		}
	}
}
