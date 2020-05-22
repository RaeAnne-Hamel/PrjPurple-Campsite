import campground_data.Reservation;
import jdk.jfr.StackTrace;

import org.junit.*;

import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;

class TestReservation {
    //these will be the start and end dates
    private Date obStart = new Date(2020, Calendar.JUNE, 11);
    private Date obEnd = new Date(2020, Calendar.JUNE, 15);


    //this will be a basic reservation
    private Reservation testRegularReservation = new Reservation(null, 3,obStart, obEnd, "regualr");
    //this will be a group reservation
    private Reservation testGroupReservation = new Reservation(null, 6,obStart, obEnd, "group");

    //this is the start of the test's for the Reservations Class
    @Test
    public void tCustomerNumberToLarge()
    {
        //these are test done by the regualr vabins that is the max number
        String test1 = testRegularReservation.setCustomerNumber(4);

        //these tests are for a group cabins and is the max number
        String test2 = testGroupReservation.setCustomerNumber(8);

    //regualr site max people
        assertEquals(test1, "");
        //tests for the group campsistes that are the max
        assertEquals(test2, "");

    }

    /**
     * minumum number of people for group and regualr cabins
     */
    @Test
    public void tCustomerNumberToSmall()
    {
        //testing both group and regualr customer numbers min's
        String test1 = testRegularReservation.setCustomerNumber(1);
        String test2 = testGroupReservation.setCustomerNumber(1);

        assertEquals(test1, "");
        assertEquals(test2, "");

    }


    /**
     * Boundry cases  of people for group and regualr cabins
     */
    @Test
    public void tCustomerNumberBoundry()
    {
        //Regular cabins, boumdry cases for people who can stay
        String test1 = testRegularReservation.setCustomerNumber(0);
        String test2 = testRegularReservation.setCustomerNumber(5);

        //Group cabins, boumdry cases for people who can stay
        String test3 = testGroupReservation.setCustomerNumber(0);
        String test4 = testGroupReservation.setCustomerNumber(9);

        assertEquals(test1, "There is already the maximum number of people booked");
        assertEquals(test2, "There is already the maximum number of people booked");
        assertEquals(test3, "There is already the maximum number of people booked");
        assertEquals(test4, "There is already the maximum number of people booked");

    }






    //this is the start of the test's for the Reservations Class
    //these tests will check out the tests to change the site types
    @Test
    public void tCustomerStayTypeRegular()
    {
        //this is a regualr case of someone changing the site type.
        //these are test done by the regualr vabins
        //from regular to regualr
        Boolean test1 = testRegularReservation.setSiteType("Regular");
        //from regular to group
        Boolean test2 = testRegularReservation.setSiteType("Group");

        //these tests are for a group cabins
        //from group to group
        Boolean test3 = testGroupReservation.setSiteType("Group");
        //from group to regular
        Boolean test4 = testGroupReservation.setSiteType("Regular");



        //all of these will work.
        assertEquals(test1, true);
        assertEquals(test2, true);
        assertEquals(test3, true);
        assertEquals(test4, true);


    }

    /**
     * these tests will test to see if the method will fail,
     * if the person can not change the site. you can only change the site if the dates
     * are open.
     */
    @Test
    public void tCustomerStayTypeFail()
    {
         Reservation test2RegularReservation = new Reservation(null, 3,obStart, obEnd, "regualr");
        //this will be a group reservation
         Reservation test2GroupReservation = new Reservation(null, 6,obStart, obEnd, "group");
        //fully booked group site

        Boolean test1 = test2GroupReservation.setSiteType("Group");

        //already fully booked regular site
        Boolean test2 = test2RegularReservation.setSiteType("Group");

        //both of these should fail.
        assertEquals(test1, false);
        assertEquals(test2, false);

    }


    /**
     * thes user enters something in that is not what we want.
     */
    @Test
    public void tCustomerStayTypeBoundry()
    {
        //this is a regualr case of someone changing the site type.
        //these are test done by the regualr vabins
        //from regular to no a site type
        Boolean test1 = testRegularReservation.setSiteType("AAAAA");
        //from regular to group but group mispelled
        Boolean test2 = testRegularReservation.setSiteType("group");

        //these tests are for a group cabins
        //from group to to not a Site type
        Boolean test3 = testGroupReservation.setSiteType("AAAA");
        //from group to regular, but regualr mispelled
        Boolean test4 = testGroupReservation.setSiteType("Reglar");



        //all of these will work.
        assertEquals(test1, false);
        assertEquals(test2, false);
        assertEquals(test3, false);
        assertEquals(test4, false);


    }

