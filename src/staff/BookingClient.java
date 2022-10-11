package staff;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Level;

import hotel.BookingDetail;
import hotel.BookingManager;
import hotel.IBookingManager;

public class BookingClient extends AbstractScriptedSimpleTest {

	private IBookingManager bm = null;

	public static void main(String[] args) throws Exception {

		try {
			if(System.getSecurityManager() != null)
				System.setSecurityManager(null);
			// get booking manager

			Registry registry = LocateRegistry.getRegistry();

			String name = "BookingManager";
			IBookingManager bm = (IBookingManager) registry.lookup(name);
			System.out.println("Booking manager found.");

			BookingClient client = new BookingClient(bm);
			client.run();

		} catch (NotBoundException ex) {
			System.err.println("Could not find booking manager.");
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}


	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
			//Look up the registered remote instance
			bm = new BookingManager();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public BookingClient(IBookingManager bm) {
		try {
			//Look up the registered remote instance
			this.bm = bm;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		//Implement this method
		try {
			return bm.isRoomAvailable(roomNumber, date);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
			return false;
		}
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws Exception {
		//Implement this method
		try {
			bm.addBooking(bookingDetail);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) {
		//Implement this method
		try {
			return bm.getAvailableRooms(date);
		}
		catch(RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}

	@Override
	public Set<Integer> getAllRooms() {
		try {
			return bm.getAllRooms();
		}
		catch(RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
			return null;
		}

	}
}
