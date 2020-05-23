package campground_data;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {

    /*Primitive Variables */
    public static int StaticReservationID = 0;
    int ReservationID, nCustomerCount;
    double price;
    Lot obLot;
    ArrayList<Customer> obCustomerList;
    Date obStartDate;
    Date obEndDate;
    String Status = "Active";




    /*Reservation Constructor */
    public Reservation(Lot obLot, Date startDate, Date endDate, ArrayList<Customer> customers, int nPeople)
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


    public Lot getLot() {
        return this.obLot;
    }

    public int getReservationID()
    {
        return this.ReservationID;
    }

    @Override
    public String toString() {
        return "Reservation: " +
                "ID: " + ReservationID +
                "\t " + obStartDate +
                " - " + obEndDate;
    }
}
