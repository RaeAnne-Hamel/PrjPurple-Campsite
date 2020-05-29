import campground_data.Lot;
import campground_data.LotType;
import campground_data.Reservation;
import org.junit.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class Test2bManagerEditsThePriceOfAnAccomidation {


    //this is the start of the test's for testing if you can add a discount to the price of a cabin
    //these tests show how it should work normaly.


    //these will be the start and end dates for the reservations shown below
    private Date obStart = new Date(2020, Calendar.JUNE, 11);
    private Date obEnd = new Date(2020, Calendar.JUNE, 15);

    //the types of lots that the reserrvations are staying on.
    Lot obRegLot = new Lot(LotType.NonServicedIndividual);
    Lot obGroupLot = new Lot(LotType.NonServicedGroup);


    //this will be a basic reservation
     Reservation GlobalRegularReservation;
    //this will be a group reservation
     Reservation GlobalGroupReservation;

    @Before
    public void setUp()
    {

        //this will be a basic reservation
         Reservation testRegularReservation = new Reservation(null, 3, obStart, obEnd, obRegLot);
        //this will be a group reservation
         Reservation testGroupReservation = new Reservation(null, 6, obStart, obEnd, obGroupLot);

         //make the new reservation be the global ones so all the classes can get to them
        GlobalRegularReservation = testRegularReservation;
        GlobalGroupReservation = testGroupReservation;


    }




    /**
     * all of these tests should work normaly, all of them are normal cases for editing the price.
     */
    @Test
    public void tCustomerDiscountRegular()
    {
        //these are test done by the regualr Cabins
        //normal cases for regualr cabins
        double newPriceTest1 = GlobalRegularReservation.getPrice() * (0.9);
        double test1 = GlobalRegularReservation.getTransaction().setDiscount(90);

        double newPriceTest2 = GlobalRegularReservation.getPrice() * (0.5);
        double test2 = GlobalRegularReservation.getTransaction().setDiscount(50);


        //these tests are for a group cabins
        //normal cases for group cabins
        double newPriceTest8 = GlobalGroupReservation.getPrice() * (0.9);
        double test8 = GlobalGroupReservation.getTransaction().setDiscount(90);

        double newPriceTest9 = GlobalGroupReservation.getPrice() * (0.5);
        double test9 = GlobalGroupReservation.getTransaction().setDiscount(50);



        //regular Cabins, normal
        assertEquals(test1,  newPriceTest1, 0.001);
        assertEquals(test2, newPriceTest2, 0.001);

        //group cabins, normal
        assertEquals(test8, newPriceTest8, 0.001);
        assertEquals(test9, newPriceTest9, 0.001);

    }

    /**
     * maximum discount for group and regualr site
     * these are to test the maximum discount that can be set on a cabin (100%),
     * making tha cabin free.
     */
    @Test
    public void tCustomerDiscountMax()
    {
        //Regualr Site
        double test1 = GlobalRegularReservation.getTransaction().setDiscount(100);
        //Group Site
        double test2 = GlobalGroupReservation.getTransaction().setDiscount(100);

        assertEquals(test1, 0.0, 0.001);
        assertEquals(test2, 0.0, 0.001);

    }

    /**
     * Minumum discount for group and regualr site
     * meaning no discount on the price of the cabin.
     */
    @Test
    public void tCustomerDiscountMin()
    {
        //Regualr Site minumum
        //the discount price is 0 meaninf the price should not change
        double newPriceTest1 = GlobalRegularReservation.getPrice();
        double test1 = GlobalRegularReservation.getTransaction().setDiscount(0);

        //Group Site minimum
        //the price is set to 0 meaning the price of the cabin will not change
        double newPriceTest2 = GlobalGroupReservation.getPrice();
        double test2 = GlobalGroupReservation.getTransaction().setDiscount(0);

        assertEquals(test1, newPriceTest1, 0.001);
        assertEquals(test2, newPriceTest2, 0.001);

    }


    /**
     * boundry cases for the cabin discount types,
     * all cases that do not work 101, or -1, should just return
     * the regualr price and not aulter the price of the cabin
     */
    @Test
    public void tCustomerDiscountBoundry()
    {
        //regular cabin boundry cases
        double newPriceTest1 = GlobalRegularReservation.getPrice();
        double test1 = GlobalRegularReservation.getTransaction().setDiscount(101);

        double newPriceTest2 = GlobalRegularReservation.getPrice();
        double test2 = GlobalRegularReservation.getTransaction().setDiscount(-1);


        //group cabin boundry cases
        double newPriceTest3 = GlobalGroupReservation.getPrice();
        double test3 = GlobalGroupReservation.getTransaction().setDiscount(101);

        double newPriceTest4 = GlobalGroupReservation.getPrice();
        double test4 = GlobalGroupReservation.getTransaction().setDiscount(-1);


        assertEquals(test1, newPriceTest1, 0.001);
        assertEquals(test2, newPriceTest2, 0.001);
        assertEquals(test3, newPriceTest3, 0.001);
        assertEquals(test4, newPriceTest4, 0.001);


    }
}
