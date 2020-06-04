import campground_data.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * @author RaeAnne Hamel
 *
 */

public class TransactionTest {

    ArrayList<Customer> payingCustomers;

    @Before
    public void setup()
    {
        payingCustomers = new ArrayList<>();
        payingCustomers.add(new Customer("","","","","","","","", 1  ,1,1,1,true));
        payingCustomers.add(new Customer("","","","","","","","", 1  ,1,1,1,true));
    }

    private Date startDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    private Date endDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 13).getTime();


    @Test
    public void testCheckForReservationID()
    {
        Reservation testReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9, 0);
        Reservation testReservation2 = new Reservation(new Lot(),startDate,endDate,payingCustomers,9, 1);

        int testID1 = 0;
        int testID2 = 1;
        int testID3 = -1;
        int testID4 =2000000;

        assertEquals(testID1, testReservation.getReservationID());
        assertEquals(testID2, testReservation2.getReservationID());
        assertNotEquals(testID3, testReservation.getReservationID());
        assertNotEquals(testID4, testReservation2.getReservationID());
    }


    @Test
    public void testCheckPaymentType()
    {
        PaymentType testType1 = PaymentType.DEBIT;
        PaymentType testType2 = PaymentType.CREDITCARD;

        Reservation testReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9, 0);

        PaymentMethod payMeth = PaymentMethod.INPERSON;
        PaymentType testDefault = PaymentType.CASH ;

        String testWrong = "";
        long testWrong2 = 17398740;

        Transaction testTransaction = new Transaction(testReservation, testDefault, payMeth);

        assertEquals(testDefault, testTransaction.getPayType());
        testTransaction.setPayType(testType1);
        assertEquals(testType1, testTransaction.getPayType());
        testTransaction.setPayType(testType2);
        assertEquals(testType2, testTransaction.getPayType());
        assertNotEquals(testWrong, testTransaction.getPayType());
        assertNotEquals(testWrong2, testTransaction.getPayType());

    }

    @Test
    public void testCheckPaymentMethod()
    {
        Reservation testReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9, 0);

        PaymentMethod testDefault = PaymentMethod.EMAIL;
        PaymentMethod testType2 = PaymentMethod.FAX;
        PaymentMethod testType3 = PaymentMethod.INPERSON;
        PaymentMethod testType4 = PaymentMethod.PHONE;
        String testWrong1 = "";
        String testWrong2 = "airplane";

        PaymentType payType = PaymentType.CREDITCARD;


        Transaction testTransaction = new Transaction(testReservation, payType, testDefault);


        assertEquals(testDefault, testTransaction.getPayMethod());
        testTransaction.setPayMethod(testType2);
        assertEquals(testType2, testTransaction.getPayMethod());
        testTransaction.setPayMethod(testType3);
        assertEquals(testType3, testTransaction.getPayMethod());
        testTransaction.setPayMethod(testType4);
        assertEquals(testType4, testTransaction.getPayMethod());
        assertNotEquals(testWrong1, testTransaction.getPayMethod());
        assertNotEquals(testWrong2, testTransaction.getPayMethod());

    }


    @Test
    public void testCheckReservationPaymentAmount()
    {
        Reservation testReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9, 0);


        double expectedPrice1 = 45.00;
        double expectedPrice2 = 100.00;
        double expectedPrice3 = 85.00;


        assertEquals(expectedPrice2,testReservation.getPrice(), 0.001);
        assertNotEquals(expectedPrice1,testReservation.getPrice(), 0.001);
        assertNotEquals(expectedPrice3, testReservation.getPrice(), 0.001);
    }

    @Test
    public void testChangeReservationStatus()
    {
        Reservation testReservation = new Reservation(new Lot(),startDate,endDate,payingCustomers,9,0);
        Reservation testReservation2 = new Reservation(new Lot(),startDate,endDate,payingCustomers,9, 1);


        PaymentType testType = PaymentType.DEBIT;
        PaymentMethod testMethod = PaymentMethod.INPERSON;

        assertEquals(true, testReservation.getStatus());
        testReservation.setStatus();
        assertEquals(false, testReservation.getStatus());

        assertEquals(true, testReservation2.getStatus());
        testReservation2.setStatus();
        assertEquals(false, testReservation2.getStatus());
    }


}
