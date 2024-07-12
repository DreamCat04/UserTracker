package ch.bbw.usertracker.service;

import ch.bbw.usertracker.entity.Booking;
import ch.bbw.usertracker.entity.Role;
import ch.bbw.usertracker.entity.User;
import ch.bbw.usertracker.repository.BookingRepository;
import ch.bbw.usertracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public List<Booking> findBookingsByUser(Long userId) {
		return bookingRepository.findByUserId(userId);
	}
	
	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
	}
	
	public Booking updateBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public Booking findBookingById(Long id) {
		return bookingRepository.findById(id).orElse(null);
	}
}