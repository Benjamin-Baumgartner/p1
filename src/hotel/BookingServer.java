package hotel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingServer {

    private static final Logger logger = Logger.getLogger(BookingServer.class.getName());

    public static void main(String[] args) throws RemoteException {

        // set security manager if non existent
        if(System.getSecurityManager() != null)
            System.setSecurityManager(null);

        // create booking manager
        BookingManager bm = new BookingManager();
        String name = "BookingManager";

        // locate registry
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry();

        } catch(RemoteException e) {
            logger.log(Level.SEVERE, "Could not locate RMI registry.");
            System.exit(-1);
        }

        // register booking manager
        IBookingManager stub;
        try {
            stub = (IBookingManager) UnicastRemoteObject.exportObject(bm, 0);
            registry.rebind(name, stub);

            logger.log(Level.INFO, "Booking manager is registered");
        } catch(RemoteException e) {
            logger.log(Level.SEVERE, "Could not get stub bound for booking manager.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
