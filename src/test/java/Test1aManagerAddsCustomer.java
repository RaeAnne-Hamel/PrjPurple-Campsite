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

    private Customer testCustomer = new Customer(sName, sAddress, sAddress, sAddress, sAddress, sAddress, sEmail, nFax, nPhone, nSecPhone, nVists, bFreq);

    private Customer obCustomer;
    @NotNull(message = "Can not be empty")

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
        testCustomer.setName("");
        assertNotEquals("", testCustomer.getName());
    }

    /**
     * INVALID: Name 256 characters long
     */
    @Test
    public void testNameTooLong()
    {
        String j256 = repeatJ(256);
        testCustomer.setName(j256);
        assertNotEquals(j256, testCustomer.getName());
    }

    /**
     * VALID: Name 255 characters long
     */
    @Test
    public void testNameBoundaryLong()
    {
        String j255 = repeatJ(255);
        testCustomer.setName(j255);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * VALID: Name is 5 characters
     */
    @Test
    public void testNameValid()
    {
        testCustomer.setName("Harry");
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Address null
     */
    @Test
    public void testAddressEmpty()
    {
        testCustomer.setAddress("");
        assertNotEquals("", testCustomer.getAddress());
    }

    /**
     * INVALID: Address 256 characters long
     */
    @Test
    public void testAddressTooLong()
    {
        String j256 = repeatJ(256);
        testCustomer.setAddress(j256);
        assertNotEquals(j256, testCustomer.getAddress());
    }

    /**
     * VALID: Address 255 characters long
     */
    @Test
    public void testAddressBoundaryLong()
    {
        String j255 = repeatJ(255);
        testCustomer.setAddress(j255);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * VALID: Address is 5 characters
     */
    @Test
    public void testAddressValid()
    {
        testCustomer.setAddress("Box 423");
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Email null
     */
    @Test
    public void testEmailEmpty()
    {
        testCustomer.setEmail("");
        assertNotEquals("", testCustomer.getEmail());
    }

    /**
     * INVALID: Email 256 characters long
     */
    @Test
    public void testEmailTooLong()
    {
        String j256 = repeatJ(256);
        testCustomer.setEmail(j256);
        assertNotEquals(j256, testCustomer.getEmail());
    }

    /**
     * VALID: Email 255 characters long
     */
    @Test
    public void testEmailBoundaryLong()
    {
        String j255 = repeatJ(255);
        testCustomer.setEmail(j255);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * VALID: Email is 5 characters
     */
    @Test
    public void testEmailValid()
    {
        testCustomer.setEmail("email@email.com");
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Fax is 12 characters long
     */
    @Test
    public void testFaxTooLong()
    {
        testCustomer.setFax(849520582618L);
        assertNotEquals(849520582618L, testCustomer.getFax());
    }

    /**
     * VALID: Fax is 11 characters long
     */
    @Test
    public void testFaxValid()
    {
        testCustomer.setFax(49520582618L);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Fax is 9 characters long
     */
    @Test
    public void testFaxTooShort()
    {
        testCustomer.setFax(628194614L);
        assertNotEquals(628194614L, testCustomer.getFax());
    }

    /**
     * INVALID: Phone is 12 characters long
     */
    @Test
    public void testPhoneTooLong()
    {
        testCustomer.setPhone(849520582618L);
        assertNotEquals(849520582618L, testCustomer.getPhone());
    }

    /**
     * VALID: Phone is 11 characters long
     */
    @Test
    public void testPhoneValid()
    {
        testCustomer.setPhone(49520582618L);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Phone is 9 characters long
     */
    @Test
    public void testPhoneTooShort()
    {
        testCustomer.setPhone(628194614L);
        assertNotEquals(628194614L, testCustomer.getPhone());
    }

    /**
     * VALID: Secondary Phone is 11 characters long
     */
    @Test
    public void testSecPhoneValid()
    {
        testCustomer.setSecPhone(49520582618L);
        assertEquals(0, validator.validate(testCustomer).size());
    }

    /**
     * INVALID: Secondary Phone is 12 characters long
     */
    @Test
    public void testSecPhoneTooLong()
    {
        testCustomer.setSecPhone(849520582618L);
        assertNotEquals(849520582618L, testCustomer.getSecPhone());
    }

    /**
     * INVALID: Secondary Phone is 9 characters long
     */
    @Test
    public void testSecPhoneTooShort()
    {
        testCustomer.setSecPhone(628194614L);
        assertNotEquals(628194614L, testCustomer.getSecPhone());
    }

    private String repeatJ(int count){
        return new String(new char[count]).replace('\0','M');
    }

    /***
     * Helper: performs the same 4 asserts for invalid field / violations
     *      1.assert only 1 violation (multiple violations may indicate poor choice of test values)
     *      2.assert the property with the violation is the one we expect
     *      3.assert that the error message we get is the expected error message
     *      4.assert the invalid value is what was set to ensure th setter dod not change the value somehow
     * @param expectedProperty - the string name of the property/attribute in the cst1.color.carfleet.pink.cars_data.Car class we are testing
     * @param expectedErrMsg - the EXACT expected error message
     * @param expectedValue - the EXACT invalid value (ensure the value was not changed by the class or setter)
     */
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
