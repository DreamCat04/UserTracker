package ch.bbw.usertracker.controller;

import ch.bbw.usertracker.entity.Booking;
import ch.bbw.usertracker.entity.User;
import ch.bbw.usertracker.service.BookingService;
import ch.bbw.usertracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User createdUser = userService.registerUser(user);
		return ResponseEntity.ok(createdUser);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/bookings")
	public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
		Booking createdBooking = bookingService.createBooking(booking);
		return ResponseEntity.ok(createdBooking);
	}
	
	@PutMapping("/bookings/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
		booking.setId(id);
		Booking updatedBooking = bookingService.updateBooking(booking);
		return ResponseEntity.ok(updatedBooking);
	}
	
	@DeleteMapping("/bookings/{id}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
		return ResponseEntity.ok().build();
	}
}
