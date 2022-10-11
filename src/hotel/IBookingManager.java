package hotel;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.time.LocalDate;
import java.util.Set;

/**
 * Interface for remote booking managers.
 */
public interface IBookingManager extends Remote {

    /**
     * @return  return a set of all rooms in the hotel
     * @throws 	RemoteException
     */
    public Set<Integer> getAllRooms() throws RemoteException;

    /**
     * Check if room is available on a given date (LocalDate)
     * @param   roomNumber
     *          room number for which we want to check availability
     * @param   date
     *          date for which we want to check availability
     * @return  true if room is available on the requested date
     *          otherwise false
     * @throws 	RemoteException
     */
    public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException, ReservationException;

    /**
     * Try to add a bookingdetail
     * @param   bookingDetail
     *          the booking detail that will be tried to be added
     * @throws  RemoteException
     *          in case of RMI remote exception
     * @throws  ReservationException
     *          in case the room is already booked
     */
    public void addBooking(BookingDetail bookingDetail) throws RemoteException, ReservationException;

    /**
     * Get all rooms that are still available for a given date
     * @param   date
     *          date for which room availability needs to be tested
     * @return  return a set of all rooms that are available for the given date
     * @throws  RemoteException
     */
    public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException;

}
