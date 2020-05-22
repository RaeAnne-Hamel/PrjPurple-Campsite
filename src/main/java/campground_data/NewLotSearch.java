package campground_data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NewLotSearch {

    public static Date[] getDates(Enum nType) {

        return null;
    }

    public static ArrayList<Lot> chooseDate(int nStartYear, int nStartMonth, int nStartDay, int nEndYear, int nEndMonth, int nEndDay) throws Exception {

        ArrayList<Lot> obLots = new ArrayList<>();

        Date dateStart = new Date(nStartYear, nStartMonth, nStartDay);
        Date dateEnd = new Date(nEndYear, nEndMonth, nEndDay);

        if (dateStart.compareTo(dateEnd) > 0)
        {
            throw new Exception("Start date must be before end date.");
        }

        //BookingsLedger.checkOverlap()

        LocalDate localDateStart = dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateEnd = dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long lDaysBetween = ChronoUnit.DAYS.between(localDateStart, localDateEnd);

        List<LocalDate> allDates = IntStream.iterate(0, i -> i + 1)
                .limit(lDaysBetween)
                .mapToObj(i -> localDateStart.plusDays(i))
                .collect(Collectors.toList());



        Date dateDiff = new Date(dateEnd.getTime() - dateStart.getTime());

        return obLots;
    }
}
