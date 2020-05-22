package campground_data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class NewLotSearch {

    public static Date[] getDates(String nType) {

        Date dateStart = new Date();


        for (Lot obLot : BookingsLedger.aLot) {
            if (obLot.getType.equals(nType)) {
                
            }
        }

        return null;
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
    public static ArrayList<Lot> chooseDate(String nType, int nStartYear, int nStartMonth, int nStartDay, int nEndYear, int nEndMonth, int nEndDay) {
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

        //BookingsLedger.checkOverlap()

//        LocalDate localDateStart = dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate localDateEnd = dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        long lDaysBetween = ChronoUnit.DAYS.between(localDateStart, localDateEnd);
//
//        List<LocalDate> allDates = IntStream.iterate(0, i -> i + 1)
//                .limit(lDaysBetween)
//                .mapToObj(i -> localDateStart.plusDays(i))
//                .collect(Collectors.toList());

        return obLots;
    }
}
