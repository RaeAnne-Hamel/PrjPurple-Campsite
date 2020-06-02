
import campground_data.*;
import org.junit.Before;
import org.junit.Test;
import sun.security.krb5.internal.crypto.EType;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class Test_7b_Manager_Saves_And_Loads_Previous_Information {

    private String fName = "src/files/Customer.txt";

    /* Create Booking Ledger */
    BookingsLedger bl = new BookingsLedger();


    /* Sample Customers */
    Customer cust0 = new Customer(0, "John", "130 Hello Streen", "Email@Email.com", 1111111111,
            1222222222, 1333333333, 1, false,0);

    Customer cust1 = new Customer(1, "KANE", "140 Hello Streen", "Email@Email.com", 1111111111,
            1222222222, 1333333333, 1, false,0);

    /* Sample Manager*/

    Manager manager0 = new Manager();



    @Before
    public void setup()
    {
        /* Customer */
        bl.addCustomer(cust0);
        bl.addCustomer(cust1);

        /* Manager */
        manager0.setID(0);
        manager0.setPermissions(1);
        bl.addManager(manager0);


    }

    /* Checks if a desired file exists PASSED (Assuming Customer file present in src/files/Customer.txt */
    @Test
    public void tCheckIfFileExists()
    {
        File file = PersistentDataManager.isFilePresent(fName);
        assertNotEquals(null, file);
    }

    /* Save files that are to be loaded */
    @Test
    public void tSystemSaved()
    {
        PersistentDataManager.save(bl.ma);


    }

    /* Checks a disired file was sussesfully loaded (Customer) */
    @Test
    public void tLoadFile()
    {
        BookingsLedger bl = new BookingsLedger();

        ArrayList<Object> aCustomers = new ArrayList<>();
        assertEquals(0, aCustomers.size());

        bl.setCustomerList(PersistentDataManager.load(fName, LoadType.Customer));
        bl.setManagerList(PersistentDataManager.load(fName, LoadType.Customer));
        bl.setLotList(PersistentDataManager.load(fName, LoadType.Customer));
        bl.setReservationsList(PersistentDataManager.load(fName, LoadType.Customer));
        bl.setReservationsList(PersistentDataManager.load(fName, LoadType.Customer));



        /* Check if 2 customers have been loaded */
        assertEquals(2, aCustomers.size());
        System.out.println(aCustomers.get(0));
        System.out.println(aCustomers.get(1));

        PersistentDataManager.save(aCustomers,"src/files/save1.txt");
    }

    /* Check if a desired group of files are not loaded */
//    @Test
//    public void tSystemNotLoaded()
//    {
//        File fCustomers = PersistentDataManager.isFilePresent("src/files/Customers.txt");
//        File fReservation = PersistentDataManager.isFilePresent("src/files/Customers.txt");
//        File fLot = PersistentDataManager.isFilePresent("src/files/Customers.txt");
//        File fTransaction = PersistentDataManager.isFilePresent("src/files/Customers.txt");
//        File fManager = PersistentDataManager.isFilePresent("src/files/Customers.txt");
//
//        assertEquals(null, fCustomers);
//        assertEquals(null, fReservation);
//        assertEquals(null, fLot);
//        assertEquals(null, fTransaction);
//        assertEquals(null, fManager);
//    }

    /* Check if a desired group of files are saved */


}

