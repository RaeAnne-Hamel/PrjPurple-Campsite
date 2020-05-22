package campground_data;

import campground_ui.MainConsole;

import java.util.ArrayList;

/***
 * example class may be removed
 */
public class BookingsLedger {


    private static Manager user = new Manager();
    public static ArrayList<Reservation> aReservation = new ArrayList<>();

    public static Manager getUser()
    {
        return user;
    }

    public void editReservation()
    {

    }

    /*
    A simple template class. Checks permissions. Always returns true
    because the permission system is not yet implemented.
     */
    public static boolean isValidPermissions(Manager user)
    {
        return true;
    }


    /**
     * Check get a reservation based on the ID
     * @param ID - Inputs an ID
     */
    public static Reservation getReservation(int ID)
    {
        for(Reservation res: aReservation)
        {
            /*If the Reservation ID is found*/
            if (res.getReservationID() == ID)
            {
                return res;
            }
        }
        return null;
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

        String response = "yes";

        for(Reservation res: aReservation)
        {
            /*If the Reservation ID is found*/
            if (res.getReservationID() == ID)
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

/*    public CampLedger() {
    }

    private ValidationHelper vh  = new ValidationHelper();
    private HashMap<Integer, Guest> guests = new HashMap<Integer, Guest>();
    //declare controller with collection and validator as parameters
    private GuestController guestCtrl = new GuestController(guests,vh);



    //getters
    public HashMap<Integer, Guest> getGuests() {return guests;}
    public GuestController guestController() {return guestCtrl;}
    public ValidationHelper validationHelper() {return vh;}
    */
}
