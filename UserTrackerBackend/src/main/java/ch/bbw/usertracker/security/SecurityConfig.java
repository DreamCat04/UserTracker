package ch.bbw.usertracker.security;

import ch.bbw.usertracker.jwt.JwtAuthenticationFilter;
import ch.bbw.usertracker.jwt.JwtAuthorizationFilter;
import ch.bbw.usertracker.jwt.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebSecurityConfigurer {
	
	private final JwtUtils jwtUtils;
	
	public SecurityConfig(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}
	
	
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.requestMatchers("/api/auth/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(new JwtAuthenticationFilter(http.authenticationManager(), jwtUtils))
				.addFilter(new JwtAuthorizationFilter(http.authenticationManager(), jwtUtils));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(auth.userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void init(SecurityBuilder builder) throws Exception {
	
	}
	
	@Override
	public void configure(SecurityBuilder builder) throws Exception {
		builder.build();
		configure();
	}
	
}
