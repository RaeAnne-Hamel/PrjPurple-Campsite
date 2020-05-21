import campground_data.*;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class BookingsLedgerTest {

    @Test
    public void testThatQuerySearchLotIDReturnsCorrectLot()
    {
        int LotID1 = 1;
        int LotID0 = 0;
        int LotID255 = 255;
        int LotIDminus1 = -1;
        int LotID256 = 256;
        int LotID1000 = 1000;

        BookingsLedger TestLedger = new BookingsLedger();

        Lot testLot1 = TestLedger.querySearchCampsite(LotID1);
        Lot testLot0 = TestLedger.querySearchCampsite(LotID0);
        Lot testLot255 = TestLedger.querySearchCampsite(LotID255);
        Lot testLotminus1 = TestLedger.querySearchCampsite(LotIDminus1);
        Lot testLot256 = TestLedger.querySearchCampsite(LotID256);
        Lot testLot1000 = TestLedger.querySearchCampsite(LotID1000);


        assertEquals(LotID1, testLot1.getLotID());
        assertEquals(LotID0, testLot0.getLotID());
        assertEquals(LotID255, testLot255.getLotID());
        assertEquals(LotIDminus1, testLotminus1.getLotID());
        assertEquals(LotID256, testLot256.getLotID());
        assertEquals(LotID1000, testLot1000.getLotID());

    }

    @Test
    public void testCorrectReservationsAreShown()
    {
        int LotID = 1;

        BookingsLedger TestLedger = new BookingsLedger();

        Lot testLot = TestLedger.querySearchCampsite(LotID);

        ArrayList<Reservation> initialReservation = TestLedger.getReservations(LotID);

        ArrayList<Reservation> testReservation = TestLedger.removeLot(testLot.getLotID());

        assertEquals(initialReservation, testReservation);

    }

    @Test
    public void testSetRemovalReasonSetsCorrectReason()
    {
        String testReason = "Testing";
        String testReason2 = "";
        String testReason3 = "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" +
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" +
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" +
                "hhhhhh";

        ArrayList<Reservation> obReservationList = new ArrayList<>();
        boolean sAvailable = true;

        Lot testLot = new Lot(1, LotType.Serviced, obReservationList, 1, "", sAvailable);

        testLot.setRemovalReason(testReason);
        assertEquals(testReason, testLot.getRemovalReason());

        testLot.setRemovalReason(testReason2);
        assertEquals(testReason2, testLot.getRemovalReason());

        testLot.setRemovalReason(testReason3);
        assertEquals(testReason3, testLot.getRemovalReason());

    }

    @Test
    public void testSetAvailablility()
    {
        boolean availabilityTest = false;

        ArrayList<Reservation> obReservationList = new ArrayList<>();
        boolean sAvailable = true;

        Lot testLot = new Lot(1, LotType.Serviced, obReservationList, 1, "", sAvailable);

        testLot.setAvailability(false);

        assertEquals(availabilityTest, testLot.getAvailability());


    }


}
