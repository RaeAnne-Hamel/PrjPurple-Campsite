package campground_data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Customer {
    private int CustomerID;
    private String sName;
    private String sAddress;

//    String sName;
//    String sAddress;
    private String sEmail;
    private long nFax;
    private long nPhone;
    private long nSecPhone;
    private int nVisits;
    private boolean isFrequent;
    private int idPool;

    public Customer(int CustomerID, String sName, String sAddress, String sEmail, long nFax,
                    long nPhone, long nSecPhone, int nVisits, boolean isFrequent, int idPool)
    {
        this.CustomerID = CustomerID;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sEmail = sEmail;
        this.nFax = nFax;
        this.nPhone = nPhone;
        this.nSecPhone = nSecPhone;
        this.nVisits = nVisits;
        this.isFrequent = isFrequent;
        this.idPool = idPool;
    }

    public Customer() { }

//    @NotEmpty(message = "Name can not be empty")
//    @Size(max = 25, message = "Name can not be too long")

    public int getCustomerID() { return CustomerID; }

    public String getName()
    {
        return sName;
    }

    public String getAddress() { return sAddress; }

    public String getEmail()
    {
        return sEmail;
    }

    public long getFax()
    {
        return nFax;
    }

    public long getPhone()
    {
        return nPhone;
    }

    public long getSecPhone()
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

