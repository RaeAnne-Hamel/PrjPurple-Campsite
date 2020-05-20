import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Search3aTest {
    int nStartDay;
    int nEndDay;
    int nStartMonth;
    int nEndMonth;
    int nStartYear = 2020;
    int nEndYear = 2020;
    Lot[] obLotResults;
    final int TOTAL_LOTS = 28;


    /***
     * VALID: Dates appropriate
     */
    @Test
    public void testOpenDates() {
        nStartDay = 1;
        nEndDay = 8;
        nStartMonth = 8;
        nEndMonth = 8;


        obLotResults = chooseDate(nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.length, TOTAL_LOTS);
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

        obLotResults = chooseDate(nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.length, 0);
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

        obLotResults = chooseDate(nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.length, 0);
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

        obLotResults = chooseDate(nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.length, 0);
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

        obLotResults = chooseDate(nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.length, TOTAL_LOTS);
    }

    /**
     * INVALID: June only has 30 days.
     */
    public void testInvalidDayAfter() {
        nStartDay = 31;
        nStartMonth = 6;
        nEndDay = 7;
        nEndMonth = 7;

        obLotResults = chooseDate(nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.length, 0);
    }

    /**
     * INVALID: There is no day 0.
     */
    public void testInvalidDayBefore() {
        nStartDay = 0;
        nStartMonth = 8;
        nEndDay = 7;
        nEndMonth = 8;

        obLotResults = chooseDate(nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);

        assertEquals(obLotResults.length, 0);
    }
}
