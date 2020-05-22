package campground_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class BookingsLedger {

    ArrayList<Reservation> aReservation;
    ArrayList<Lot> aLot;
    ArrayList<Customer> aCustomer;
    ArrayList<Manager> aManager;


    /*
    Takes in a integer representing the LotID and returns a Lot with an ID matching the integer input.
    If No lot is found the system returns null and prints out a line of text notifying that the lot cannot be found.
     */
    public Lot querySearchCampsite(int LotID)
    {
        try
        {
            for (Lot lot : aLot) {
                if (lot.nLotID == LotID) {
                    return lot;
                }
            }
        }
        catch(NumberFormatException e)
        {
            System.out.printf("Something other than a whole number was passed in. only whole numbers from 1-255 may be passed\n");
        }

        System.out.printf("Search could not find the Lot specified.\n");
        return null;
    }

    /*
    Test Code make sure to remove and make actual code
     */
    public ArrayList<Reservation> removeReservation(int ReservationID)
    {
        ArrayList<Reservation> testReservation = new ArrayList<>();
        return testReservation;
    }

    /*
    Outputs the information in a good looking format to the console
     */
    public void displayLot(int LotID)
    {
        Lot displayLot = querySearchCampsite(LotID);
        System.out.printf("Lot ID: " + displayLot.nLotID +
                "\nLot Type: " + displayLot.obType +
                "\nRemoval Reason: " + displayLot.sRemovalReason +
                "\nAvailability " + displayLot.bAvailability);
    }

    /*
    Removes
     */
    public void removeLot(int LotID)
    {


    }

    /*
    Takes in a LotID and returns an ArrayList of reservations associated with that Lot based on the ID
     */
    public ArrayList<Reservation> getReservations(int LotID)
    {
        ArrayList<Reservation> obRes = new ArrayList<>();

        for (int i=0; i<aReservation.size(); i++)
        {
            if (aReservation.get(i).obLot.nLotID == LotID)
            {
                obRes.add(aReservation.get(i));
            }
        }

        return obRes;

    }




}
