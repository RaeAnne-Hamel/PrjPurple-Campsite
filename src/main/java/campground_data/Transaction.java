package campground_data;

public class Transaction extends Savable{

    public PaymentType payType;
    public PaymentMethod payMethod;
    Reservation obRes;


    public Transaction(Reservation obRes, PaymentType type, PaymentMethod method)
    {
        this.payMethod = method;
        this.payType = type;
        this.obRes = obRes;
    }



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

    @Override
    public void save(String fName) {

    }
}
