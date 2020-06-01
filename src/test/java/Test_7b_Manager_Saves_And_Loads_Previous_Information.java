
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

    @Before
    public void setup()
    {

    }

    /* Checks if a desired file exists PASSED (Assuming Customer file present in src/files/Customer.txt */
    @Test
    public void tCheckIfFileExists()
    {
        File file = PersistentDataManager.isFilePresent(fName);
        assertNotEquals(null, file);
    }

    /* Checks a disired file was sussesfully loaded (Customer) */
    @Test
    public void tLoadFile()
    {
        ArrayList<Customer> aCustomers = new ArrayList<>();
        assertEquals(0, aCustomers.size());

        aCustomers = PersistentDataManager.load(fName, LoadType.Customer);

        /* Check if 2 customers have been loaded */
        assertEquals(2, aCustomers.size());
        System.out.println(aCustomers.get(0));
        System.out.println(aCustomers.get(1));

        PersistentDataManager.save(aCustomers,"src/files/save1.txt");
    }

    /* Check if a desired group of files are not loaded */
    @Test
    public void tSystemNotLoaded()
    {


    }

    /* Check if a desired group of files are saved */
    @Test
    public void tSystemSaved()
    {


    }

}

