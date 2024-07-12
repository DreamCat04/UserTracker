package ch.bbw.usertracker.jwt;

import ch.bbw.usertracker.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-length:86400000}")
	private long validityInMilliseconds; // 24h
	
	private SecretKey key;
	
	@PostConstruct
	protected void init() {
		//key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String createToken(String email, Role role) {
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("role", role.name());
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
		String role = claims.get("role", String.class);
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}
}