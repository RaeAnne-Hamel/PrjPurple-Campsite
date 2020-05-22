package campground_data;

import java.util.Date;

public class Reservation {

    Lot obLot;
    Customer[] obCustomerList;
    int nCustomerCount;
    Date obStartDate;
    Date obEndDate;
    int ReservationID;
    int StaticReservationID = 0;
    double price;
    String Status = "Active";

    /*Reservation Constructor */
    public Reservation(Lot Lot, Date startDate, Date endDate, Customer[] customers, int nPeople)
    {
        /*Set the passed in pramaters*/
        this.obLot = Lot;
        this.obStartDate = startDate;
        this.obEndDate = endDate;
        this.obCustomerList = customers;
        this.nCustomerCount = nPeople;

        /*Set the ID for the specific reservation*/
        ReservationID = StaticReservationID++;
    }

    public int getReservationID()
    {
        return this.ReservationID;
    }

}
