import campground_data.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddReservation1bTest {


    //Will have a lotID of 0
    private static Lot testLot = new Lot(0, LotType.ServicedIndividual,new ArrayList<Reservation>(),0,"",true);
    ArrayList<Lot> lotArray = new ArrayList<>();
    ArrayList<Customer> testCustomer = new ArrayList<>();
    private static Customer obCustomer = new Customer("John", "Doe","Addr","Addr","Addr","Addr","Addr","Email@Email",1234567890L,1234567890L,1234567890L,1,true);


    @Test
    public void testBasicOverlap()
    {
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);



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
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2020, 6,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2020,6,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,5).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,13).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer, 2));
    }

    @Test
    public void testLateEdge()
    {
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

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
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2020, 7,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2020,7,15).getTime();

        Date secondaryStartDate = new GregorianCalendar(2020,7,11).getTime();
        Date secondaryEndDate = new GregorianCalendar(2020,7,14).getTime();

        Assert.assertTrue(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
        Assert.assertFalse(BL.addReservation(0, secondaryStartDate, secondaryEndDate, testCustomer
                , 2));
    }

    @Test
    public void testImpossibleTime()
    {
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,5).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
    }

    @Test
    public void testPastReservation()
    {
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2020, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2020,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
    }

    @Test
    public void testOneYearIntoFuture()
    {
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2022, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2022,0,15).getTime();

        Assert.assertFalse(BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2));
    }

    @Test
    public void testOverCapacity()
    {
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

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
