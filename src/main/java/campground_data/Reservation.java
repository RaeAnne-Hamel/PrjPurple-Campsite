package campground_data;

import java.util.Date;

public class Reservation {



    Lot obLot;
    Customer[] obCustomerList;
    int nCustomerCount;
    Date obStartDate;
    Date obEndDate;
    int ReservationID;
    static int StaticReservationID = 0;
    double price;
    public Boolean status;

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
        this.ReservationID = StaticReservationID++;
        this.price = 100.00;
        this.status = true;

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

}
