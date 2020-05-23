import campground_data.BookingsLedger;
import campground_data.Customer;
import campground_data.Lot;
import campground_data.LotType;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddReservation1bTest {


    //Will have a lotID of 0
    private static Lot testLot = new Lot(LotType.NonServicedIndividual, true);
    private Customer[] testCustomer = {new Customer("John Doe",
            "111 A Street",
            "email@email.org",
            3424443336L,
            1532223334L,
            6535556662L)};


    @Test
    public void testBasicOverlap()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);
        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,1).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,5).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,1).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,10).getTime();

        Date thirdStartDate = new GregorianCalendar(2021,2, 1).getTime();
        Date thirdEndDate = new GregorianCalendar(2021, 2,1).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer, 2));
        Assert.assertTrue(BL.addReservation(0,thirdStartDate,thirdEndDate,testCustomer,2));
    }

    @Test
    public void testEarlyEdge()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,5).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,13).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer, 2));
    }

    @Test
    public void testLateEdge()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,13).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,20).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer, 2));
    }

    @Test
    public void testInternalOverlap()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,11).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,14).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer
                , 2));
    }

    @Test
    public void testImpossibleTime()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,5).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
    }

    @Test
    public void testPastReservation()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2020, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2020,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
    }

    @Test
    public void testOneYearIntoFuture()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2022, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2022,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
    }

    @Test
    public void testOverCapacity()
    {
        BookingsLedger BL = new BookingsLedger();
        BL.aLot.add(testLot);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 12));
    }

    //Gregorian Calendar Example stored using military time
    //GregorianCalendar(int year, int month, int date, int hour, int minute)
    //GregorianCalendar(int year, int month, int date)
    //int month is 0 based. 0 = Jan, 11 = Dec
    //Use .getTime() to return a Date object
}
