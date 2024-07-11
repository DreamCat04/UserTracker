package ch.bbw.usertracker.service;

import ch.bbw.usertracker.entity.Role;
import ch.bbw.usertracker.entity.User;
import ch.bbw.usertracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerUser(User user) {
		if (userRepository.count() == 0) {
			user.setRole(Role.ADMIN);
		} else {
			user.setRole(Role.MEMBER);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}