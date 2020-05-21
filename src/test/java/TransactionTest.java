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

    }
}
