import campground_data.BookingsLedger;
import campground_data.Customer;
import campground_data.Reservation;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class ReservationTest
{
    /*Create array of paying customers*/
    private Customer[] payingCustomers = {new Customer(),new Customer()};

    /*Create dates of check-in and check-out*/
    private Date startDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    private Date endDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 13).getTime();

    BookingsLedger BL = new BookingsLedger();

/*Create Valid Reservation*/
    Reservation oneReservation = new Reservation(1,startDate,endDate,payingCustomers,9);

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

        assertEquals(BL.removeReservation(0), BL.removeReservation(123456789));
    }

    /*If the manager inputs a reservation ID that IS present*/
    @Test
    public void testRemoveReservationPresent()
    {
        boolean access = true;
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.getUser()));

        assertEquals(BL.removeReservation(0), false);
    }
}
