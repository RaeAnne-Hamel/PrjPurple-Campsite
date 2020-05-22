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
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.user));
    }

/*If a manager has adequate permissions the user should be prompted for ID*/
    @Test
    public void testRemoveReservationMethod()
    {
        //Simply returns true if permissions are valid
        boolean access = true;
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.user));

        //Simply returns true if permissions are valid
        assertEquals(oneReservation.getID(), BL.removeReservation(1));

    }

/*If the manager inputs a reservation ID that is not present*/
    @Test
    public void testRemoveReservationNotPresent()
    {
        //Simply returns true if permissions are valid
        boolean access = true;
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.user));

        //Simply returns true if permissions are valid
        assertEquals(oneReservation.getID(), BL.removeReservation(123456789));
    }

/*If a manager is prompted with a 1 or a 0 to confirm or cancel the reservation*/
    @Test
    public void testRemoveReservation()
    {
        //Simply returns true if permissions are valid
        boolean access = true;
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.user));

        //Simply returns true if permissions are valid
        assertEquals(oneReservation.getID(), BL.removeReservation(1));

    }
}
