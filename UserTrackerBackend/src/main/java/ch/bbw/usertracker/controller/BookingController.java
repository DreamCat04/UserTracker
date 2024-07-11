package ch.bbw.usertracker.controller;

import ch.bbw.usertracker.entity.Booking;
import ch.bbw.usertracker.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ch.bbw.usertracker.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<?> createBooking(@RequestBody Booking booking, @AuthenticationPrincipal User user) {
		booking.setUser(user);
		Booking createdBooking = bookingService.createBooking(booking);
		return ResponseEntity.ok(createdBooking);
	}
	
	@GetMapping
	public ResponseEntity<?> getUserBookings(@AuthenticationPrincipal User user) {
		List<Booking> bookings = bookingService.findBookingsByUser(user.getId());
		return ResponseEntity.ok(bookings);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long id, @AuthenticationPrincipal User user) {
		Booking booking = bookingService.findBookingById(id);
		if (booking.getUser().getId().equals(user.getId())) {
			bookingService.deleteBooking(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
}