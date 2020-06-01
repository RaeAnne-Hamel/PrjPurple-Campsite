package campground_data;

import java.util.ArrayList;

public class Customer extends Persistent{
    private int CustomerID;
    private String sName;
    private String sAddress;
    private String sEmail;
    private long nFax;
    private long nPhone;
    private long nSecPhone;
    private int nVisits;
    private boolean isFrequent;
    public static int idPool;

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
    public String Name;

    public Customer(String name)
    {
        this.Name = name;
    }

    public Customer() {}

    public int getCustomerID() { return CustomerID; }

    public String getName() { return sName; }

    public String getAddress() { return sAddress; }

    public String getEmail() { return sEmail; }

    public long getFax() { return nFax; }

    public long getPhone() { return nPhone; }

    public long getSecPhone() { return nSecPhone; }

    public int getVisits() { return nVisits; }

    public boolean getFrequent() { return isFrequent; }

    public int getIdPool() { return idPool; }

    public void setCustomerID(int customerID) { CustomerID = customerID; }

    public void setName(String sName) { this.sName = sName; }

    public void setAddress(String sAddress) { this.sAddress = sAddress; }

    public void setEmail(String sEmail) { this.sEmail = sEmail; }

    public void setFax(long nFax) { this.nFax = nFax; }

    public void setPhone(long nPhone) { this.nPhone = nPhone; }

    public void setSecPhone(long nSecPhone) { this.nSecPhone = nSecPhone; }

    public void setVisits(int nVisits) { this.nVisits = nVisits; }

    public void setFrequent(boolean frequent) { isFrequent = frequent; }

    public void setIdPool(int idPool) { this.idPool = idPool; }

    /* ID, Address, Email, Fax, Phone, SecPhone, Visits, frequent */
    public void load(BookingsLedger bl, Object... arg) {
        this.setCustomerID(Integer.parseInt((String) arg[0]));
        this.setAddress((String)arg[1]);
        this.setEmail((String)arg[2]);
        this.setFax(Long.parseLong((String) arg[3]));
        this.setPhone(Long.parseLong((String) arg[4]));
        this.setSecPhone(Long.parseLong((String) arg[5]));
        this.setVisits(Integer.parseInt((String) arg[6]));
        this.setFrequent(Boolean.parseBoolean((String) arg[7]));
        idPool++;
    }

    /* ID, Address, Email, Fax, Phone, SecPhone, Visits, frequent */
    @Override
    public String savable() {
        return String.format("%d,%s,%d,%d,%d,%d,%b",
                getCustomerID(),getAddress(),getFax(),getPhone(),getSecPhone(),getVisits(),getFrequent());
    }

    /* Empty until needed */
    @Override
    public void link(BookingsLedger bl, Object... arg) { }
}


