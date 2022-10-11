package hotel;

//import java.util.ArrayList;
//import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDate;
import java.io.Serializable;

public class Room implements Serializable {

	private Integer roomNumber;
	// private List<BookingDetail> bookings;
	private ConcurrentHashMap<LocalDate,BookingDetail> bookingsMap;

	public Room(Integer roomNumber) {
		this.roomNumber = roomNumber;
		// bookings = new ArrayList<BookingDetail>();
		bookingsMap = new ConcurrentHashMap<LocalDate,BookingDetail>();
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	/*
	 * remove setRoomNumber to make roomNumber immutable
	 * avoids concurrency issues
	 */
//	public void setRoomNumber(Integer roomNumber) {
//		this.roomNumber = roomNumber;
//	}
//
//	public List<BookingDetail> getBookings() {
//		return bookings;
//	}

//	public void setBookings(List<BookingDetail> bookings) {
//		this.bookings = bookings;
//	}

	public BookingDetail getBooking(LocalDate date) {

		return bookingsMap.getOrDefault(date, null);
	}

	public void setBooking(BookingDetail booking) throws ReservationException {

		BookingDetail previous = bookingsMap.putIfAbsent(booking.getDate(), booking);
		if (previous != null){
			throw new ReservationException("Room already booked, cannot process booking request " + booking.toString());
		}
	}
}