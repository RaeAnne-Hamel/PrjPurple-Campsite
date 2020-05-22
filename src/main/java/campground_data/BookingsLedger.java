package campground_data;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class BookingsLedger {


    ArrayList<Customer> aCustomer;

    Boolean bCustomerPasses = true;

    public void addCustomer()
    {
        Customer obAddCustomer = new Customer();

        String sName = obAddCustomer.getName();
        String sAddress = obAddCustomer.getAddress();
        String sEmail = obAddCustomer.getEmail();
        long nFax = obAddCustomer.getFax();
        long nPhone = obAddCustomer.getPhone();
        long nSecPhone = obAddCustomer.getSecPhone();

        containsLetters(sName);
        tooLongString(sName, 256);
        tooShortString(sName, 1);

        tooLongString(sAddress, 256);
        tooShortString(sAddress, 1);

        tooLongString(sEmail, 256);
        tooShortString(sEmail, 5);

        tooBigLong(nFax);
        tooSmallLong(nFax);

        tooBigLong(nPhone);
        tooSmallLong(nPhone);

        tooBigLong(nSecPhone);
        tooSmallLong(nSecPhone);

        if (bCustomerPasses) aCustomer.add(obAddCustomer);
    }

    public void containsLetters(String word)
    {
        word.toLowerCase();
        char[] arCh = word.toCharArray();
        for (char ch : arCh) {
            if (!(ch >= 'a' && ch <= 'z')) bCustomerPasses = false;
        }
    }

    public void tooLongString(String word, int length)
    {
        if (word.length() >= length) bCustomerPasses = false;
    }

    public void tooShortString(String word, int length)
    {
        if (word.length() <= length) bCustomerPasses = false;
    }

    public void tooBigLong(long number)
    {
        if (number > 99999999999L) bCustomerPasses = false;
    }

    public void tooSmallLong(long number)
    {
        if (number < 1000000000) bCustomerPasses = false;
    }
}
