import campground_data.BookingsLedger;
import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class ReservationTest
{
    /*Create array of paying customers*/

    ArrayList<Customer> payingCustomers;

    @Before
    public void setup()
    {
         payingCustomers = new ArrayList<>();
        payingCustomers.add(new Customer(0,"","","", 1  ,1,1,1,true,1));
        payingCustomers.add(new Customer(0,"","","", 1  ,1,1,1,true,1));


    }

    /*Create dates of check-in and check-out*/
    private Date startDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    private Date endDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 13).getTime();
    Reservation oneReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9);
    BookingsLedger BL = new BookingsLedger();

/*Create Valid Reservation*/



    /*Test if a manager attempting to remove a reservation has IN adequate permissions*/
    @Test
    public void testPesmissions()
    {
        //Simply returns false if permissions are Invalid
        boolean access = false;

        assertEquals(access, !BookingsLedger.isValidPermissions(BookingsLedger.getUser()));
        //assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.user));
    }


    /*If the manager inputs a reservation ID that is not present*/
    @Test
    public void testRemoveReservationNotPresent()
    {
        boolean access = true;
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.getUser()));

        assertEquals(BL.removeReservation(0), false);
    }

    /*If the manager inputs a reservation ID that IS present*/
    @Test
    public void testRemoveReservationPresent()
    {

        boolean access = true;

        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.getUser()));

        /*checks that the reservation was added */
        BL.getAllReservations().add(oneReservation);
        assertEquals(BL.getAllReservations().size(), 1);

        /*checks that the reservation was successfully removed */
        BL.removeReservation(0);
        assertEquals(0, BL.getAllReservations().size());
    }
}
