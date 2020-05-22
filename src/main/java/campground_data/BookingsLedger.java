package campground_data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingsLedger
{
    public static ArrayList<Reservation> aReservation = new ArrayList<>();
    private ArrayList<Lot> aLot = new ArrayList<>();


    public void addReservation(int nLotID, Date obStartDate, Date obEndDate, Customer obCustomer, int nPeople)
    {
        //TODO
    }

    /**
     * checkOverlap is a method that with the lotID find a reservation and check if the specified dates overlap
     * with any other reservation in the date range. if the method returns false there is no overlap and if it returns
     * true there is a overlap.
     * @param nLotID
     * @param obStartDate
     * @param obEndDate
     * @return
     */
    public static boolean checkOverlap(int nLotID, Date obStartDate, Date obEndDate)
    {
        List<Reservation> aFilteredReservation = aReservation.stream()
                .filter(e -> e.getLot().getLotID() == nLotID)
                .collect(Collectors.toList());

        boolean bOverlap = false;
        for(Reservation obReservation : aFilteredReservation)
        {
            //If dates Overlap set bOverlap to false and break loop
            if (!((obStartDate.compareTo(obReservation.obEndDate) <=0) && (obEndDate.compareTo(obReservation.obStartDate) >=0)))
            {
                bOverlap = true;
                break;
            }
        }
        return bOverlap;
    }
}
