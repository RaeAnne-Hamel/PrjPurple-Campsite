package campground_data;

public class Customer extends Persistent{

    private final int MAX_LENGTH = 255;
    private int CustomerID;
    private String sName;
    private String sLast;
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

    public Customer(String sName, String sLast, String sAddress, String sProvince, String sCity,
                    String sPostal, String sCountry, String sEmail, long nFax, long nPhone,
                    long nSecPhone, int nVisits, boolean isFrequent)
    {
        this.CustomerID = idPool++;
        this.sName = sName;
        this.sLast = sLast;
        this.sAddress = sAddress;
        this.sProvince = sProvince;
        this.sCity = sCity;
        this.sPostal = sPostal;
        this.sCountry = sCountry;
        this.sEmail = sEmail;
        this.nFax = nFax;
        this.nPhone = nPhone;
        this.nSecPhone = nSecPhone;
        this.nVisits = nVisits;
        this.isFrequent = isFrequent;
    }

    public Customer(String sName, String sLast, String sAddress, String sProvince, String sCity,
                    String sPostal, String sCountry, String sEmail, long nPhone)
    {
        this.CustomerID = idPool++;
        this.sName = sName;
        this.sLast = sLast;
        this.sAddress = sAddress;
        this.sProvince = sProvince;
        this.sCity = sCity;
        this.sPostal = sPostal;
        this.sCountry = sCountry;
        this.sEmail = sEmail;
        this.nPhone = nPhone;
        this.isFrequent = false;

        this.nSecPhone = 1234567890;
        this.nVisits = 0;
        this.nFax = 1234567890;
    }

    public String Name;

    public Customer(String name)
    {
        this.Name = name;
    }

    public Customer()
    {
        this.CustomerID = idPool++;
        this.sName = "";
        this.sLast = "";
        this.sAddress = "";
        this.sProvince = "";
        this.sCity = "";
        this.sPostal = "";
        this.sCountry = "";
        this.sEmail = "";
        this.nPhone = 0;
        this.isFrequent = false;
        this.nSecPhone = 0;
        this.nVisits = 0;
        this.nFax = 0;
    }

    public int getCustomerID() { return CustomerID; }

    public String getName() { return sName; }

    public String getLast() { return sLast; }

    public String getAddress() { return sAddress; }

    public String getProvince() {return sProvince; }

    public String getCity() {return sCity; }

    public String getPostal() {return sPostal; }

    public String getCountry() {return sCountry; }

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

    public void setLast(String sName) {
        if (sName.length() > 0 && sName.length()<=MAX_LENGTH)
        {
            this.sLast = sName;
        }
    }

    public void setAddress(String sAddress) {
        if (sAddress.length() > 0 && sAddress.length()<=MAX_LENGTH)
        {
            this.sAddress = sAddress;
        }
    }


    public void setProvince(String sProvince) {
        if (sProvince.length() > 0 && sProvince.length()<=MAX_LENGTH)
        {
            this.sProvince = sProvince;
        }
    }

    public void setCity(String sCity) {
        if (sCity.length() > 0 && sCity.length()<=MAX_LENGTH)
        {
            this.sCity = sCity;
        }
    }

    public void setPostal(String sPostal) {
        if (sPostal.length() > 2 && sPostal.length()<=10)
        {
            this.sPostal = sPostal;
        }
    }

    public void setCountry(String sCountry) {
        if (sCountry.length() > 0 && sCountry.length()<=MAX_LENGTH)
        {
            this.sCountry = sCountry;
        }
    }

    public void setEmail(String sEmail) {
        if (sEmail.length() > 0 && sEmail.length()<=MAX_LENGTH)
        {
            this.sEmail = sEmail;
        }
    }

    public void setFax(long nPhone) {
        if (nPhone > 1000000000L && nPhone < 99999999999L)
        {
            this.nFax = nPhone;
        }
    }

    public void setPhone(long nPhone) {
        if (nPhone > 1000000000L && nPhone < 99999999999L)
        {
            this.nPhone = nPhone;
        }
    }

    public void setSecPhone(long nPhone) {

        if (nPhone > 1000000000L && nPhone < 99999999999L)
        {
            this.nSecPhone = nPhone;
        }
    }

    public void setVisits(int nVisits) { this.nVisits = nVisits; }

    public void setFrequent(boolean frequent) { isFrequent = frequent; }

