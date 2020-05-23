package campground_data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import campground_ui.MainConsole;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BookingsLedger
{
    ArrayList<Reservation> aReservation = new ArrayList<>();
    ArrayList<Lot> aLot = new ArrayList<>();
    ArrayList<Customer> aCustomer;
    ArrayList<Manager> aManager;


    /**
     * addReservation will add a new Reservation into the aReservation ArrayList while checking and preventing any
     * issues such as overlapping booking and overbooking. The method will return true if it has succeeded and false
     * if it has failed.
     * @param nLotID
     * @param obStartDate
     * @param obEndDate
     * @param obCustomers
     * @param nPeople
     * @return
     */
    public boolean addReservation(int nLotID, Date obStartDate, Date obEndDate, Customer[] obCustomers, int nPeople) {
        List<Lot> filteredLots = aLot.stream()
                .filter(e -> e.getLotID() == nLotID)
                .collect(Collectors.toList());

        //Check if list is more than 0 entries. If so fail and return false
        if (filteredLots.size() == 0) {
            System.out.printf("LotId not found\n");
            return false;
        }

        Lot obLot = filteredLots.get(0);

        //Get todays date
        Date obToday = new Date();
        //Get todays date one year from now
        Date obYearAfter = new Date();
        obYearAfter.setYear(obYearAfter.getYear() + 1);

        //If there are more than 2 customers fail and return false
        if (obCustomers.length > 2) {
            System.out.printf("More than 2 Customers\n");
            return false;
        }

        //If there is a overlap with another reservation it will fail and return false
        if (checkOverlap(nLotID, obStartDate, obEndDate)) {
            System.out.printf("Overlaps with existing reservation\n");
            return false;
        }

        //Check if endDate is before startDate. If so it will fail and return false
        if (obStartDate.compareTo(obEndDate) > 0) {
            System.out.printf("Invalid date\n");
            return false;
        }

        //Check if obStartDate is placed before today
        if (obStartDate.compareTo(obToday) < 0) {
            System.out.printf("Reservation placed in past\n");
            return false;
        }

        //Check if obStartDate is over one year into the future
        if (obStartDate.compareTo(obYearAfter) > 0) {
            System.out.printf("Reservation placed one year into future\n");
            return false;
        }

        switch (obLot.getLotType()) {
            case Cabin:
            case DeluxeCabin:
                break;

            case NonServicedIndividual:
            case ServicedIndividual:
                if (nPeople > 4) {
                    System.out.printf("Overcapacity\n");
                    return false;
                }
                break;

            case NonServicedGroup:
            case ServicedGroup:
                if (nPeople > 8) {
                    System.out.printf("Overcapacity\n");
                    return false;
                }
                break;

            default:
                return false;
        }


        //If all checks have succeeded add the reservation to aReservations and return true
        System.out.printf("Adding Reservation...\n");
        aReservation.add(new Reservation(obLot, obStartDate, obEndDate, obCustomers, nPeople));
        System.out.printf("Reservation Added\n");
        return true;
    }


    private static Manager user = new Manager();

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



    /**
     * checkOverlap is a method that with the lotID find a reservation and check if the specified dates overlap
     * with any other reservation in the date range. if the method returns false there is no overlap and if it returns
     * true there is a overlap.
     * @param nLotID
     * @param obStartDate
     * @param obEndDate
     * @return
     */
    public boolean checkOverlap(int nLotID, Date obStartDate, Date obEndDate)
    {
        List<Reservation> aFilteredReservation = aReservation.stream()
                .filter(e -> e.getLot().getLotID() == nLotID)
                .collect(Collectors.toList());

        boolean bOverlap = false;
        for(Reservation obReservation : aFilteredReservation)
        {
            //If dates Overlap set bOverlap to false and break loop
            if ((obStartDate.compareTo(obReservation.obEndDate) <=0) && (obEndDate.compareTo(obReservation.obStartDate) >=0))
            {
                bOverlap = true;
                break;
            }
        }
        return bOverlap;
    }

    /**
     * Removes a reservation based on the ID input
     * @param ID
     * @return
     */
    public boolean removeReservation(int ID)
    {
        /* Create a temporary Reservation ArrayList */
        ArrayList<Reservation> tmpReservations = new ArrayList<>();
        boolean resFound = false;

        for(Reservation res: aReservation)
        {
            /*If the Reservation ID is found*/
            if (res.getID() == ID)
            {
                /*Asks for a confirmation from the user if they want to remove the reservatin */
                String sConfirm = MainConsole.Prompt("Are you sure you want to remove the reservation? (Y , N )");
                if (sConfirm.equals("Y"))
                {
                    resFound = true;
                    /* Double check if they want to remove */
                    continue;
                }
            }

            /* Add a reservation to the tmp resorvation */
            tmpReservations.add(res);
        }

        /*If the reservation is not fount */
        if (resFound == false)
            System.out.println("Reservation Not Found");

        aReservation = tmpReservations;
        return resFound;
    }

    /**
     * Check get a reservation based on the ID
     * @param ID - Inputs an ID
     */
    public static Reservation getReservation(ArrayList<Reservation> aReservation, int ID)
    {
        for(Reservation res: aReservation)
        {
            /*If the Reservation ID is found*/
            if (res.getID() == ID)
            {
                return res;
            }
        }
        return null;
    }

    /*
    A simple template class. Checks permissions. Always returns true
    because the permission system is not yet implemented.
     */
    public static boolean isValidPermissions(Manager user)
    {
        return true;
    }

    public static Manager getUser()
    {
        return user;
    }

    public ArrayList<Reservation> getReservations()
    {
        return aReservation;
    }

}
