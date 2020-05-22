import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author RaeAnne Hamel
 *
 */

public class TransactionTest {

    /**
     * this test will check that the reservations have valid id to bring up the reservations
     */
    @Test
    public void testValidReservationID()
    {
        int ReserveID1 = 1;
        int ReserveID2 =  20;

        int NotReserveID = -1;
        int NotReserveID2 = 500000000;

       ArrayList<Reservation> obReservationList = new ArrayList<>();


        Transaction transaction1  = testLedger.querySearchReservation(ReserveID1);
        Transaction transaction3  = testLedger.querySearchReservation(ReserveID2);
        Transaction transaction5  = testLedger.querySearchReservation(NotReserveID);
        Transaction transaction6  = testLedger.querySearchReservation(NotReserveID2);



        assertEquals(ReserveID1, transaction1.getReservation());
        assertEquals(ReserveID2, transaction3.getReservation());
        assertEquals(NotReserveID, transaction5.getResevation());
        assertEquals(NotReserveID2, transaction6.getReservation());

    }


    @Test
    public void testCheckPaymentType()
    {
        PaymentType testType1 = PaymentType.CASH;
        PaymentType testType2 = PaymentType.CREDITCARD;
        PaymentType testType3 = PaymentType.DEBIT;

        PaymentMethod payMeth = PaymentMethod.INPERSON;

        Transaction testTransaction = new Transaction(payMeth);

        testTransaction.setPayType(testType1);
        assertEquals(testType1, testTransaction.getPayType());
        testTransaction.setPayType(testType2);
        assertEquals(testType2, testTransaction.getPayType());
        testTransaction.setPayType(testType3);
        assertEquals(testType3, testTransaction.getPayType());

    }

    @Test
    public void testCheckPaymentMethod()
    {
        PaymentMethod testType1 = PaymentMethod.EMAIL;
        PaymentMethod testType2 = PaymentMethod.FAX;
        PaymentMethod testType3 = PaymentMethod.INPERSON;
        PaymentMethod testType4 = PaymentMethod.PHONE;

        PaymentType payType = PaymentType.CREDITCARD;

        Transaction testTransaction = new Transaction(payType, "");

        testTransaction.setPayMethod(testType1);
        assertEquals(testType1, testTransaction.getPayMethod());
        testTransaction.setPayMethod(testType2);
        assertEquals(testType2, testTransaction.getPayMethod());
        testTransaction.setPayMethod(testType3);
        assertEquals(testType3, testTransaction.getPayMethod());
        testTransaction.setPayMethod(testType4);
        assertEquals(testType4, testTransaction.getPayMethod());

    }


    @Test
    public void testCheckReservationPaymentAmount()
    {
        Reservation testReservation = new Reservation() //need constructor setup
        Reservation testReservation2 = new Reservation() //need constructor setup
        Reservation testReservation3 = new Reservation() //need constructor setup
        Reservation testReservation4 = new Reservation() //need constructor setup

        double expectedPrice1 = 45.00;
        double expectedPrice2 = 105.00;
        double expectedPrice3 = 85.00;
        double expectedPrice4 = 345.00;

        assertTrue(testReservation.getPrice());

    }

    @Test
    public void testChangeReservationStatus()
    {
        String testStatus = "inActive";
        PaymentType testType = PaymentType.DEBIT;
        PaymentMethod testMethod = PaymentMethod.INPERSON;

        Reservation testReservation = new Reservation() //need constructor setup
        Reservation testReservation2 = new Reservation() //need constructor setup

        testReservation.setStatus(testStatus);
        assertEquals(testStatus, testReservation.getStatus());

        testReservation2.setStatus(testStatus);
        assertEquals(testStatus, testReservation2.getStatus());
    }
}
