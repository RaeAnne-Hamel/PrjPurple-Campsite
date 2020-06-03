import campground_data.*;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DateSearchAccommodations6cTest {


    ArrayList<Lot> lotArray = new ArrayList<>();
    ArrayList<Customer> testCustomer = new ArrayList<>();
    private static Customer obCustomer = new Customer("First", "Last", "Address", "Province",
            "City", "Postal", "Country", "Email",3064443333L);


    @Test
    public void FindNothingTest() {
        Lot testLot = new Lot(0);
        lotArray.add(testLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0, 1).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021, 0, 5).getTime();

        BL.addReservation(0, testStartDate, testEndDate, testCustomer, 2);

        assertEquals(0, BL.checkAvailability(testStartDate, testEndDate).size());
    }

    @Test
    public void FindOneAccommodationTest() {
        Lot testLot = new Lot(1);
        Lot secondLot = new Lot(2);
        lotArray.add(testLot);
        lotArray.add(secondLot);
        testCustomer.add(obCustomer);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);

        //GregorianCalendar of Jan 1st, 2021 12:00PM
        Date testStartDate = new GregorianCalendar(2021, 0, 1).getTime();
        //GregorianCalendar of Jan 5th, 2021 12:00PM
        Date testEndDate = new GregorianCalendar(2021, 0, 5).getTime();

        Date secondaryStartDate = new GregorianCalendar(2021, 0, 1).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021, 0, 5).getTime();

        BL.addReservation(1, testStartDate, testEndDate, testCustomer, 2);

        assertEquals(1, BL.checkAvailability(secondaryStartDate, secondaryEndDate).size());
    }

    @Test
    public void FindThreeAccommodationTest() {
        Lot testLot = new Lot(3);
        Lot secondLot = new Lot(4);
        Lot thirdLot = new Lot(5);
        lotArray.add(testLot);
        lotArray.add(secondLot);
        lotArray.add(thirdLot);
        BookingsLedger BL = new BookingsLedger();
        BL.setLotList(lotArray);


        Date secondaryStartDate = new GregorianCalendar(2021, 0, 10).getTime();
        Date secondaryEndDate = new GregorianCalendar(2021, 0, 15).getTime();


        assertEquals(3, BL.checkAvailability(secondaryStartDate, secondaryEndDate).size());
    }

    @Test
    public void InvalidDateTest() {
        {
            Lot testLot = new Lot(6);
            lotArray.add(testLot);
            BookingsLedger BL = new BookingsLedger();
            BL.setLotList(lotArray);


            Date secondaryStartDate = new GregorianCalendar(2021, 0, 1).getTime();
            Date secondaryEndDate = new GregorianCalendar(2021, 0, 5).getTime();


            assertEquals(null, BL.checkAvailability(secondaryEndDate, secondaryStartDate));
        }

    }

}
