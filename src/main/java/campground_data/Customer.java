package campground_data;

public class Customer
{
    private int CustomerID;
    private String sName;
    private String sAddress;
    private String sEmail;
    private long nFax;
    private long nPhone;
    private long nSecPhone;
    private int nVisits;
    private boolean isFrequent;
    private static int IdPool = 1;

    public Customer(String sName, String sAddress, String sEmail, long nFax, long nPhone, long nSecPhone)
    {
        this.sName = sName;
        this.sAddress = sAddress;
        this.sEmail = sEmail;
        this.nFax = nFax;
        this.nPhone = nPhone;
        this.nSecPhone = nSecPhone;
        //The the customerID using the method
        setCustomerID();
    }

    private void setCustomerID()
    {
        CustomerID = IdPool;
        IdPool++;
    }
}