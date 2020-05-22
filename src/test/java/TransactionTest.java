import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        PaymentType testType1 = cash;
        PaymentType testType2 = debit;
        PaymentType testType3 = credit;

        PaymentMethod payMeth = inPerson;

        Transaction testTransaction = new Transaction(1, "", payMeth);

        testTransaction.setPaymentType(testType1);
        assertEquals(testType1, testTransaction.getPaymentType());
        testTransaction.setPaymentType(testType2);
        assertEquals(testType2, testTransaction.getPaymentType());
        testTransaction.setPaymentType(testType3);
        assertEquals(testType3, testTransaction.getPaymentType());

    }

    @Test
    public void testCheckPaymentMethod()
    {
        PaymentMethod testType1 = inPerson;
        PaymentMethod testType2 = fax;
        PaymentMethod testType3 = email;
        PaymentMethod testType4 = phone;

        PaymentType payType = debit;

        Transaction testTransaction = new Transaction(1, payType, "");

        testTransaction.setPaymentMethod(testType1);
        assertEquals(testType1, testTransaction.getPaymentMethod());
        testTransaction.setPaymentMethod(testType2);
        assertEquals(testType2, testTransaction.getPaymentMethod());
        testTransaction.setPaymentMethod(testType3);
        assertEquals(testType3, testTransaction.getPaymentMethod());
        testTransaction.setPaymentMethod(testType4);
        assertEquals(testType4, testTransaction.getPaymentMethod());

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
        
        assertEquals(expectedPrice1, testReservation.getPrice(), 0.001);
        assertEquals(expectedPrice2, testReservation2.getPrice(), 0.001);
        assertEquals(expectedPrice3, testReservation3.getPrice(), 0.001);
        assertEquals(expectedPrice4, testReservation4.getPrice(), 0.001);
        assertEquals(expectedPrice4, testReservation4.getPrice(), 0.001);

    }

    @Test
    public void testChangeReservationStatus()
    {
        String testStatus = "inActive";
        PaymentType testType = debit;
        PaymentMethod testMethod = inPerson;

        Reservation testReservation = new Reservation() //need constructor setup
        Reservation testReservation2 = new Reservation() //need constructor setup

        testReservation.setStatus(testStatus);
        assertEquals(testStatus, testReservation.getStatus());

        testReservation2.setStatus(testStatus);
        assertEquals(testStatus, testReservation2.getStatus());
    }
}
