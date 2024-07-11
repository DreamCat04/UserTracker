package ch.bbw.usertracker.controller;

import ch.bbw.usertracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import ch.bbw.usertracker.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/register")
	public User register(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	// Weitere Endpunkte
}
