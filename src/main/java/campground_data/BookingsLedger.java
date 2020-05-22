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

    public ArrayList<Lot> getLotList()
    {
        return aLot;
    }

    public void setLotList(ArrayList<Lot> aLot)
    {
        this.aLot = aLot;
    }

    public Lot querySearchCampsite(int LotID)
    {
        
        for (Lot lot : aLot) {

            if (lot.nLotID == LotID) {
                return lot;
            }
        }

        System.out.printf("Search could not find the Lot specified.\n");
        return null;
    }

    /*
    Runs through the reservations and removes any reservation associated with the Lot passed in.
     */
    public void removeReservations(Lot obLot)
    {

        for (Reservation obRes : aReservation)
        {
            if (obRes.obLot == obLot)
            {
                aReservation.remove(obRes);
            }
        }
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
    Sets the availability of the lot to the boolean passed in.
     */
    public void setLotAvailability(Lot obLot, boolean bAvailable)
    {
        obLot.bAvailability = bAvailable;

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
