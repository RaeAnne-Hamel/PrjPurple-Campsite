import campground_data.BookingsLedger;
import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class ReservationTest
{
    /*Create array of paying customers*/
    private Customer[] payingCustomers = {new Customer(0,"","","", 1  ,1,1,1,true,1),new Customer(0,"","","", 1  ,1,1,1,true,1)};

    /*Create dates of check-in and check-out*/
    private Date startDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    private Date endDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 13).getTime();

    BookingsLedger BL = new BookingsLedger();

/*Create Valid Reservation*/
    Reservation oneReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9);


    /*Test if a manager has adiquate permissions*/
    @Test
    public void testPesmissions()
    {
        //Simply returns false if permissions are Invalid
        boolean access = false;

        assertEquals(access, !BookingsLedger.isValidPermissions(BookingsLedger.getUser()));
    }


    /*If the manager inputs a reservation ID that is not present*/
    @Test
    public void testRemoveReservationNotPresent()
    {
        /*If permissions are valid*/
        boolean access = true;
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.getUser()));

        /*No reservation exists so expect false*/
        assertEquals(BL.removeReservation(0), false);
    }

    /*If the manager inputs a reservation ID that IS present it is removed*/
    @Test
    public void testRemoveReservationPresent()
    {

        /*If permissions are valid*/
        boolean access = true;
        assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.getUser()));

        /* Add a reservation, check if the reservation is added in arraylist */
        BL.getAllReservations().add(oneReservation);
        assertEquals(BL.getAllReservations().size(), 1);

        /*checks that the reservation was successfully removed */
        BL.removeReservation(0);
        assertEquals(0, BL.getAllReservations().size());
    }
}
