import campground_data.*;
import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

public class GuestTest {

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

    /***
     * Run before each @Test to create a valid car object for test cases
     * or any other settings/variables needed in multiple test cases
     */
    @Before
    public void setUpValidGuest()
    {
/*
        guest = new Guest();
        guest.setGuestID(0);
        guest.setFirstName("Bob");
*/

    }

    /***
     * INVALID: FirstName null
     */
    @Test
    public void testFirstNameIsEmpty() {
/*

        //set 1 field invalid - all other fields are populated with valid data by setUpValidGuest
        guest.setFirstName("");

        // use the helper function that will run 4 asserts
        //pass in the property/attribute name, the EXACT expected error message, and the set invalid value
        assertInvalid(guest, "firstName","First name can not be empty", "" );
*/

    }

    /***
     * INVALID: manufacturer 26 characters is too long
     */
    @Test
    public void testFirstNameIsTooLong() {
/*

        //create an invalid string with 26 characters -too long
        String invalid = repeatM(26);

        //set value to invalid string
        guest.setFirstName(invalid);

        //use the helper function that will run 4 asserts
        // pass in the property/attribute name, the EXACT expected error message, and the set invalid value
        assertInvalid(guest,"firstName","First name can have at most 25 characters", invalid );
*/

    }

    /***
     * VALID: manufacturer 25 characters upper bound
     */
    @Test
    public void testManufacturerUpperBound() {
/*

        //set value to valid 25 character string - upper bound
        guest.setFirstName(repeatM(25));

        //run validator on car object and count how many violations - should be 0 - no violations
        assertEquals( 0, validator.validate( guest ).size() );

*/
    }

    /***
     * VALID: manufacturer 5 characters within range
     */
    @Test
    public void testManufacturerValid() {
/*

        //set value to valid 25 character string
        guest.setFirstName(repeatM(5));

        //run validator on car object and count how many violations - should be 0 - no violations
        assertEquals( 0, validator.validate( guest ).size() );

*/
    }

    /***
     * Helper: quickly create a string of letter M's of any size for testing string length bounds
     * @param count - number of times the letter M is repeated in a string
     * @return string of the letter M repeated the specified number of times
     */
    private String repeatM(int count){
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
