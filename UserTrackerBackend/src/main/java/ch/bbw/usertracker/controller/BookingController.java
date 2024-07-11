package ch.bbw.usertracker.controller;

import ch.bbw.usertracker.entity.Booking;
import ch.bbw.usertracker.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public Booking createBooking(@RequestBody Booking booking, @AuthenticationPrincipal User user) {
		return bookingService.createBooking(booking, user.getUsername());
	}
	
	// Weitere Endpunkte
}