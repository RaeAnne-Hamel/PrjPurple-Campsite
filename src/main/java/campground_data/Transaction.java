package campground_data;

public class Transaction {

    public PaymentType payType;
    public PaymentMethod payMethod;
    //this is the discount for the price of that reservation.
    public double discount;
    Reservation obRes;


    public Transaction(Reservation obRes, PaymentType type, PaymentMethod method)
    {
        this.payMethod = method;
        this.payType = type;
        this.obRes = obRes;
    }

    public Transaction(Reservation res) {
        payType = PaymentType.DEBIT; //this will be a defult for transaction
        payMethod = PaymentMethod.INPERSON; // this will also be a defult type
        this.obRes = res; // this will be the reservation that has this spcific transaction.


    }

    public double setDiscount ( int discount)
    {
        if (discount == 100) {
            obRes.getPrice() = 0.00;
            return 0.0;
        } else if (discount < 100 && discount > 0) {
            double dCalPrice = obRes.getPrice() * (discount / 100);
            dCalPrice = round(dCalPrice, 2);
            obRes.price = dCalPrice;
            return dCalPrice;

        }
        //if the number is a bad number
        //we will not change the price of the object.
        return obRes.getPrice();
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
}
