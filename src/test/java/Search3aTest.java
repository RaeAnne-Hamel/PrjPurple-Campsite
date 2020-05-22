import campground_data.LotType;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static campground_data.LotType.*;
import static campground_data.NewLotSearch.getDates;
import static org.junit.Assert.assertEquals;
import static campground_data.NewLotSearch.chooseDate;

public class Search3aTest {
    int nStartDay;
    int nEndDay;
    int nStartMonth;
    int nEndMonth;
    int nStartYear = 2020;
    int nEndYear = 2020;
    ArrayList<Lot> obLotResults = new ArrayList<>();
    final int TOTAL_LOTS = 3;
    LotType nType = Cabin;

    /***
     * VALID: Dates appropriate
     */
    @Test
    public void testOpenDates() {
        nStartDay = 1;
        nEndDay = 8;
        nStartMonth = 8;
        nEndMonth = 8;

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), TOTAL_LOTS);
    }

    /**
     * VALID: No dates returned
     */
    @Test
    public void testReservedDates() {
        nStartDay = 1;
        nStartMonth = 7;
        nEndDay = 8;
        nEndMonth = 7;

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
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

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

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

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * VALID: Can reserve if starting in under a year and ending over a year from now
     */
    @Test
    public void testStartingUnderAYearEndingOverAYear() {
        nStartYear = 2021;
        nEndYear = 2021;
        nStartDay = 5;
        nStartMonth = 20;
        nEndDay = 6;
        nEndMonth = 30;

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), TOTAL_LOTS);
    }

    /**
     * INVALID: June only has 30 days.
     */
    public void testInvalidDayAfter() {
        nStartDay = 31;
        nStartMonth = 6;
        nEndDay = 7;
        nEndMonth = 7;

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * INVALID: There is no day 0.
     */
    public void testInvalidDayBefore() {
        nStartDay = 0;
        nStartMonth = 8;
        nEndDay = 7;
        nEndMonth = 8;

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * INVALID: Can't make a reservation that ends before it starts.
     */
    public void testEndBeforeStart() {
        nStartDay = 7;
        nStartMonth = 7;
        nEndDay = 1;
        nEndMonth = 7;

        obLotResults = chooseDate(nType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.size(), 0);
    }

    /**
     * VALID: Test that the next 30 days are returned for each cabin.
     */
    public void testDatesReturned() {
        ArrayList<LocalDate> allDates = getDates(nType);

        assertEquals(allDates.size(), 90);
    }
}
