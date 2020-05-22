import campground_data.BookingsLedger;
import campground_data.Customer;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class Test1aManagerAddsCustomer
{
    private static ValidatorFactory vf;
    private static Validator validator;

    int nCustID = 5;
    String sName = "Harry";
    String sAddress = "Box 312";
    String sEmail = "email@email.com";
    long nFax = 47583945721L;
    long nPhone = 13067235921L;
    long nSecPhone = 13066913921L;
    int nVists = 4;
    boolean bFreq = true;
    int nIDPool = 2;

    private Customer testCustomer = new Customer(nCustID, sName, sAddress, sEmail, nFax, nPhone, nSecPhone, nVists, bFreq, nIDPool);

    /***
     * Run once at class creation to set up validator
     * or any other static objects
     */
    @BeforeClass
    public static void setUpValidator() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    /***
     * Run once at class destruction to tear down validator
     * or any other static objects
     */
    @AfterClass
    public static void tearDownValidator()
    {
        //gracefully teardown the validator factory
        vf.close();
    }

    /**
     * INVALID: Name null
     */
    @Test
    public void testNameEmpty()
    {
        testCustomer.setsName("");
        assertEquals("", testCustomer.getName());
    }

    /**
     * INVALID: Name 256 characters long
     */
    @Test
    public void testNameTooLong()
    {
        String j256 = repeatJ(256);
        testCustomer.setsName(j256);
        assertEquals(j256, testCustomer.getName());
    }

    /**
     * VALID: Name 255 characters long
     */
    @Test
    public void testNameBoundaryLong()
    {
        String j255 = repeatJ(255);
        testCustomer.setsName(j255);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * VALID: Name is 5 characters
     */
    @Test
    public void testNameValid()
    {
        testCustomer.setsName("Harry");
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Address null
     */
    @Test
    public void testAddressEmpty()
    {
        testCustomer.setsAddress("");
        assertEquals("", testCustomer.getAddress());
    }

    /**
     * INVALID: Address 256 characters long
     */
    @Test
    public void testAddressTooLong()
    {
        String j256 = repeatJ(256);
        testCustomer.setsAddress(j256);
        assertEquals(j256, testCustomer.getAddress());
    }

    /**
     * VALID: Address 255 characters long
     */
    @Test
    public void testAddressBoundaryLong()
    {
        String j255 = repeatJ(255);
        testCustomer.setsAddress(j255);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * VALID: Address is 5 characters
     */
    @Test
    public void testAddressValid()
    {
        testCustomer.setsAddress("Box 423");
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Email null
     */
    @Test
    public void testEmailEmpty()
    {
        testCustomer.setsEmail("");
        assertEquals("", testCustomer.getEmail());
    }

    /**
     * INVALID: Email 256 characters long
     */
    @Test
    public void testEmailTooLong()
    {
        String j256 = repeatJ(256);
        testCustomer.setsEmail(j256);
        assertEquals(j256, testCustomer.getEmail());
    }

    /**
     * VALID: Email 255 characters long
     */
    @Test
    public void testEmailBoundaryLong()
    {
        String j255 = repeatJ(255);
        testCustomer.setsEmail(j255);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * VALID: Email is 5 characters
     */
    @Test
    public void testEmailValid()
    {
        testCustomer.setsEmail("email@email.com");
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Fax is 12 characters long
     */
    @Test
    public void testFaxTooLong()
    {
        testCustomer.setnFax(849520582618L);
        assertEquals(849520582618L, testCustomer.getFax());
    }

    /**
     * VALID: Fax is 11 characters long
     */
    @Test
    public void testFaxValid()
    {
        testCustomer.setnFax(49520582618L);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Fax is 9 characters long
     */
    @Test
    public void testFaxTooShort()
    {
        testCustomer.setnFax(628194614L);
        assertEquals(628194614L, testCustomer.getFax());
    }

    /**
     * INVALID: Phone is 12 characters long
     */
    @Test
    public void testPhoneTooLong()
    {
        testCustomer.setnPhone(849520582618L);
        assertEquals(849520582618L, testCustomer.getPhone());
    }

    /**
     * VALID: Phone is 11 characters long
     */
    @Test
    public void testPhoneValid()
    {
        testCustomer.setnPhone(49520582618L);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Phone is 9 characters long
     */
    @Test
    public void testPhoneTooShort()
    {
        testCustomer.setnPhone(628194614L);
        assertEquals(628194614L, testCustomer.getPhone());
    }

    /**
     * VALID: Secondary Phone is 11 characters long
     */
    @Test
    public void testSecPhoneValid()
    {
        testCustomer.setnSecPhone(49520582618L);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Secondary Phone is 12 characters long
     */
    @Test
    public void testSecPhoneTooLong()
    {
        testCustomer.setnSecPhone(849520582618L);
        assertEquals(849520582618L, testCustomer.getSecPhone());
    }

    /**
     * INVALID: Secondary Phone is 9 characters long
     */
    @Test
    public void testSecPhoneTooShort()
    {
        testCustomer.setnSecPhone(628194614L);
        assertEquals(628194614L, testCustomer.getSecPhone());
    }

    private String repeatJ(int count){
        return new String(new char[count]).replace('\0','M');
    }
}
