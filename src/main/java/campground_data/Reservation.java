package campground_data;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Reservation extends Persistent{


    /*Primitive Variables */
    int ReservationID, nCustomerCount, nLinkedLotID;
    double price;
    Lot obLot;

    public ArrayList <Customer> obCustomerList = new ArrayList<>();

    Date obStartDate;
    Date obEndDate;
    public Boolean status;


    public static int idCounter = 0;

    //a new transaction
    Transaction transaction;

    /*Reservation Constructor */
    public Reservation(Lot obLot, Date startDate, Date endDate, ArrayList<Customer> customers, int nPeople)
    {

        /*Set the passed in parameters*/
        this.obLot = obLot;
        this.obStartDate = startDate;
        this.obEndDate = endDate;
        this.obCustomerList = customers;
        this.nCustomerCount = nPeople;
        this.transaction = new Transaction(this);

        /*Set the ID for the specific reservation*/
        this.ReservationID = ++idCounter;
        this.price = 100.00;
        this.status = true;

    }

    public Reservation(){};

    /*Reservation Constructor */
    public Reservation(ArrayList<Customer> customers, int nPeople, Date startDate, Date endDate, Lot obLot)
    {
            this.obLot = obLot;
            this.obCustomerList = customers;
            this.nCustomerCount = nPeople;
            this.obStartDate = startDate;
            this.obEndDate = endDate;

            this.ReservationID = ++idCounter;
            this.transaction = new Transaction(this);
            double price = 100.0;
            this.status = true;
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
    public ArrayList<Customer> getCustomerList() {return this.obCustomerList; }
    public int getCustomerCount() { return this.nCustomerCount; }


    /**
     * allows the person to be able to
     * set a price for the reservation.
     *
     * @param newPrice
     */
    public void setPrice(double newPrice) {
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

    public ArrayList<Customer> getObCustomerList(){
        return this.obCustomerList;
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

    public Date getObStartDate() {
        return obStartDate;
    }

    public Date getObEndDate(){
        return obEndDate;
    }

    public int getnCustomerCount(){
        return nCustomerCount;
    }

    /**
     * this is used for testing cases, it will let me test the trandaction class
     * for a reservation.
     * @return
     */
    public Transaction getTransaction()
         {
             return this.transaction;
         }



    @Override
    public String toString() {
        return "Reservation: " +
                "ID: " + ReservationID +
                "\n\t " + obStartDate +
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


    /* ID, nCustCount, Price, LinkedLotID, StartDate(Y,M,D), EndDate(Y,M,D), status*/
    @Override
    public void load(BookingsLedger bl, Object... arg) {
        this.ReservationID = Integer.parseInt((String)arg[0]);
        this.nCustomerCount = Integer.parseInt((String)arg[1]);
        this.price = Double.parseDouble((String)arg[2]);

        Date obStartDate = new Date(Integer.parseInt((String)arg[3]),Integer.parseInt((String)arg[4]),Integer.parseInt((String)arg[5]));
        this.obStartDate = obStartDate;

        Date obEndDate = new Date(Integer.parseInt((String)arg[6]),Integer.parseInt((String)arg[7]),Integer.parseInt((String)arg[8]));
        this.obStartDate = obEndDate;

        status = Boolean.parseBoolean((String) arg[9]);
    }

    @Override
    public String savable() {

        String CustomerIDs = "";
        for (int i = 0; i < nCustomerCount; i ++)
        {
            CustomerIDs += "" + obCustomerList.get(0).getCustomerID();
            if(i < nCustomerCount -1)
                CustomerIDs +=",";
        }

        return String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d,%b",
                getReservationID(),nCustomerCount,nLinkedLotID,(obStartDate.getYear()+1900),obStartDate.getMonth(),
                obStartDate.getDay(),(obEndDate.getYear()+1900),obEndDate.getMonth(),obEndDate.getDay(),status);
    }

    /* Must link with an Arraylist of customers. */
    @Override
    public void link(BookingsLedger bl, Object... arg) {

        /* searches for Lots with the ID's in their save data */
        for (int i = 0; i < bl.getLotList().size(); i++)
            if (bl.getLotList().get(i).nLotID == this.nLinkedLotID)
                obLot = bl.getLotList().get(i);

        /* searches for customers with the ID's in their save data */
        ArrayList<Customer> aCustomers = new ArrayList<>();
        for(int i = 0; i < nCustomerCount; i++)
        {
            int searchForID = Integer.parseInt((String)arg[11+i]);
            for (int j = 0; j < bl.aCustomer.size(); j++)
            {
                if (bl.aCustomer.get(i).getCustomerID() == searchForID)
                    obCustomerList.add(bl.aCustomer.get(i));
            }
        }

        this.obCustomerList = aCustomers;
    }

}

