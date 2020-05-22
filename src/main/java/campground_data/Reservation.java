package campground_data;

import java.util.Date;

public class Reservation {
    Customer[] obCustomerList;
    Date obStartDate, obEndDate;
    String Status = "Active";
    public static int StaticReservationID = 0;
    int ReservationID, nCustomerCount;
    Lot obLot;
    double price;
    public double price;


    public Reservation(Customer[] customers, int nPeople, Date startDate, Date endDate, Lot obLot)
    {
        this.obStartDate = startDate;
        this.obEndDate = endDate;
        this.obCustomerList = customers;
        this.nCustomerCount = nPeople;

       /*Set the ID for the specific reservation*/
        ReservationID = StaticReservationID++;
        this.price = getPrice();

    }


    /**
     * this method will check to make sure that the proper number of people have been set
     * @param number
     * @return
     */
    public String setCustomerNumber(int number)
    {
        switch(obLot){
            case obLot.getLotType() == LotType.Cabin:
                return  checkSmallType(number);
                break;

            case obLot.getLotType() == LotType.ServicedIndividual:
                return  checkSmallType(number);
                break;

            case obLot.getLotType() == LotType.NonServicedIndividual:
                return  checkSmallType(number);
                break;

            case obLot.getLotType() == LotType.ServicedGroup:
                return  checkGroupType(number);
                break;

            case obLot.getLotType() == LotType.NonServicedGroup:
                return  checkGroupType(number);
                break;

            case obLot.getLotType() == LotType.DeluxeCabin:
                return  checkGroupType(number);
                break;
            default:
                return "There is already the maximum number of people booked";

    }



    }


    private String checkSmallType(int number)
    {
        if(number <= 4)
        {
            this.nCustomerCount = number;
            return "";
        }
        else
        {
            return "There is already the maximum number of people booked";
        }

    }

    private String checkGroupType(int number) {
        if (number <= 8) {
            this.nCustomerCount = number;
            return "";
        } else {
            return "There is already the maximum number of people booked";
        }
    }


        public double setDiscount ( int discount)
        {
            if (discount == 100) {
                this.price = 0.00;
                return 0.0;
            } else if (discount < 100 && discount > 0) {
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
        private double round ( double dVal, int places)
        {
            long factor = (long) Math.pow(10, places);
            dVal = dVal * factor;
            long temp = Math.round(dVal);
            return (temp / factor);

        }


        private double getPrice () {
            //for  now the price will always be 0
            //they can maualy input a price if they need.
            return 0.0;
        }

        /**
         * allows the person to be able to
         * set a price for the reservation.
         * @param newPrice
         */
        private void setPrice ( double newPrice)
        {
            this.price = newPrice;
        }

        /**
         *
         * @param
         * @return
         */
        public boolean setSiteType (LotType NewLot)
        {
            Lot TempLot = new Lot(NewLot);
            Boolean check = checkOverlap(TempLot.getLotID(), this.obStartDate, this.obEndDate);
            if (check) {
                this.obLot = TempLot;
                return true;
            } else {
                return false;
            }
        }


        public String changeDate (Date obStart, Date obEnd)
        {
            return "Need to create";
        }


}
