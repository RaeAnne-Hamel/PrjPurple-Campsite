package campground_data;

public class Transaction extends Persistent{

    public PaymentType payType;
    public PaymentMethod payMethod;
    public int obResID;
    Reservation obRes;


    public Transaction(Reservation obRes, PaymentType type, PaymentMethod method)
    {
        this.payMethod = method;
        this.payType = type;
        this.obRes = obRes;
    }

    public Transaction(){};



    public PaymentMethod getPayMethod() {
        return payMethod;
    }

    public PaymentType getPayType() {
        return payType;
    }

    public void setPayMethod(PaymentMethod payMethod) {
        this.payMethod = payMethod;
    }


    public void setPayType(PaymentType payType) {
        this.payType = payType;
    }

    /**
     * wiil set the status to be false, so we know a reservation is paid
     */
    public void setStatus()
    {
        this.obRes.setStatus();
    }

    /**
     * This method will: a string that will tell you if the reservation needs payment or payment is complete
     * @return a string
     */
    public String isActive()
    {
        if(obRes.getStatus())
        {
            return "Needs payment.";
        }
        else
        {
            return "Payment complete.";
        }
    }

    /**
     * this method will call get price from the reservation that will display the total price
     * of the reservation that need to be collected
     * @return
     */
    public double getPrice()
    {
        return obRes.getPrice();
    }

     @Override
    public String toString()
     {
         return String.format("Reservation: %s, Payment type: %s, Payment method: %s Price: %.2f", this.obRes, this.payType, this.payMethod, this.getPrice());
     }

     /* PaymentType, PaymentMethod, ReservationID */
    @Override
    public void load(BookingsLedger bl, Object... arg) {
        setPayType(PaymentType.valueOf((String)arg[0]));
        setPayMethod(PaymentMethod.valueOf((String)arg[1]));
        obResID = Integer.parseInt((String)arg[2]);
    }

    @Override
    public String savable() {
        return String.format("%s,%s,%d",
                getPayType(),getPayMethod(),obResID);
    }


    /* Must link with a reservation */
    @Override
    public void link(BookingsLedger bl, Object... arg) {
        for (int i = 0; i < bl.getAllReservations().size(); i ++)
            if (bl.getAllReservations().get(i).getID() == obResID)
                obRes = bl.getAllReservations().get(i);
    }
}
