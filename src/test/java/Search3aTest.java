import campground_data.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static campground_data.LotType.*;
import static org.junit.Assert.assertEquals;

public class Search3aTest {
    int nStartDay;
    int nEndDay;
    int nStartMonth;
    int nEndMonth;
    int nStartYear = 2020;
    int nEndYear = 2020;
    ArrayList<Lot> obLotResults = new ArrayList<>();
    final int TOTAL_LOTS = 3;
    LotType nType = ServicedIndividual;
    NewLotSearch NLS;

    @Before
    public void setup(){
        ArrayList<Lot> aLot = new ArrayList<>();

        aLot.add(new Lot(1));
        aLot.add(new Lot(2));
        aLot.add(new Lot(3));

        ArrayList<Customer> aCustomer = new ArrayList<>();
        ArrayList<Reservation> aReservation = new ArrayList<>();

        aCustomer.add(new Customer(1, "bob", "215 bob street", "bob@bob.com", 3, 7801234, 3144567, 4, true, 3));

        aReservation.add(new Reservation(new Lot(1), new GregorianCalendar(2020, 7, 1).getTime(),
                new GregorianCalendar(2020, 7, 8).getTime(), aCustomer, 4));


        BookingsLedger BL = new BookingsLedger(aLot, aReservation, null, null);

        NLS = new NewLotSearch(BL);
    }

    /***
     * VALID: Dates appropriate
     */
    @Test
    public void testOpenDates() {
        nStartDay = 1;
        nEndDay = 8;
        nStartMonth = 9;
        nEndMonth = 9;


        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), TOTAL_LOTS);
    }

    /**
     * VALID: The two lots that aren't reserved are returned.
     */
    @Test
    public void testReservedDates() {
        nStartDay = 1;
        nStartMonth = 8;
        nEndDay = 8;
        nEndMonth = 8;

        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 2);
    }

    /**
     * INVALID: Can't search dates in past.
     */
    @Test
    public void testPastDate() {
        nStartYear = 2019;
        nEndYear = 2019;
        nStartDay = 1;
        nStartMonth = 7;
        nEndDay = 8;
        nEndMonth = 7;

        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * INVALID: Can't search over 1 year in future.
     */
    @Test
    public void testOverOneYearInFuture() {
        nStartYear = 2021;
        nEndYear = 2021;
        nStartDay = 1;
        nStartMonth = 7;
        nEndDay = 8;
        nEndMonth = 7;

        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * VALID: Can reserve if starting in under a year and ending over a year from now
     */
    @Test
    public void testStartingUnderAYearEndingOverAYear() {
        nStartYear = 2021;
        nEndYear = 2021;
        nStartDay = 20;
        nStartMonth = 4;
        nEndDay = 30;
        nEndMonth = 4;

        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 3);
    }

    /**
     * INVALID: No month has more than 31 days.
     */
    @Test
    public void testInvalidDayAfter() {
        nStartDay = 32;
        nStartMonth = 6;
        nEndDay = 7;
        nEndMonth = 7;

        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * INVALID: There is no day 0.
     */
    @Test
    public void testInvalidDayBefore() {
        nStartDay = 0;
        nStartMonth = 8;
        nEndDay = 7;
        nEndMonth = 8;

        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * INVALID: Can't make a reservation that ends before it starts.
     */
    @Test
    public void testEndBeforeStart() {
        nStartDay = 7;
        nStartMonth = 7;
        nEndDay = 1;
        nEndMonth = 7;

        obLotResults = NLS.chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

//    /**
//     * VALID: Test that the next 30 days are returned for each lot.
//     */
//    @Test
//    public void testDatesReturned() {
//        ArrayList<LocalDate> allDates = NLS.getDates(nType);
//
//        assertEquals(allDates.size(), 90);
//    }
}
