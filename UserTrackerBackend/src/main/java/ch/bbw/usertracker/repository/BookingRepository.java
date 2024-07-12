package ch.bbw.usertracker.repository;

import ch.bbw.usertracker.entity.Booking;
import ch.bbw.usertracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser(User user);
	
	List<Booking> findByUserId(Long userId);
}