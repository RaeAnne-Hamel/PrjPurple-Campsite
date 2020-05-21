package campground_data;

public class Customer {
    int CustomerID;
    String sName;
    String sAddress;
    String sEmail;
    long nFax;
    long nPhone;
    long nSecPhone;
    int nVisits;
    boolean isFrequent;
    int idPool;

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

    public Customer()
    {

    }

    public String getName()
    {
        return sName;
    }

    public String getAddress()
    {
        return sAddress;
    }

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


}

