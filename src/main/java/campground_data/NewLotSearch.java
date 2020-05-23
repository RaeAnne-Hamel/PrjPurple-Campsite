package campground_data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Class created by Dustin Wiebe.
 */
public class NewLotSearch {
    private static BookingsLedger BL;

    /**
     * Externally accessible constructor for New Lot Search.
     * @param BL
     */
    public NewLotSearch(BookingsLedger BL)
    {
        NewLotSearch.BL = BL;
    }


//    private static final long DAYS_TO_SEARCH = 30;
//
//    /**
//     * Displays the next 30 days of available reservations for one type of lot.
//     * @param nType
//     * @return
//     */
//    public ArrayList<LocalDate> getDates(LotType nType) {
//
//        ArrayList<LocalDate> allDates = new ArrayList<>();
//
//        //Checks every stored lot to see if it's the same type. Lists the available dates
//        for (Lot obLot : BL.aLot) {
//            if (obLot.getType().equals(nType)) {
//                LocalDate localDateStart = LocalDate.now();
//                LocalDate localDateEnd = localDateStart.plus(DAYS_TO_SEARCH, ChronoUnit.DAYS);
//
//
//                List<LocalDate> cabinDates = IntStream.iterate(0, i -> i + 1)
//                        .limit(DAYS_TO_SEARCH)
//                        .mapToObj(i -> localDateStart.plusDays(i))
//                        .collect(Collectors.toList());
//                allDates.addAll(cabinDates);
//            }
//        }
//        return (ArrayList<LocalDate>) allDates;
//    }

    /**
     * chooseDate will take in the type of lot to be reserved, a starting year/month/day, and an ending year/month/day. It will return a list of all lots of that type
     * available to reserve for that period of dates.
     * @param nType
     * @param nStartYear
     * @param nStartMonth
     * @param nStartDay
     * @param nEndYear
     * @param nEndMonth
     * @param nEndDay
     * @return
     */
    public ArrayList<Lot> chooseDate(LotType nType, int nStartYear, int nStartMonth, int nStartDay, int nEndYear, int nEndMonth, int nEndDay) {

        ArrayList<Lot> obLots = new ArrayList<>();

        //Adjusts the month from a 1-12 system to a 0-11 system.
        nStartMonth--;
        nEndMonth--;

        //Checks to see if it's a real day.
        if (nStartDay > 31 || nEndDay > 31 || nStartDay < 1 || nEndDay < 1)
        {
            System.out.println("Please input a valid day.");
            return obLots;
        }

        //Creates Date objects for today, the start date, and the end date.
        Date dateToday = new Date();
        Date dateStart = new GregorianCalendar(nStartYear, nStartMonth, nStartDay).getTime();
        Date dateEnd = new GregorianCalendar(nEndYear, nEndMonth, nEndDay).getTime();

        //Checks to see if the start date is over a year's worth of seconds in the future.
        double dSeconds = (double) (dateStart.getTime() - dateToday.getTime())/1000;
        double dYear = dSeconds/60/60/24/365;
        if (dYear > 1)
        {
            System.out.println(dSeconds);
            System.out.println("You cannot make reservations over a year from today.");
            return obLots;
        }

        //Checks if the reservation is before today.
        if (dateStart.compareTo(dateToday) < 0)
        {
            System.out.println("Start date must be after today.");
            return obLots;
        }

        //Checks if the start date is before the end date.
        if (dateStart.compareTo(dateEnd) > 0)
        {
            System.out.println("End date must be after start date.");
            return obLots;
        }

        //Errors out if there are no lots in the system.
        if (BL.aLot.size() == 0)
        {
            System.out.println("No lots to reserve.");
            return obLots;
        }

        //Checks all the lots to see if they're the right type and are available.
        for (Lot obLot : BL.aLot)
        {
            if (obLot.getLotType() == nType)
            {
                if (!BL.checkOverlap(obLot.getLotID(), dateStart, dateEnd))
                {
                    obLots.add(obLot);
                }
            }
        }

        //Tells user if no lots are available to reserve on those dates.
        if (obLots.size() == 0)
        {
            System.out.println("No lots available.");
        }
        return obLots;
    }
}
