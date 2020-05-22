import campground_data.Lot;
import campground_data.LotType;
import campground_data.Reservation;
import jdk.jfr.StackTrace;

import org.junit.*;

import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.*;

import javax.xml.validation.Validator;


class ReservationTest {

    //these will be the start and end dates
    private Date obStart = new Date(2020, Calendar.JUNE, 11);
    private Date obEnd = new Date(2020, Calendar.JUNE, 15);

    Lot obRegLot = new Lot( LotType.NonServicedIndividual);
    Lot obGroupLot = new Lot( LotType.NonServicedIndividual);



    //this will be a basic reservation
    private Reservation testRegularReservation = new Reservation(null, 3,obStart, obEnd, obRegLot);
    //this will be a group reservation
    private Reservation testGroupReservation = new Reservation(null, 6,obStart, obEnd, obGroupLot);





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
        Boolean test1 = testRegularReservation.setSiteType(LotType.Cabin);
        //from regular to group
        Boolean test2 = testRegularReservation.setSiteType(LotType.ServicedGroup);

        //these tests are for a group cabins
        //from group to group
        Boolean test3 = testGroupReservation.setSiteType(LotType.ServicedGroup);
        //from group to regular
        Boolean test4 = testGroupReservation.setSiteType(LotType.NonServicedIndividual);



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
         Reservation test2RegularReservation = new Reservation(null, 3,obStart, obEnd, obRegLot);
        //this will be a group reservation
         Reservation test2GroupReservation = new Reservation(null, 6,obStart, obEnd, obGroupLot);
        //fully booked group site

        Boolean test1 = test2GroupReservation.setSiteType(LotType.DeluxeCabin);

        //already fully booked regular site
        Boolean test2 = test2RegularReservation.setSiteType(LotType.ServicedIndividual);

        //both of these should fail.
        assertEquals(test1, false);
        assertEquals(test2, false);

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
