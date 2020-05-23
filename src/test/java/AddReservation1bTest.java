import campground_data.BookingsLedger;
import campground_data.Customer;
import campground_data.Lot;
import campground_data.LotType;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static campground_data.BookingsLedger.aReservation;

public class AddReservation1bTest {
    BookingsLedger BL = new BookingsLedger();

    //Will have a lotID of 0
    private Lot testLot = new Lot(LotType.NonServicedIndividual, true);
    private Customer[] testCustomer = {new Customer("John Doe",
            "111 A Street",
            "email@email.org",
            3424443336L,
            1532223334L,
            6535556662L)};


    @Test
    public void testBasicOverlap()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,1).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,5).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,1).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,10).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer, 2));

        aReservation.clear();
    }

    @Test
    public void testEarlyEdge()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,5).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,13).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer, 2));
        aReservation.clear();
    }

    @Test
    public void testLateEdge()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,13).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,20).getTime();

        Assert.assertTrue("Added Reservation", BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer, 2));

        aReservation.clear();
    }

    @Test
    public void testInternalOverlap()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,11).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,14).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer
                , 2));
        aReservation.clear();
    }

    @Test
    public void testImpossibleTime()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,5).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));

        aReservation.clear();
    }

    @Test
    public void testPastReservation()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2020, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2020,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));

        aReservation.clear();
    }

    @Test
    public void testOneYearIntoFuture()
    {
        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2022, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2022,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));

        aReservation.clear();
    }

    @Test
    public void testOverCapacity()
    {
        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 12));

        aReservation.clear();
    }

    //Gregorian Calendar Example stored using military time
    //GregorianCalendar(int year, int month, int date, int hour, int minute)
    //GregorianCalendar(int year, int month, int date)
    //int month is 0 based. 0 = Jan, 11 = Dec
    //Use .getTime() to return a Date object
}
