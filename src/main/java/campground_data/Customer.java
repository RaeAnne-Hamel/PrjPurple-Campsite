package campground_data;

public class Customer {
    private final int MAX_LENGTH = 255;
    private int CustomerID;
    private String sName;
    private String sAddress;
    private String sProvince;
    private String sCity;
    private String sPostal;
    private String sCountry;
    private String sEmail;
    private long nFax;
    private long nPhone;
    private long nSecPhone;
    private int nVisits;
    private boolean isFrequent;
    private static int idPool = 0;

    public Customer(String sName, String sAddress, String sCity, String sProvince,
                    String sCountry, String sPostal, String sEmail, long nFax, long nPhone,
                    long nSecPhone, int nVisits, boolean isFrequent)
    {
        this.CustomerID = idPool++;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sCity = sCity;
        this.sProvince = sProvince;
        this.sCountry = sCountry;
        this.sPostal = sPostal;
        this.sEmail = sEmail;
        this.nFax = nFax;
        this.nPhone = nPhone;
        this.nSecPhone = nSecPhone;
        this.nVisits = nVisits;
        this.isFrequent = isFrequent;
    }

    public Customer(String sName, String sAddress, String sCity, String sProvince,
                    String sCountry, String sPostal, String sEmail, long nPhone)
    {
        this.CustomerID = idPool++;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sProvince = sProvince;
        this.sCity = sCity;
        this.sPostal = sPostal;
        this.sCountry = sCountry;
        this.sEmail = sEmail;
        this.nPhone = nPhone;
        this.isFrequent = false;
        this.nSecPhone = 0;
        this.nVisits = 0;
        this.nFax = 0;
    }

    public String Name;

    public Customer(String name)
    {
        this.Name = name;
    }

    public int getCustomerID() { return CustomerID; }

    public String getName() { return sName; }

    public String getAddress() { return sAddress; }

    public String getCity() {return sCity; }

    public String getProvince() {return sProvince; }

    public String getCountry() {return sCountry; }

    public String getPostal() {return sPostal; }

    public String getEmail() { return sEmail; }

    public long getFax() { return nFax; }

    public long getPhone() { return nPhone; }

    public long getSecPhone() { return nSecPhone; }

    public int getVisits() { return nVisits; }

    public boolean getFrequent() { return isFrequent; }

    public int getIdPool() { return idPool; }

    public void setCustomerID(int customerID) { CustomerID = customerID; }

    public void setName(String sName) {
        if (sName.length() > 0 && sName.length()<=MAX_LENGTH)
        {
            this.sName = sName;
        }
    }

    public void setAddress(String sAddress) {
        if (sAddress.length() > 0 && sAddress.length()<=MAX_LENGTH)
        {
            this.sAddress = sAddress;
        }
    }

    public void setCity(String sCity) {
        if (sCity.length() > 0 && sCity.length()<=MAX_LENGTH)
        {
            this.sCity = sCity;
        }
    }

    public void setProvince(String sProvince) {
        if (sProvince.length() > 0 && sProvince.length()<=MAX_LENGTH)
        {
            this.sProvince = sProvince;
        }
    }

    public void setCountry(String sCountry) {
        if (sCountry.length() > 0 && sCountry.length()<=MAX_LENGTH)
        {
            this.sCountry = sCountry;
        }
    }

    public void setPostal(String sPostal) {
        if (sPostal.length() > 2 && sPostal.length()<=10)
        {
            this.sPostal = sPostal;
        }
    }

    public void setEmail(String sEmail) {
        if (sEmail.length() > 0 && sEmail.length()<=MAX_LENGTH)
        {
            this.sEmail = sEmail;
        }
    }

    public void setFax(long nPhone) {
        if (nPhone > 1000000000L && nPhone < 9999999999L)
            this.nFax = nPhone;
    }

    public void setPhone(long nPhone) {
        if (nPhone > 1000000000L && nPhone < 9999999999L)
            this.nPhone = nPhone;
    }

    public void setSecPhone(long nPhone) {
        if (nPhone > 1000000000L && nPhone < 9999999999L)
            this.nSecPhone = nPhone;
    }

    public void setVisits(int nVisits) { this.nVisits = nVisits; }

    public void setFrequent(boolean frequent) { isFrequent = frequent; }


    public String updateCustomer(Customer obCust, String sName, String sAddress, String sCity,
                                 String sProvince, String sCountry, String sPostal, String sEmail,
                                 long nPhone, long nFax, long nSecPhone){

        String sVal = "Successfully changed";


        return sVal;
    }
}


