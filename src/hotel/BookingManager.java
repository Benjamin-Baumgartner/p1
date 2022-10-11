package hotel;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BookingManager implements IBookingManager {

	// private Room[] rooms;
	private ConcurrentHashMap<Integer, Room> rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() throws RemoteException {
//		Set<Integer> allRooms = new HashSet<Integer>();
//		Iterable<Room> roomIterator = Arrays.asList(rooms);
//		for (Room room : roomIterator) {
//			allRooms.add(room.getRoomNumber());
//		}
//		return allRooms;
		return rooms.keySet();
	}

	private Room getRoom(Integer roomNumber) throws ReservationException {
		Room room = rooms.getOrDefault(roomNumber, null);
		if (room == null){
			throw new ReservationException("Room doesn't exist " + roomNumber.toString());
		}
		return room;
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException, ReservationException {
		//implement this method
		Room room = this.getRoom(roomNumber);
		BookingDetail booking = room.getBooking(date);
		if (booking == null){
			return true;
		}
		return false;

	}

	public void addBooking(BookingDetail bookingDetail) throws RemoteException, ReservationException {
		Room room = this.getRoom(bookingDetail.getRoomNumber());
		room.setBooking(bookingDetail);
		//implement this method
	}

	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		Set<Integer> availableRooms = new HashSet<Integer>();
		for (Room room : rooms.values()){
			if (room.getBooking(date) == null) {
				availableRooms.add(room.getRoomNumber());
			}
		}
		return availableRooms;
		//implement this method
	}

	private static ConcurrentHashMap<Integer, Room> initializeRooms() {
		// Room[] rooms = new Room[4];
		ConcurrentHashMap<Integer, Room> rooms = new ConcurrentHashMap<Integer, Room>();
//		rooms[0] = new Room(101);
//		rooms[1] = new Room(102);
//		rooms[2] = new Room(201);
//		rooms[3] = new Room(203);
		rooms.put(101, new Room(101));
		rooms.put(102, new Room(102));
		rooms.put(201, new Room(201));
		rooms.put(203, new Room(203));
		return rooms;
	}
}
