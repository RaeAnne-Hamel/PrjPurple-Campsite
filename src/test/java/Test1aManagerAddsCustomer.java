import campground_data.Customer;
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
    private Customer customer;
    private static ValidatorFactory vf;
    private static Validator validator;

    //private Guest guest;


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

    @Before
    public void setUpCustomer()
    {
        customer = new Customer();
        customer.setCustomerID(5);
        customer.setsName("Harry");
        customer.setsAddress("Box 312");
        customer.setsEmail("email@email.com");
        customer.setnFax(47583945721L);
        customer.setnPhone(13067235921L);
        customer.setnSecPhone(13066913921L);
        customer.setnVisits(4);
        customer.setFrequent(true);
        customer.setIdPool(1);
    }

    /**
     * INVALID: Name null
     */
    @Test
    public void testNameEmpty()
    {
        customer.setsName("");
        assertInvalid(customer, "sName","Name can not be empty", "" );
    }

    /**
     * INVALID: Name 256 characters long
     */
    @Test
    public void testNameTooLong()
    {
        String j256 = repeatJ(256);
        customer.setsName(j256);
        assertInvalid(customer, "Name","Name can not be too long", j256 );
    }

    /**
     * VALID: Name 255 characters long
     */
    @Test
    public void testNameBoundaryLong()
    {
        String j255 = repeatJ(255);
        customer.setsName(j255);
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * VALID: Name is 5 characters
     */
    @Test
    public void testNameValid()
    {
        customer.setsName("Harry");
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * INVALID: Address null
     */
    @Test
    public void testAddressEmpty()
    {
        customer.setsAddress("");
        assertInvalid(customer, "Address", "Address can't be empty", "");
    }

    /**
     * INVALID: Address 256 characters long
     */
    @Test
    public void testAddressTooLong()
    {
        String j256 = repeatJ(256);
        customer.setsAddress(j256);
        assertInvalid(customer, "Address","Address can not be too long", j256 );
    }

    /**
     * VALID: Address 255 characters long
     */
    @Test
    public void testAddressBoundaryLong()
    {
        String j255 = repeatJ(255);
        customer.setsAddress(j255);
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * VALID: Address is 5 characters
     */
    @Test
    public void testAddressValid()
    {
        customer.setsAddress("Box 423");
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * INVALID: Email null
     */
    @Test
    public void testEmailEmpty()
    {
        customer.setsEmail("");
        assertInvalid(customer, "Email", "Email can't be empty", "");
    }

    /**
     * INVALID: Email 256 characters long
     */
    @Test
    public void testEmailTooLong()
    {
        String j256 = repeatJ(256);
        customer.setsEmail(j256);
        assertInvalid(customer, "Email","Email can not be too long", j256 );
    }

    /**
     * VALID: Email 255 characters long
     */
    @Test
    public void testEmailBoundaryLong()
    {
        String j255 = repeatJ(255);
        customer.setsEmail(j255);
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * VALID: Email is 5 characters
     */
    @Test
    public void testEmailValid()
    {
        customer.setsEmail("email@email.com");
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * INVALID: Fax null
     */
    @Test
    public void testFaxEmpty()
    {
        customer.setnFax(0);
        assertInvalid(customer, "Fax", "Fax can't be empty", "");
    }

    /**
     * INVALID: Fax is 12 characters long
     */
    @Test
    public void testFaxTooLong()
    {
        customer.setnFax(849520582618L);
        assertInvalid(customer, "Fax","Fax can not be too long", 849520582618L );
    }

    /**
     * VALID: Fax is 11 characters long
     */
    @Test
    public void testFaxValid()
    {
        customer.setnFax(49520582618L);
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * INVALID: Fax is 9 characters long
     */
    @Test
    public void testFaxTooShort()
    {
        customer.setnFax(628194614L);
        assertInvalid(customer, "Fax","Fax can not be too short", 849520582618L );
    }

    /**
     * INVALID: Phone null
     */
    @Test
    public void testPhoneEmpty()
    {
        customer.setnPhone(0);
        assertInvalid(customer, "Phone", "Phone can not be too empty", "");
    }

    /**
     * INVALID: Phone is 12 characters long
     */
    @Test
    public void testPhoneTooLong()
    {
        customer.setnPhone(849520582618L);
        assertInvalid(customer, "Phone","Phone can not be too long", 849520582618L );
    }

    /**
     * VALID: Phone is 11 characters long
     */
    @Test
    public void testPhoneValid()
    {
        customer.setnPhone(49520582618L);
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * INVALID: Phone is 9 characters long
     */
    @Test
    public void testPhoneTooShort()
    {
        customer.setnPhone(628194614L);
        assertInvalid(customer, "Phone","Phone can not be too short", 849520582618L );
    }

    /**
     * INVALID: Secondary Phone null
     */
    @Test
    public void testSecPhoneEmpty()
    {
        customer.setnSecPhone(0);
        assertInvalid(customer, "Secondary Phone", "Secondary Phone can not be too empty", "");
    }

    /**
     * VALID: Secondary Phone is 11 characters long
     */
    @Test
    public void testSecPhoneValid()
    {
        customer.setnSecPhone(49520582618L);
        assertEquals(0, validator.validate(customer).size());
    }

    /**
     * INVALID: Secondary Phone is 12 characters long
     */
    @Test
    public void testSecPhoneTooLong()
    {
        customer.setnSecPhone(849520582618L);
        assertInvalid(customer, "Secondary Phone","Secondary Phone can not be too long", 849520582618L );
    }

    /**
     * INVALID: Secondary Phone is 9 characters long
     */
    @Test
    public void testSecPhoneTooShort()
    {
        customer.setnSecPhone(628194614L);
        assertInvalid(customer, "Secondary Phone","Secondary Phone can not be too short", 849520582618L );
    }

    private String repeatJ(int count){
        return new String(new char[count]).replace('\0','M');
    }

    private void assertInvalid(Object obj, String expectedProperty, String expectedErrMsg, Object expectedValue){
        //run validator on car object and store the resulting violations in a collection
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate( obj );

        //count how many violations - SHOULD ONLY BE 1
        assertEquals( 1, constraintViolations.size() );

        //get first violation from constraintViolations collection
        ConstraintViolation<Object> violation = constraintViolations.iterator().next();

        //ensure that expected property has the violation
        assertEquals( expectedProperty, violation.getPropertyPath().toString() );

        //ensure error message matches what is expected
        assertEquals( expectedErrMsg, violation.getMessage() );

        //ensure the invalid value is what was set
        assertEquals( expectedValue, violation.getInvalidValue() );
    }
}