    /**
     * Method for adding or updating a customer. nCheck determines whether it will be added or updated.
     * @param sName First name
     * @param sLast Last name
     * @param sAddress Street address
     * @param sProvince Province or state
     * @param sCity City
     * @param sPostal Postal code
     * @param sCountry Country
     * @param sEmail Email Address
     * @param nPhone Phone Number
     * @param nFax Fax Number
     * @param nSecPhone Secondary Phone Number
     * @param nCheck Determines whether to add up update a customer
     * @return Returns a string that shows whether there was an error or the update was successful.
     */

    //Validates the fields inputted and returns a string with what is entered incorrectly
    public String updateCustomer(String sName, String sLast, String sAddress, String sProvince,
                                 String sCity, String sPostal, String sCountry, String sEmail,
                                 long nPhone, long nFax, long nSecPhone, int nCheck) {
        if (!(sName.length() > 0 && sName.length() <= MAX_LENGTH)) {
            return "First name must be between 1 and 255 characters.";
        }
        if (!(sLast.length() > 0 && sLast.length() <= MAX_LENGTH)) {
            return "Last name must be between 1 and 255 characters.";
        }
        if (!(sAddress.length() > 0 && sAddress.length() <= MAX_LENGTH)){
            return "Street address must be between 1 and 255 characters.";
        }

        if (!(sProvince.length() > 0 && sProvince.length()<=MAX_LENGTH)){
            return "State/province must be between 1 and 255 characters.";
        }
        if (!(sCity.length() > 0 && sCity.length()<=MAX_LENGTH)){
            return "City must be between 1 and 255 characters.";
        }
        if (!(sPostal.length() > 2 && sPostal.length()<=10)){
            return "Postal code must be between 3 and 10 characters";
        }
        if (!(sCountry.length() > 0 && sCountry.length()<=MAX_LENGTH)){
            return "Country must be between 1 and 255 characters.";
        }
        if (!(sEmail.length() > 4 && sEmail.length()<=MAX_LENGTH && sEmail.contains("@"))){
            return "Email must be between 5 and 255 characters and include an @ symbol.";
        }

        if (!(nPhone > 1000000000L && nPhone < 99999999999L)){
            return "Phone number must be 10 or 11 digits.";
        }
        if (!(nSecPhone > 1000000000L && nSecPhone < 99999999999L) && nSecPhone != 0){
            return "Secondary phone number must be 10 or 11 digits.";
        }
        if (!(nFax > 1000000000L && nFax < 99999999999L) && nFax != 0){
            return "Fax number must be 10 or 11 digits.";
        }

        this.setName(sName);
        this.setLast(sLast);
        this.setAddress(sAddress);
        this.setProvince(sProvince);
        this.setCity(sCity);
        this.setPostal(sPostal);
        this.setCountry(sCountry);
        this.setEmail(sEmail);
        this.setPhone(nPhone);
        this.setFax(nFax);
        this.setSecPhone(nSecPhone);

        //If nCheck is 1, it returns that the customer is added. Otherwise, that the customer is updated.
        if (nCheck == 1) return "Successfully added";
        else return "Successfully updated";
    }

    /* ID, Address, Email, Fax, Phone, SecPhone, Visits, frequent */
    public void load(BookingsLedger bl, Object... arg) {
        this.setCustomerID(Integer.parseInt((String) arg[0]));
        this.setName((String)arg[1]);
        this.setLast((String)arg[2]);
        this.setAddress((String)arg[3]);
        this.setCity((String)arg[4]);
        this.setProvince((String)arg[5]);
        this.setCountry((String)arg[6]);
        this.setPostal((String)arg[7]);
        this.setEmail((String)arg[8]);
        this.setFax(Long.parseLong((String) arg[9]));
        this.setPhone(Long.parseLong((String) arg[10]));
        this.setSecPhone(Long.parseLong((String) arg[11]));
        this.setVisits(Integer.parseInt((String) arg[12]));
        this.setFrequent(Boolean.parseBoolean((String) arg[13]));
        idPool++;
    }

    /* ID, Address, Email, Fax, Phone, SecPhone, Visits, frequent */
    @Override
    public String savable() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d,%b",
                getCustomerID(),getName(),getLast(),getAddress(),getCity(),getProvince(),getCountry(),getPostal(),getEmail(),getFax(),getPhone(),getSecPhone(),getVisits(),getFrequent());
    }

    /* Empty until needed */
    @Override
    public void link(BookingsLedger bl, Object... arg) { }

}







