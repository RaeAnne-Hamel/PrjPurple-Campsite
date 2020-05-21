package campground_data;

import java.util.ArrayList;
import java.util.Date;

public class NewLotSearch {


    public static ArrayList<Lot> chooseDate(int nStartYear, int nStartMonth, int nStartDay, int nEndYear, int nEndMonth, int nEndDay) {



        ArrayList<Lot> obLots = new ArrayList<>();

        Date dateStart = new Date(nStartYear, nStartMonth, nStartDay);
        Date dateEnd = new Date(nEndYear, nEndMonth, nEndDay);

        if (dateStart.compareTo(dateEnd) > 0)
        {
            return null;
        }
        
        return obLots;

    }
}
