import campground_data.BookingsLedger;

import campground_data.Customer;
import campground_data.Lot;
import campground_data.LotType;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DateSearchAccommodationsTest
{
    private static Lot testLot = new Lot(LotType.NonServicedIndividual, true);
    ArrayList<Lot> lotArray = new ArrayList<>();
    ArrayList<Customer> testCustomer = new ArrayList<>();
    private static Customer obCustomer = new Customer(0,"John Doe","Addr","Email@Email",1L,1L,1L,1,true,1);

    @Test
    public void FindNothingTest()
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
        Date secondaryEndDate = new GregorianCalendar(2021,0,5).getTime();

        BL.addReservation(0,testStartDate,testEndDate,testCustomer,2);

        assertEquals(0,BL.checkAvailability(secondaryStartDate,secondaryEndDate).size());
    }

}
