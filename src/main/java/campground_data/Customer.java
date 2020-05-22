package campground_data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Customer {
    private static int CustomerID;
    private static String sName;
    private static String sAddress;

//    String sName;
//    String sAddress;
    private static String sEmail;
    private static long nFax;
    private static long nPhone;
    private static long nSecPhone;
    private static int nVisits;
    private static boolean isFrequent;
    private static int idPool;

    public Customer(int CustomerID, String sName, String sAddress, String sEmail, long nFax,
                    long nPhone, long nSecPhone, int nVisits, boolean isFrequent, int idPool)
    {
        Customer.CustomerID = CustomerID;
        Customer.sName = sName;
        Customer.sAddress = sAddress;
        Customer.sEmail = sEmail;
        Customer.nFax = nFax;
        Customer.nPhone = nPhone;
        Customer.nSecPhone = nSecPhone;
        Customer.nVisits = nVisits;
        Customer.isFrequent = isFrequent;
        Customer.idPool = idPool;
    }

    public Customer() { }

    @NotEmpty(message = "Name can not be empty")
    @Size(max = 25, message = "Name can not be too long")

    public static int getCustomerID() { return CustomerID; }

    public static String getName()
    {
        return sName;
    }

    public static String getAddress() { return sAddress; }

    public static String getEmail()
    {
        return sEmail;
    }

    public static long getFax()
    {
        return nFax;
    }

    public static long getPhone()
    {
        return nPhone;
    }

    public static long getSecPhone()
    {
        return nSecPhone;
    }

    public int getnVisits() { return nVisits; }

    public boolean isFrequent() { return isFrequent; }

    public int getIdPool() { return idPool; }

    public void setCustomerID(int customerID) { CustomerID = customerID; }

    public void setsName(String sName) { this.sName = sName; }

    public void setsAddress(String sAddress) { this.sAddress = sAddress; }

    public void setsEmail(String sEmail) { this.sEmail = sEmail; }

    public void setnFax(long nFax) { this.nFax = nFax; }

    public void setnPhone(long nPhone) { this.nPhone = nPhone; }

    public void setnSecPhone(long nSecPhone) { this.nSecPhone = nSecPhone; }

    public void setnVisits(int nVisits) { this.nVisits = nVisits; }

    public void setFrequent(boolean frequent) { isFrequent = frequent; }

    public void setIdPool(int idPool) { this.idPool = idPool; }
}

