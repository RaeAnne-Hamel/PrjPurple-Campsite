package campground_data;

import java.util.ArrayList;

/***
 * example class may be removed
 */
public class BookingsLedger {


    public static Manager user = new Manager();
    private static ArrayList<Reservation> aReservation = new ArrayList<>();

    public Manager getUser()
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

    /*Check if the inputted reservation string is a valid ID (long) */
    public boolean isValidReservation()
    {

    }



    /* Checks whether a reservation to be removed exists */
    public static Reservation getReservation(int ID)
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


    public static boolean removeReservation(int ID)
    {
        /* Create a temporary Reservation ArrayList */
        ArrayList<Reservation> tmpReservations = new ArrayList<>();
        boolean lotFound = false;

        for(Reservation res: aReservation)
        {
            /*If the Reservation ID is found*/
            if (res.getID() == ID)
            {
                lotFound = true;

                /* Double check if they want to remove */
                continue;
            }

            /* Add a reservation to the tmp resorvation */
            tmpReservations.add(res);
        }

        aReservation = tmpReservations;
        return lotFound;
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
