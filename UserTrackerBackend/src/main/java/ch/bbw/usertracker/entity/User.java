package ch.bbw.usertracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@jakarta.persistence.Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public String getUsername() {
		return firstName + " " + lastName;
	}
}