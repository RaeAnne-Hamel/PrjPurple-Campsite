import campground_data.Customer;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Dustin Wiebe
 */
public class EditCustomer1eTest {
    int nCustID = 5;
    String sName = "Harry";
    String sAddress = "Box 312";
    String sEmail = "email@email.com";
    String sProvince = "Saskatchewan";
    String sPostal = "S7N4V2";
    String j256 = repeatJ(256);
    String j255 = repeatJ(255);
    String j1 = repeatJ(1);
    long nFax = 47583945721L;
    long nPhone = 13067235921L;
    long nSecPhone = 13066913921L;
    int nVists = 4;
    boolean bFreq = true;
    int nIDPool = 2;

    private Customer testCustomer = new Customer(nCustID, sName, sAddress, sEmail, nFax, nPhone, nSecPhone, nVists, bFreq, nIDPool);


    /**
     * INVALID: Province length must be no more than 255 characters and must be at least 1 character.
     */
    @Test
    public void TestProvinceLength()
    {
        testCustomer.setProvince("");
        assertEquals("Saskatchewan", testCustomer.getProvince());
        testCustomer.setProvince(j256);
        assertEquals("Saskatchewan", testCustomer.getProvince());
    }

    /**
     * VALID: Province can be 255 characters.
     */
    @Test
    public void TestValidProvince()
    {
        testCustomer.setProvince(j255);
        assertEquals(j255, testCustomer.getProvince());
        testCustomer.setProvince(j1);
        assertEquals(j1, testCustomer.getProvince());
    }

    /**
     * INVALID: Name length must be no more than 255 characters and must be at least 1 character.
     */
    @Test
    public void TestNameLength()
    {
        testCustomer.setName("");
        assertEquals("Harry", testCustomer.getName());
        testCustomer.setName(j256);
        assertEquals("Harry", testCustomer.getName());
    }

    /**
     * VALID: Name can be 255 characters.
     */
    @Test
    public void TestValidName()
    {
        testCustomer.setName(j255);
        assertEquals(j255, testCustomer.getName());
        testCustomer.setName(j1);
        assertEquals(j1, testCustomer.getName());
    }


    /**
     * INVALID: Postal code length must be no more than 10 characters and must be at least 3 characters.
     */
    @Test
    public void TestPostalLength()
    {
        testCustomer.setPostal(repeatJ(2));
        assertEquals("S7N4V2", testCustomer.getPostal());
        testCustomer.setPostal(repeatJ(11));
        assertEquals("S7N4V2", testCustomer.getPostal());
    }

    /**
     * VALID: Postal code can be 3-10 characters.
     */
    @Test
    public void TestValidPostal()
    {
        String postal3 = repeatJ(3);
        testCustomer.setPostal(postal3);
        assertEquals(postal3, testCustomer.getPostal());
        String postal10 = repeatJ(10);
        testCustomer.setPostal(postal10);
        assertEquals(postal10, testCustomer.getPostal());
    }








    private String repeatJ(int count){
        return new String(new char[count]).replace('\0','M');
    }
}
