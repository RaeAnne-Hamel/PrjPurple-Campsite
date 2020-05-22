public class Transaction {

    public PaymentType payType;
    public PaymentMethod payMethod;


    public Transaction(PaymentType type, PaymentMethod method)
    {
        this.payMethod = method;
        this.payType = type;
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




}
