import jdk.jfr.StackTrace;

import org.junit.*;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class TestReservation {
    //this will be a basic reservation
    private Reservation testRegularReservation = new Reservation(null, 3,obStart, obEnd, regualr);
    //this will be a group reservation
    private Reservation testGroupReservation = new Reservation(null, 6,obStart, obEnd, group);

    //these will be the start and end dates
    private Date obStart = new Date(2020, 06, 11);
    private Date obEnd = new Date(2020, 06, 15);

    //this is the start of the test's for the Reservations Class
    @Test
    public void tCustomerNumberToLarge()
    {
        //these are test done by the regualr vabins
        String test1 = testRegularReservation.setCustomerNumber(1);
        String test2 = testRegularReservation.setCustomerNumber(4);
        //boundy cases
        String test3 = testRegularReservation.setCustomerNumber(0);
        String test4 = testRegularReservation.setCustomerNumber(5);
        //special cases
        String testRSpecial = testRegularReservation.setCustomerNumber(@34);


        //these tests are for a group cabins
        String test5 = testGroupReservation.setCustomerNumber(1);
        String test6 = testGroupReservation.setCustomerNumber(8);
        //boundy cases
        String test7 = testGroupReservation.setCustomerNumber(0);
        String test8 = testGroupReservation.setCustomerNumber(9);
        String testGSpecial = testGroupReservation.setCustomerNumber(@34);

        assertEquals(test1, "");
        assertEquals(test2, "");
        assertEquals(test3, "There is already the maximum number of people booked");
        assertEquals(test4, "There is already the maximum number of people booked");
        assertEquals(testRSpecial, "There is already the maximum number of people booked");

        //tests for the group campsistes
        assertEquals(test5, "");
        assertEquals(test6, "");
        assertEquals(test7, "There is already the maximum number of people booked");
        assertEquals(test8, "There is already the maximum number of people booked");
        assertEquals(testGSpecial, "There is already the maximum number of people booked");






    }


}
