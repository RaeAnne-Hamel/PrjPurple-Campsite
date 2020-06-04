package campground_data;

public class Transaction {

    public PaymentType payType;
    public PaymentMethod payMethod;
    //this is the discount for the price of that reservation.
    public double discount;
    public Reservation obRes;


    public Transaction(Reservation obRes, PaymentType type, PaymentMethod method)
    {
        this.payMethod = method;
        this.payType = type;
        this.obRes = obRes;
        this.discount = 0.0;
    }

    public Transaction(Reservation res) {
        payType = PaymentType.DEBIT; //this will be a defult for transaction
        payMethod = PaymentMethod.INPERSON; // this will also be a defult type
        this.obRes = res; // this will be the reservation that has this spcific transaction.
        this.discount = 0.0; //the defult will always be no discount.


    }

    public double setDiscount ( int discount)
    {
        //if they want the campground to be free
        if (discount == 100) {
            //set the price to be 0.
            obRes.setPrice(0.00);
            this.discount = discount;
            return 0.0; // return the new price
            //if the price is smaller than 100 and bigger than 0
        }else if((discount == 0)) //if the discount is 0, which will be the defult
        {
            //set the price to be 0.
            obRes.setPrice(obRes.getPrice());
            this.discount = discount;
            return obRes.getPrice(); // return the new price
        }
        else if (discount < 100 && discount > 0) {
            double dCalPrice =  (double) (discount / 100.0);
            this.discount = dCalPrice;
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

    public void setPrice(double Price)
    {
        obRes.setPrice(Price);
    }

     @Override
    public String toString()
     {
         return String.format("Reservation: %s, \n Payment type: %s, \n Payment method: %s \n Price: %.2f", this.obRes, this.payType, this.payMethod, this.getPrice());
     }
}
