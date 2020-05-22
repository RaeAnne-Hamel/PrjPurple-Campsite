package campground_data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class NewLotSearch {

    private static final long DAYS_TO_SEARCH = 30;

    public static ArrayList<LocalDate> getDates(LotType nType) {

        Date dateStart = new Date();

        ArrayList<LocalDate> allDates = new ArrayList<>();

        for (Lot obLot : BookingsLedger.aLot) {
            if (obLot.getType.equals(nType)) {
                LocalDate localDateStart = LocalDate.now();
                LocalDate localDateEnd = localDateStart.plus(DAYS_TO_SEARCH, ChronoUnit.DAYS);


                List<LocalDate> cabinDates = IntStream.iterate(0, i -> i + 1)
                        .limit(DAYS_TO_SEARCH)
                        .mapToObj(i -> localDateStart.plusDays(i))
                        .collect(Collectors.toList());
                for (LocalDate obDate : cabinDates)
                {
                    allDates.add(obDate);
                }
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
    public static ArrayList<Lot> chooseDate(LotType nType, int nStartYear, int nStartMonth, int nStartDay, int nEndYear, int nEndMonth, int nEndDay) {
        ArrayList<Lot> obLots = new ArrayList<>();

        Date dateStart = new Date(nStartYear, nStartMonth, nStartDay);
        Date dateEnd = new Date(nEndYear, nEndMonth, nEndDay);

        for (Lot obLot : BookingsLedger.aLot)
        {
            if (obLot.getType.equals(nType))
            {
                if (!checkOverlap(obLot.getLotID(), dateStart, dateEnd))
                {
                    obLots.add(obLot);
                }
            }
        }

//        if (dateStart.compareTo(dateEnd) > 0)
//        {
//            throw new Exception("Start date must be before end date.");
//        }
        return obLots;
    }

    @Override
    public String toString(){
        return null;
    }
}
