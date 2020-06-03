
import campground_data.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;



public class ReservationTest {

    //these will be the start and end dates
    private Date obStart = new Date(2020, Calendar.JUNE, 11);
    private Date obEnd = new Date(2020, Calendar.JUNE, 15);

    Lot obRegLot = new Lot(LotType.NonServicedIndividual);
    Lot obGroupLot = new Lot(LotType.NonServicedGroup);


    //this will be a basic reservation
    private Reservation testRegularReservation = new Reservation(null, 3, obStart, obEnd, obRegLot);
    //this will be a group reservation
    private Reservation testGroupReservation = new Reservation(null, 6, obStart, obEnd, obGroupLot);


    /*Create array of paying customers*/

    ArrayList<Customer> payingCustomers;

    @Before
    public void setup()
    {
         payingCustomers = new ArrayList<>();
        payingCustomers.add(new Customer("","","","","","","","", 1  ,1,1,1,true));
        payingCustomers.add(new Customer("","","","","","","","", 1  ,1,1,1,true));


    }

    /*Create dates of check-in and check-out*/
    private Date startDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    private Date endDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 13).getTime();
    Reservation oneReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9,0);
    BookingsLedger BL = new BookingsLedger();



    //this is the start of the test's for the Reservations Class
    @Test
    public void tCustomerNumberToLarge() {
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
    public void tCustomerNumberToSmall() {
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
    public void tCustomerNumberBoundry() {
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
    public void tCustomerStayTypeRegular() {
        //these will be the start and end dates
         Date obStart = new Date(2020, 6, 11);
         Date obEnd = new Date(2020, 6, 15);

        //this will be a basic reservation
         Reservation testRegularReservation = new Reservation(null, 3, obStart, obEnd, obRegLot);
        //this will be a group reservation
         Reservation testGroupReservation = new Reservation(null, 6, obStart, obEnd, obGroupLot);
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
    public void tCustomerStayTypeFail() {
        Reservation test2RegularReservation = new Reservation(null, 3, obStart, obEnd, obRegLot);
        //this will be a group reservation
        Reservation test2GroupReservation = new Reservation(null, 6, obStart, obEnd, obGroupLot);
        //fully booked group site

        //we have to add these reservation to our reservation list.
        BL.addReservation(testGroupReservation);
        BL.addReservation(testRegularReservation);
        BL.addReservation(test2RegularReservation);
        BL.addReservation(test2RegularReservation);

        Boolean test1 = test2GroupReservation.setSiteType(LotType.NonServicedIndividual);

        //already fully booked regular site
        Boolean test2 = test2RegularReservation.setSiteType(LotType.NonServicedGroup);

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
    public void tCustomerStayDates() {
        Date obStart = new Date(2020, Calendar.JUNE, 1);
        Date obEnd = new Date(2020, Calendar.JUNE, 8);
        Date obEndShort = new Date(2020, Calendar.JUNE, 5);


        //normal cases sucessfule change
        String test1 = testRegularReservation.changeDate(obStart, obEnd);

        BL.addReservation(testRegularReservation);

        //unsucessfuly change, because there is already a booking on that day
        String test2 = testRegularReservation.changeDate(obStart, obEnd);
        //the end date comes before the start date
        String test3 = testRegularReservation.changeDate(obEnd, obStart);

        //regular Cabins
        assertEquals(test1, "");
        assertEquals(test2, "These dates will not work for this reservation");
        assertEquals(test3, "These dates will not work for this reservation");


    }

        /*Test if a manager attempting to remove a reservation has IN adequate permissions*/
        @Test
        public void testPesmissions() {
            //Simply returns false if permissions are Invalid
            boolean access = false;

            assertEquals(access, !BookingsLedger.isValidPermissions(BookingsLedger.getUser()));
            //assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.user));
        }


        /*If the manager inputs a reservation ID that is not present*/
//        @Test
//        public void testRemoveReservationNotPresent() {
//            boolean access = true;
//            assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.getUser()));
//
//            assertEquals(BL.removeReservation(0), false);
//        }

        /*If the manager inputs a reservation ID that IS present*/
//        @Test
//        public void testRemoveReservationPresent() {
//
//            boolean access = true;
//
//            assertEquals(access, BookingsLedger.isValidPermissions(BookingsLedger.getUser()));
//
//            /*checks that the reservation was added */
//            BL.getAllReservations().add(oneReservation);
//            assertEquals(BL.getAllReservations().size(), 6);
//
//            /*checks that the reservation was successfully removed */
//            BL.removeReservation(0);
//            assertEquals(0, BL.getAllReservations().size());
//        }


    }

