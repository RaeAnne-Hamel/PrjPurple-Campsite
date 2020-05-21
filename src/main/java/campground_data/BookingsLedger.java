package campground_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class BookingsLedger {

    ArrayList<Reservation> aReservation;
    ArrayList<Lot> aLot;
    ArrayList<Customer> aCustomer;
    ArrayList<Manager> aManager;

    public Lot querySearchCampsite(int cID)
    {
        ArrayList<Reservation> obReservationList = new ArrayList<>();
        boolean sAvailable = true;

        Lot testLot = new Lot(1, LotType.Serviced, obReservationList, 1, "", sAvailable);
        return testLot;
    }

    public ArrayList<Reservation> removeReservation(int ReservationID)
    {
        ArrayList<Reservation> testReservation = new ArrayList<>();
        return testReservation;
    }

    public void displayLot(int LotID)
    {

    }

    public ArrayList<Reservation> removeLot(int LotID)
    {
        Lot testLot = querySearchCampsite(LotID);
        return testLot.obReservationList;

    }

    public ArrayList<Reservation> getReservations(int lotID)
    {
        ArrayList<Reservation> testReservation = new ArrayList<>();
        return testReservation;
    }




}
