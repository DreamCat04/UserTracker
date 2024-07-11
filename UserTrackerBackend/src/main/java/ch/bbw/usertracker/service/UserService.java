package ch.bbw.usertracker.service;

import ch.bbw.usertracker.entity.Role;
import ch.bbw.usertracker.entity.User;
import ch.bbw.usertracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User registerUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (userRepository.count() == 0) {
			user.setRole(Role.ADMIN);
		} else {
			user.setRole(Role.MEMBER);
		}
		return userRepository.save(user);
	}
	
	// Weitere Methoden
}