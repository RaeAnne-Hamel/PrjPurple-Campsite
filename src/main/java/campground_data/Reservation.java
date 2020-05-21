package campground_data;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;
import java.util.Date;

public class Reservation {
    public Guest[] obCustomerList;
    public int nCustomerCount;
    public Date obStart;
    public Date obEnd;
    public String cabinType;
    public int ReservationCode;
    public static int ReservationID = 0; //TODO: find where this comed from
    public double price;


    public Reservation(Guest[] guestList, int NumberOfCustomers, Date obStart, Date EndDate, String cabinType)
    {
        this.obCustomerList = guestList;
        this.nCustomerCount = NumberOfCustomers;
        this.obStart = obStart;
        this.obEnd = EndDate;
        this.cabinType = cabinType;
        this.ReservationCode = 0;  //TODO: find where this comes from in booking leduger
        this.price = CalculatePrice();

    }



    public String setCustomerNumber(int number)
    {
        if(this.cabinType.equals("Regular"))
        {
            if(number <= 4)
            {
                return "";
            }
        }
        else if(this.cabinType.equals("Group"))
        {
            if(number <= 8)
            {
                return "";
            }
        }
        else
        {
            return "There is already the maximum number of people booked";
        }

    }

    private double CalculatePrice() {
    }




}
