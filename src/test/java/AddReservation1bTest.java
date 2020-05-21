import junit.framework.TestCase;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddReservation1bTest extends TestCase
{

    private Lot testLot = new Lot(LotType.nonServiced,true, 0);
    private Customer testCustomer = new Customer("John Doe","111 A Street", "email@email.org", 3424443336,1532223334,6535556662);


    @Test
    public void testBasicOverlap()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,1).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,5).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021,0,1).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021,0,10).getTime();

        assertEquals("Added Reservation", addReservation(0,testStartDate, testEndDate, testCustomer, 2));
        assertEquals("Overlapping with existing reservation. Please pick a different date.", addReservation(0,secondaryStartDate,secondaryEndDate,testCustomer
        ,2));

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

        assertEquals("Added Reservation", addReservation(0,testStartDate, testEndDate, testCustomer, 2));
        assertEquals("Overlapping with existing reservation. Please pick a different date.", addReservation(0,secondaryStartDate,secondaryEndDate,testCustomer
                ,2));

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

        assertEquals("Added Reservation", addReservation(0,testStartDate, testEndDate, testCustomer, 2));
        assertEquals("Overlapping with existing reservation. Please pick a different date.", addReservation(0,secondaryStartDate,secondaryEndDate,testCustomer
                ,2));

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

        assertEquals("Added Reservation", addReservation(0,testStartDate, testEndDate, testCustomer, 2));
        assertEquals("Overlapping with existing reservation. Please pick a different date.", addReservation(0,secondaryStartDate,secondaryEndDate,testCustomer
                ,2));

    }

    @Test
    public void testImpossibleTime()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021,0,5).getTime();

        assertEquals("System returns Impossible time frame", addReservation(0,testStartDate, testEndDate, testCustomer, 2));

    }

    @Test
    public void testPastReservation()
    {

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2020, 0,10).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2020,0,15).getTime();

        assertEquals("System returns Impossible time frame", addReservation(0,testStartDate, testEndDate, testCustomer, 2));

    }


    //Gregorian Calendar Example stored using military time
    //GregorianCalendar(int year, int month, int date, int hour, int minute)
    //Use .getTime() to return a date
}