    //this is the start of the test's for the Reservations Class
    //these tests will check to make sure that the price changes correclty
    //when a discount it applied.
    //group and regual sites cost diffreent so we need to check both when
    //applying a discount to the site.
    @Test
    public void tCustomerDiscountRegular()
    {
        //these are test done by the regualr Cabins
        //normal cases for regualr cabins
        double newPriceTest1 = testRegularReservation.price * (0.9);
        double test1 = testRegularReservation.setDiscount(90);

        double newPriceTest2 = testRegularReservation.price * (0.5);
        double test2 = testRegularReservation.setDiscount(50);


       //these tests are for a group cabins
        //normal cases for group cabins
        double newPriceTest8 = testGroupReservation.price * (0.9);
        double test8 = testGroupReservation.setDiscount(90);

        double newPriceTest9 = testGroupReservation.price * (0.5);
        double test9 = testGroupReservation.setDiscount(50);


        //regular Cabins
        assertEquals(test1,  newPriceTest1, 0.001);
        assertEquals(test2, newPriceTest2, 0.001);

        //group cabins
        assertEquals(test8, newPriceTest8, 0.001);
        assertEquals(test9, newPriceTest9, 0.001);

    }

    /**
     * maximum discount for group and regualr site
     */
    @Test
    public void tCustomerDiscountMax()
    {
        //Regualr Site
        double test1 = testRegularReservation.setDiscount(100);
        //Group Site
        double test2 = testGroupReservation.setDiscount(100);

        assertEquals(test1, 0.0, 0.001);
        assertEquals(test2, 0.0, 0.001);

    }

    /**
     * Minumum discount for group and regualr site
     */
    @Test
    public void tCustomerDiscountMin()
    {
        //Regualr Site minumum
        double newPriceTest1 = testRegularReservation.price;
        double test1 = testRegularReservation.setDiscount(0);

        double newPriceTest2 = testRegularReservation.price * (0.01);
        double test2 = testRegularReservation.setDiscount(1);

        //Group Site minimum
        double newPriceTest3 = testGroupReservation.price;
        double test3 = testGroupReservation.setDiscount(0);

        double newPriceTest4 = testGroupReservation.price * (0.01);
        double test4 = testGroupReservation.setDiscount(1);

        assertEquals(test1, newPriceTest1, 0.001);
        assertEquals(test2, newPriceTest2, 0.001);

        assertEquals(test3, newPriceTest3, 0.001);
        assertEquals(test4, newPriceTest4, 0.001);


    }

    /**
     * boundry cases for the cabin discount types
     */
    @Test
    public void tCustomerDiscountBoundry()
    {
        //regular cabin boundry cases
        double newPriceTest1 = testGroupReservation.price;
        double test1 = testRegularReservation.setDiscount(101);

        double newPriceTest2 = testGroupReservation.price;
        double test2 = testRegularReservation.setDiscount(-1);


        //group cabin boundry cases
        double newPriceTest3 = testGroupReservation.price;
        double test3 = testGroupReservation.setDiscount(101);

        double newPriceTest4 = testGroupReservation.price;
        double test4 = testGroupReservation.setDiscount(-1);


        assertEquals(test1, newPriceTest1, 0.001);
        assertEquals(test2, newPriceTest2, 0.001);
        assertEquals(test3, newPriceTest3, 0.001);
        assertEquals(test4, newPriceTest4, 0.001);


    }


    //this is the start of the test's for the Reservations Class
    //these tests will check to make sure that the price changes correclty
    //when a discount it applied.
    //because both group sites and regualr sites will use the same method
    //it will not matter which site needs to get its dates changed
    @Test
    public void tCustomerStayDates()
    {
        Date obStart = new Date(2020, Calendar.JUNE,1);
        Date obEnd = new Date(2020,Calendar.JUNE,8);
        Date obEndShort = new Date(2020,Calendar.JUNE,5);


        //normal cases sucessfule change
        String test1 = testRegularReservation.changeDate(obStart, obEnd);


        //unsucessfuly change
        String test2 = testRegularReservation.changeDate(obStart, obEnd);

        //Dates that have passes
        Date obPastStart = new Date(2019,Calendar.JUNE,1);
        Date obPastEnd = new Date(2019,Calendar.JUNE,8);
        String test3 = testRegularReservation.changeDate(obPastStart, obPastEnd);
        //on date is in the past
        String test4 = testRegularReservation.changeDate(obPastStart, obEnd);

        //Dates are too far into the future
        Date obFutureStart = new Date(2021,Calendar.JUNE,1);
        Date obFutureEnd = new Date(2021,Calendar.JUNE,8);
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
