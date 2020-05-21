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



        //these tests are for a group cabins
        String test5 = testGroupReservation.setCustomerNumber(1);
        String test6 = testGroupReservation.setCustomerNumber(8);
        //boundy cases
        String test7 = testGroupReservation.setCustomerNumber(0);
        String test8 = testGroupReservation.setCustomerNumber(9);


        assertEquals(test1, "");
        assertEquals(test2, "");
        assertEquals(test3, "There is already the maximum number of people booked");
        assertEquals(test4, "There is already the maximum number of people booked");



        //tests for the group campsistes
        assertEquals(test5, "");
        assertEquals(test6, "");
        assertEquals(test7, "There is already the maximum number of people booked");
        assertEquals(test8, "There is already the maximum number of people booked");
    }

    //this is the start of the test's for the Reservations Class
    //these tests will check out the tests to change the site types
    @Test
    public void tCustomerStayType()
    {
        //these are test done by the regualr vabins
        //from regular to regualr
        String test1 = testRegularReservation.setSiteType("Regular Site");
        //from regular to group
        String test2 = testRegularReservation.setSiteType("Group Site");
        //already fully booked regular site
        String test3 = testRegularReservation.setSiteType("Group Site");


        //these tests are for a group cabins
        //from group to group
        String test4 = testGroupReservation.setSiteType("Group Site");
        //from group to regular
        String test5 = testGroupReservation.setSiteType("Regular Site");
        //fully booked group site
        String test6 = testGroupReservation.setSiteType("Group");


        assertEquals(test1, true);
        assertEquals(test2, true);
        assertEquals(test3, false);
        assertEquals(test4, true);
        assertEquals(test5, true;
        assertEquals(test6, false);

    }

    //this is the start of the test's for the Reservations Class
    //these tests will check to make sure that the price changes correclty
    //when a discount it applied.
    //group and regual sites cost diffreent so we need to check both when
    //applying a discount to the site.
    @Test
    public void tCustomerDiscount()
    {
        //these are test done by the regualr Cabins
        //normal cases
        int newPriceTest1 = testRegularReservation.getPrice * (0.9);
        String test1 = testRegularReservation.setDiscount(90);

        int newPriceTest2 = testRegularReservation.getPrice * (0.5);
        String test2 = testRegularReservation.setDiscount(50);

        //boundry cases
        String test3 = testRegularReservation.setDiscount(100);

        int newPriceTest4 = testRegularReservation.getPrice;
        String test4 = testRegularReservation.setDiscount(0);

        int newPriceTest5 = testRegularReservation.getPrice * (0.01);
        String test5 = testRegularReservation.setDiscount(1);

        String test6 = testRegularReservation.setDiscount(101);
        String test7 = testRegularReservation.setDiscount(-1);



//
//        //these tests are for a group cabins

        int newPriceTest8 = testGroupReservation.getPrice * (0.9);
        String test8 = testGroupReservation.setDiscount(90);

        int newPriceTest9 = testGroupReservation.getPrice * (0.5);
        String test9 = testGroupReservation.setDiscount(50);

        //boundry cases
        String test10 = testGroupReservation.setDiscount(100);

        int newPriceTest11 = testGroupReservation.getPrice;
        String test11 = testGroupReservation.setDiscount(0);

        int newPriceTest12 = testGroupReservation.getPrice * (0.01);
        String test12 = testGroupReservation.setDiscount(1);

        String test13 = testGroupReservation.setDiscount(101);
        String test14 = testGroupReservation.setDiscount(-1);

//regular Cabins
        assertEquals(test1, newPriceTest1);
        assertEquals(test2, newPriceTest2);
        assertEquals(test3, 0);
        assertEquals(test4, newPriceTest4);
        assertEquals(test5, newPriceTest5);
        assertEquals(test6, "invalid discount price");
        assertEquals(test7, "invalid discount price");

        //group cabins
        assertEquals(test8, newPriceTest8);
        assertEquals(test9, newPriceTest9);
        assertEquals(test10, 0);
        assertEquals(test11, newPriceTest11);
        assertEquals(test12, newPriceTest12);
        assertEquals(test13, "invalid discount price");
        assertEquals(test14, "invalid discount price");


    }


    //this is the start of the test's for the Reservations Class
    //these tests will check to make sure that the price changes correclty
    //when a discount it applied.
    //because both group sites and regualr sites will use the same method
    //it will not matter which site needs to get its dates changed
    @Test
    public void tCustomerStayDates()
    {
        Date obStart = new Date(2020,6,1);
        Date obEnd = new Date(2020,6,8);
        Date obEndShort = new Date(2020,6,5)


        //normal cases sucessfule change
        String test1 = testRegularReservation.changeDate(obStart, obEnd);


        //unsucessfuly change
        String test2 = testRegularReservation.changeDate(obStart, obEnd);

        //Dates that have passes
        Date obPastStart = new Date(2019,6,1);
        Date obPastEnd = new Date(2019,6,8);
        String test3 = testRegularReservation.changeDate(obPastStart, obPastEnd);
        //on date is in the past
        String test4 = testRegularReservation.changeDate(obPastStart, obEnd);

        //Dates are too far into the future
        Date obFutureStart = new Date(2021,6,1);
        Date obFutureEnd = new Date(2021,6,8)
        String test5 = testRegularReservation.changeDate(obFutureStart, obFutureEnd);


        //End date comes before the start date
        String test6 = testRegularReservation.changeDate(obEnd, obStart);



//regular Cabins
        assertEquals(test1, "");
        assertEquals(test2, "These dates will not work for this reservation");
        assertEquals(test3, "These dates will not work for this reservation");
        assertEquals(test4, "These dates will not work for this reservation");
        assertEquals(test5, "These dates will not work for this reservation");
        assertEquals(test6, "These dates will not work for this reservation");





    }


}
