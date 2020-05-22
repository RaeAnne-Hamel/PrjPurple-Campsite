package campground_data;

import java.util.Date;

public class Reservation {


    public Boolean status;
    public static int StaticReservationID = 0;
    public int ReservationID;

    public double price;

    public Reservation()
    {
        ReservationID = StaticReservationID++;
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
    public void setStatus() {
        this.status = false;
    }

    public double getPrice () {
        //for  now the price will always be 0
        //they can manually input a price if they need.

        return this.price;
         }
}
