package campground_data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class NewLotSearch {
    private static BookingsLedger BL;

    public NewLotSearch(BookingsLedger BL)
    {
        NewLotSearch.BL = BL;
    }

    private static final long DAYS_TO_SEARCH = 30;

    public ArrayList<LocalDate> getDates(LotType nType) {

        ArrayList<LocalDate> allDates = new ArrayList<>();

        for (Lot obLot : BL.aLot) {
            if (obLot.getType().equals(nType)) {
                LocalDate localDateStart = LocalDate.now();
                LocalDate localDateEnd = localDateStart.plus(DAYS_TO_SEARCH, ChronoUnit.DAYS);


                List<LocalDate> cabinDates = IntStream.iterate(0, i -> i + 1)
                        .limit(DAYS_TO_SEARCH)
                        .mapToObj(i -> localDateStart.plusDays(i))
                        .collect(Collectors.toList());
                allDates.addAll(cabinDates);
            }
        }
        return (ArrayList<LocalDate>) allDates;
    }

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

        if (nStartDay > 31 || nEndDay > 31 || nStartDay < 1 || nEndDay < 1)
        {
            System.out.println("Please input a valid day.");
            return obLots;
        }

        Date dateToday = new Date();
        Date dateStart = new GregorianCalendar(nStartYear, nStartMonth, nStartDay).getTime();
        Date dateEnd = new GregorianCalendar(nEndYear, nEndMonth, nEndDay).getTime();

        double dSeconds = (double) (dateStart.getTime() - dateToday.getTime())/1000;
        double dYear = dSeconds/60/60/24/365;

        if (dateStart.compareTo(dateToday) < 0)
        {
            System.out.println("Start date must be after today.");
            return obLots;
        }

        if (dateStart.compareTo(dateEnd) > 0)
        {
            System.out.println("Start date must be before end date.");
            return obLots;
        }

        if (dYear > 1)
        {
            System.out.println(dSeconds);
            System.out.println("You cannot make reservations over a year from today.");
            return obLots;
        }

        for (Lot obLot : BL.aLot)
        {
            if (obLot.getType().equals(nType))
            {
                if (!BL.checkOverlap(obLot.getLotID(), dateStart, dateEnd))
                {
                    obLots.add(obLot);
                }
            }
        }
        return obLots;
    }
}
