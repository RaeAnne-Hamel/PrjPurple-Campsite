import campground_data.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static org.junit.Assert.*;

/*
    Dylan Attwater
    CST101
 */

public class ManageCustomer6bTest
{
    private static ValidatorFactory vf;
    private static Validator validator;

    String sName = "Dylan";
    String sLast = "Attwater";
    String sAddress = "Box 312";
    String sEmail = "email@email.com";
    String sProvince = "Saskatchewan";
    String sPostal = "S0K1E0";
    String sCountry = "Canada";
    String sCity = "Saskatoon";
    String j256 = repeatJ(256);
    String j255 = repeatJ(255);
    String j1 = repeatJ(1);
    String j2 = repeatJ(2);
    long nPhone = 3067235921L;

    long nFax = 4758394571L;
    int nVisits = 4;
    boolean bFreq = true;
    int nIDPool = 2;

    private Customer testCustomer = new Customer(sName, sLast, sAddress, sProvince, sCity, sPostal, sCountry, sEmail,
            nFax, nPhone, nPhone, nVisits, bFreq);

    /**
     * INVALID: Province length must be no more than 255 characters and must be at least 1 character.
     */
    @Test
    public void TestProvinceLength()
    {
        testCustomer.setProvince("");
        assertEquals(sProvince, testCustomer.getProvince());
        testCustomer.setProvince(j256);
        assertEquals(sProvince, testCustomer.getProvince());
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
        assertEquals(sName, testCustomer.getName());
        testCustomer.setName(j256);
        assertEquals(sName, testCustomer.getName());
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

    /**
     * INVALID: Address length must be no more than 255 characters and must be at least 1 character.
     */
    @Test
    public void TestAddressLength()
    {
        testCustomer.setAddress("");
        assertEquals(sAddress, testCustomer.getAddress());
        testCustomer.setName(j256);
        assertEquals(sAddress, testCustomer.getAddress());
    }

    /**
     * VALID: Address can be 255 characters.
     */
    @Test
    public void TestValidAddress()
    {
        testCustomer.setAddress(j255);
        assertEquals(j255, testCustomer.getAddress());
        testCustomer.setAddress(j1);
        assertEquals(j1, testCustomer.getAddress());
    }

    /**
     * INVALID: Country length must be no more than 255 characters and must be at least 1 character.
     */
    @Test
    public void TestCountryLength()
    {
        testCustomer.setCountry("");
        assertEquals(sCountry, testCustomer.getCountry());
        testCustomer.setCountry(j256);
        assertEquals(sCountry, testCustomer.getCountry());
    }

    /**
     * VALID: Country can be 255 characters.
     */
    @Test
    public void TestValidCountry()
    {
        testCustomer.setCountry(j255);
        assertEquals(j255, testCustomer.getCountry());
        testCustomer.setCountry(j1);
        assertEquals(j1, testCustomer.getCountry());
    }

    /**
     * INVALID: City length must be no more than 255 characters and must be at least 1 character.
     */
    @Test
    public void TestCityLength()
    {
        testCustomer.setCity("");
        assertEquals(sCity, testCustomer.getCity());
        testCustomer.setCity(j256);
        assertEquals(sCity, testCustomer.getCity());
    }

    /**
     * VALID: City can be 255 characters.
     */
    @Test
    public void TestValidCity()
    {
        testCustomer.setCity(j255);
        assertEquals(j255, testCustomer.getCity());
        testCustomer.setCity(j1);
        assertEquals(j1, testCustomer.getCity());
    }

    /**
     * INVALID: Email length must be no more than 255 characters and must be at least 1 character.
     */
    @Test
    public void TestEmailLength()
    {
        testCustomer.setEmail("");
        assertEquals(sEmail, testCustomer.getEmail());
        testCustomer.setEmail(j256);
        assertEquals(sEmail, testCustomer.getEmail());
    }

    /**
     * VALID: Email can be 255 characters.
     */
    @Test
    public void TestValidEmail()
    {
        testCustomer.setEmail(j255);
        assertEquals(j255, testCustomer.getEmail());
        testCustomer.setEmail(j1);
        assertEquals(j1, testCustomer.getEmail());
    }

    /**
     * INVALID: Phone length must be 10 characters.
     */
    @Test
    public void TestPhoneLength()
    {
        testCustomer.setPhone(0);
        assertEquals(nPhone, testCustomer.getPhone());
        testCustomer.setPhone(123456789012L);
        assertEquals(nPhone, testCustomer.getPhone());
    }

    /**
     * VALID: Phone can be 10 characters.
     */
    @Test
    public void TestValidPhone()
    {
        testCustomer.setPhone(nFax);
        assertEquals(nFax, testCustomer.getPhone());
    }

    /**
     * INVALID: Phone length must be 10 characters.
     */
    @Test
    public void TestSecPhoneLength()
    {
        testCustomer.setSecPhone(0);
        assertEquals(nPhone, testCustomer.getSecPhone());
        testCustomer.setSecPhone(123456789012L);
        assertEquals(nPhone, testCustomer.getSecPhone());
    }

    /**
     * VALID: Phone can be 10 characters.
     */
    @Test
    public void TestValidSecPhone()
    {
        testCustomer.setSecPhone(nFax);
        assertEquals(nFax, testCustomer.getSecPhone());
    }

    /**
     * INVALID: Fax length must be 10 characters.
     */
    @Test
    public void TestFaxLength()
    {
        testCustomer.setFax(0);
        assertEquals(nFax, testCustomer.getFax());
        testCustomer.setFax(123456789012L);
        assertEquals(nFax, testCustomer.getFax());
    }

    /**
     * VALID: Fax can be 10 characters.
     */
    @Test
    public void TestValidFax()
    {
        testCustomer.setFax(nPhone);
        assertEquals(nPhone, testCustomer.getFax());
    }

    private String repeatJ(int count){
        return new String(new char[count]).replace('\0','J');
    }
}
