import campground_data.*;
import org.junit.*;

import java.lang.reflect.Array;
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

        ArrayList<Lot> aLot = new ArrayList<>();

        Lot testLot1 = new Lot(1);
        Lot testLot0 = new Lot(0);
        Lot testLot255 = new Lot(255);
        Lot testLotminus1 = new Lot(-1);
        Lot testLot256 = new Lot(256);
        Lot testLot1000 = new Lot(1000);

        aLot.add(testLot1);
        aLot.add(testLot0);
        aLot.add(testLot255);
        aLot.add(testLotminus1);
        aLot.add(testLot256);
        aLot.add(testLot1000);

        TestLedger.setLotList(aLot);

        Lot QueryTestLot1 = TestLedger.querySearchCampsite(LotID1);
        Lot QueryTestLot0 = TestLedger.querySearchCampsite(LotID0);
        Lot QueryTestLot255 = TestLedger.querySearchCampsite(LotID255);
        Lot QueryTestLotMinus1 = TestLedger.querySearchCampsite(LotIDminus1);
        Lot QueryTestLot256 = TestLedger.querySearchCampsite(LotID256);
        Lot QueryTestLot1000 = TestLedger.querySearchCampsite(LotID1000);

        assertEquals(LotID1, testLot1.getLotID());
        assertEquals(LotID0, testLot0.getLotID());
        assertEquals(LotID255, testLot255.getLotID());
        assertEquals(LotIDminus1, testLotminus1.getLotID());
        assertEquals(LotID256, testLot256.getLotID());
        assertEquals(LotID1000, testLot1000.getLotID());

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


        Lot testLot = new Lot(1, LotType.ServicedIndividual, obReservationList, 1, "", sAvailable);


        testLot.setRemovalReason(testReason);
        assertEquals(testReason, testLot.getRemovalReason());

    }

    @Test
    public void testSetAvailablility()
    {
        boolean availabilityTest = false;

        ArrayList<Reservation> obReservationList = new ArrayList<>();
        boolean sAvailable = true;


        Lot testLot = new Lot(1, LotType.ServicedIndividual, obReservationList, 1, "", sAvailable);

        testLot.setAvailability(false);

        assertEquals(availabilityTest, testLot.getAvailability());


    }


}
