package ch.bbw.usertracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@jakarta.persistence.Entity
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private boolean isFullDay;
	private BookingStatus status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}