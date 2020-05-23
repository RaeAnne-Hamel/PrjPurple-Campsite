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
    public Boolean status;





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
        this.ReservationID = StaticReservationID;
        StaticReservationID++;
        this.price = 100.00;
        this.status = true;

    }


    public Lot getLot() {
        return this.obLot;
    }

    public int getReservationID()
    {
        return this.ReservationID;
    }


    //get the status of the reservation option
    public boolean getStatus() {
        return this.status;
    }

    /**
     * changes the status
     */
    public void setStatus(){
        this.status = false;
    }

    public double getPrice(){
        //for  now the price will always be 0
        //they can manually input a price if they need.

        return this.price;
         }


    @Override
    public String toString() {
        return "Reservation: " +
                "ID: " + ReservationID +
                "\t " + obStartDate +
                " - " + obEndDate;
    }

}
