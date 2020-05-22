package campground_data;

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
        this.price = getPrice();

    }


    /**
     * this method will check to make sure that the proper number of people have been set
     * @param number
     * @return
     */
    public String setCustomerNumber(int number)
    {
        if(this.cabinType.equals("Regular"))
        {
            if(number <= 4)
            {
                this.nCustomerCount = number;
                return "";
            }
        }
        else if(this.cabinType.equals("Group"))
        {
            if(number <= 8)
            {
                this.nCustomerCount = number;
                return "";
            }
        }
        else
        {
            this.nCustomerCount = number;
            return "There is already the maximum number of people booked";
        }
        return "Somthing has gone wrong";

    }

    public double setDiscount(int discount)
    {
        if(discount == 100)
        {
            this.price = 0.00;
            return 0.0;
        }
        else if (discount <100 && discount > 0)
        {
            double dCalPrice = this.getPrice() * (discount / 100);
            dCalPrice = round(dCalPrice, 2);
            this.price = dCalPrice;
            return dCalPrice;

        }
        //if the number is a bad number
        //we will not change the price of the object.
        return this.getPrice();
    }

    /**
     * this is a method to round the numbers when
     * calulating the price of items.
     * @param dVal
     * @param places
     * @return
     */
    private double round(double dVal, int places)
    {
        long factor = (long) Math.pow(10, places);
        dVal = dVal * factor;
        long temp = Math.round(dVal);
        return (temp / factor);

    }

    //TODO: find id there is some kind of price guid that sows how to do this
    private double getPrice() {
        return 0.0;


    }

    public boolean setSiteType(String siteType){

        return true;
    }


    public String changeDate(Date obStart, Date obEnd)
    {
        return "Need to create";
    }




}
