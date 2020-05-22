package campground_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/***
 * example class may be removed
 */
public class BookingsLedger {


    public static Manager user = new Manager();
    public ArrayList<Reservation> aReservation

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

    public static boolean removeReservation(int ID)
    {

        return true;
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
