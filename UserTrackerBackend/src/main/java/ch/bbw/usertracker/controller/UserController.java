package ch.bbw.usertracker.controller;

import ch.bbw.usertracker.jwt.JwtTokenProvider;
import ch.bbw.usertracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import ch.bbw.usertracker.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	private PasswordEncoder passwordEncoder;
	JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		User registeredUser = userService.registerUser(user);
		return ResponseEntity.ok(registeredUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Authentication loginRequest) {
		Optional<User> userOpt = userService.findByEmail(loginRequest.getPrincipal().toString());
		if (userOpt.isPresent() && passwordEncoder.matches(loginRequest.getCredentials().toString(), userOpt.get().getPassword())) {
			// Generate JWT token
			String token = jwtTokenProvider.createToken(userOpt.get().getEmail(), userOpt.get().getRole());
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}
}
