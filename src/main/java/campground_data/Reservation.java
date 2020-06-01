package campground_data;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Reservation<Static> {

    /*Primitive Variables */
    int ReservationID, nCustomerCount;
    double price;
    Lot obLot;
    ArrayList<Customer> obCustomerList;
    Date obStartDate;
    Date obEndDate;
    public Boolean status;

    /*Reservation Constructor */
    public Reservation(Lot obLot, Date startDate, Date endDate, ArrayList<Customer> customers, int nPeople, int StaticReservationID)
    {

        /*Set the passed in parameters*/
        this.obLot = obLot;
        this.obStartDate = startDate;
        this.obEndDate = endDate;
        this.obCustomerList = customers;
        this.nCustomerCount = nPeople;

        /*Set the ID for the specific reservation*/
        this.ReservationID = StaticReservationID++;
        this.price = 100.00;
        this.status = true;

    }
    /*Reservation Constructor */
    public Reservation(ArrayList<Customer> customers, int nPeople, Date startDate, Date endDate, Lot obLot)
    {
            this.obLot = obLot;
            this.obCustomerList = customers;
            this.nCustomerCount = nPeople;
            this.obStartDate = startDate;
            this.obEndDate = endDate;
            int StaticReservationID = 0;
            double price;
            String Status = "Active";
    }



    /**
     * this method will check to make sure that the proper number of people have been set
     *
     * @param number
     * @return
     */
    public String setCustomerNumber(int number) {
        if (obLot.getLotType() == LotType.Cabin) {
            return checkRegularType(number);
        } else if (obLot.getLotType() == LotType.ServicedIndividual) {
            return checkRegularType(number);
        } else if (obLot.getLotType() == LotType.NonServicedIndividual) {
            return checkRegularType(number);
        } else if (obLot.getLotType() == LotType.ServicedGroup) {
            return checkGroupType(number);
        } else if (obLot.getLotType() == LotType.NonServicedGroup) {
            return checkGroupType(number);
        } else if (obLot.getLotType() == LotType.DeluxeCabin) {
            return checkGroupType(number);
        } else {
            return "There is already the maximum number of people booked";
        }
    }


    private String checkRegularType(int number) {
        if (number <= 4 && number >= 1) {
            this.nCustomerCount = number;
            return "";
        } else {
            return "There is already the maximum number of people booked";
        }
    }

    private String checkGroupType(int number) {
        if (number <= 8 && number >= 1) {
            this.nCustomerCount = number;
            return "";
        } else {
            return "There is already the maximum number of people booked";
        }
    }

    public Lot getLot() {
        return this.obLot;
    }
    public int getID() {
        return this.obLot.getLotID();
    }


    /**
     * allows the person to be able to
     * set a price for the reservation.
     *
     * @param newPrice
     */
    private void setPrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * @param
     * @return
     */
    public boolean setSiteType(LotType NewLot) {
        Lot TempLot = new Lot(NewLot);
        Boolean check = BookingsLedger.checkOverlap(TempLot.getLotID(), this.obStartDate, this.obEndDate);
        if (!check) {
            this.obLot = TempLot;
            return true;
        } else {
            return false;
        }
    }


    public String changeDate(Date obStart, Date obEnd) {
        //if there is overlap between the sites
        if(CheckDateCases(obStart, obEnd))
        {
            if (!BookingsLedger.checkOverlap(this.obLot.getLotID(), obStart, obEnd)) {
                this.obStartDate = obStart;
                this.obEndDate = obEnd;
                return "";
            } else {
                return "These dates will not work for this reservation";
            }
        }
        else
        {
            return "These dates will not work for this reservation";
        }
    }


    /**
     * this will return true if it does not trigger any exceptions and false otherwise.
     * @param obStartDate
     * @param obEndDate
     * @return
     */
    public boolean CheckDateCases(Date obStartDate, Date obEndDate)
    {
        //checks to make sure the start date is not before the end date
        return !(obStartDate.compareTo(obEndDate) > 0);
    }

    public int getReservationID() {
        /*Set the ID for the specific reservation*/
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


    public String OthertoString() {
        return "Reservation: " +
                "ID: " + ReservationID +
                "\t " + obStartDate +
                " - " + obEndDate +
                " Number Of People " + nCustomerCount +
                " Lot Type: " + obLot.getLotType();
    }

}

