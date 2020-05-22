package campground_data;

import java.util.Date;

public class Reservation {

    /*Primitive Variables */
    public static int StaticReservationID = 0;
    int ReservationID, nCustomerCount;
    double price;
    Lot obLot;

    /*Reference Variables*/
    Customer[] obCustomerList;
    Date obStartDate, obEndDate;
    String Status = "Active";

    /*Reservation Constructor */
    public Reservation(Lot obLot, Date startDate, Date endDate, Customer[] customers, int nPeople)
    {
        /*Set the passed in parameters*/
        this.obLot = obLot;
        this.obStartDate = startDate;
        this.obEndDate = endDate;
        this.obCustomerList = customers;
        this.nCustomerCount = nPeople;

        /*Set the ID for the specific reservation*/
        ReservationID = StaticReservationID++;


    }

    public int getID()
    {
        return this.ReservationID;
    }
    public Lot getLot() { return this.obLot; }
}
