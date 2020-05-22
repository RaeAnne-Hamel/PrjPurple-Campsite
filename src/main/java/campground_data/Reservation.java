package campground_data;

import java.util.Date;

public class Reservation {

    /*Primative Variables */
    public static int StaticReservationID = 0;
    int nLotID, ReservationID, nCustomerCount;
    double price;

    /*Reference Variables*/
    Customer[] obCustomerList;
    Date obStartDate, obEndDate;
    String Status = "Active";

    /*Reservation Constructor */
    public Reservation(int lotID, Date startDate, Date endDate, Customer[] customers, int nPeople)
    {
        /*Set the passed in pramaters*/
        this.nLotID = lotID;
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

    



}
