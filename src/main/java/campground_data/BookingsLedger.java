package campground_data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingsLedger
{
    public ArrayList<Reservation> aReservation = new ArrayList<>();
    public ArrayList<Lot> aLot = new ArrayList<>();


    /**
     * addReservation will add a new Reservation into the aReservation ArrayList while checking and preventing any
     * issues such as overlapping booking and overbooking. The method will return true if it has succeeded and false
     * if it has failed.
     * @param nLotID
     * @param obStartDate
     * @param obEndDate
     * @param obCustomers
     * @param nPeople
     * @return
     */
    public boolean addReservation(int nLotID, Date obStartDate, Date obEndDate, Customer[] obCustomers, int nPeople)
    {
        List<Lot> filteredLots = aLot.stream()
                .filter(e-> e.getLotID() == nLotID)
                .collect(Collectors.toList());

        //Check if list is more than 0 entries. If so fail and return false
        if(filteredLots.size() == 0)
        {
            System.out.printf("LotId not found\n");
            return false;
        }

        Lot obLot = filteredLots.get(0);

        //Get todays date
        Date obToday = new Date();
        //Get todays date one year from now
        Date obYearAfter = new Date();
        obYearAfter.setYear(obYearAfter.getYear()+1);

        //If there are more than 2 customers fail and return false
        if(obCustomers.length > 2)
        {
            System.out.printf("More than 2 Customers\n");
            return false;
        }

        //If there is a overlap with another reservation it will fail and return false
        if(checkOverlap(nLotID,obStartDate,obEndDate))
        {
            System.out.printf("Overlaps with existing reservation\n");
            return false;
        }

        //Check if endDate is before startDate. If so it will fail and return false
        if(obStartDate.compareTo(obEndDate) >0)
        {
            System.out.printf("Invalid date\n");
            return false;
        }

        //Check if obStartDate is placed before today
        if (obStartDate.compareTo(obToday) <0)
        {
            System.out.printf("Reservation placed in past\n");
            return false;
        }

        //Check if obStartDate is over one year into the future
        if(obStartDate.compareTo(obYearAfter) >0)
        {
            System.out.printf("Reservation placed one year into future\n");
            return false;
        }

        switch (obLot.getLotType())
        {
            case Cabin:
            case DeluxeCabin:
                break;

            case NonServicedIndividual:
            case ServicedIndividual:
                if(nPeople > 4) {
                    System.out.printf("Overcapacity\n");
                    return false;}
                break;

            case NonServicedGroup:
            case ServicedGroup:
                if(nPeople > 8) {
                    System.out.printf("Overcapacity\n");
                    return false;}
                break;

            default:
                return false;
        }



        //If all checks have succeeded add the reservation to aReservations and return true
        System.out.printf("Adding Reservation...\n");
        aReservation.add(new Reservation(obLot, obStartDate, obEndDate, obCustomers, nPeople));
        System.out.printf("Reservation Added\n");
        return true;

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
    public boolean checkOverlap(int nLotID, Date obStartDate, Date obEndDate)
    {
        List<Reservation> aFilteredReservation = aReservation.stream()
                .filter(e -> e.getLot().getLotID() == nLotID)
                .collect(Collectors.toList());

        boolean bOverlap = false;
        for(Reservation obReservation : aFilteredReservation)
        {
            //If dates Overlap set bOverlap to false and break loop
            if ((obStartDate.compareTo(obReservation.obEndDate) <=0) && (obEndDate.compareTo(obReservation.obStartDate) >=0))
            {
                bOverlap = true;
                break;
            }
        }
        return bOverlap;
    }

}
