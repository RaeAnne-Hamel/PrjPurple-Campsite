import campground_data.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AddAccommodation2aTest {

    @Before
    public void Setup()
    {

    }

    BookingsLedger BL = new BookingsLedger();

    /*
    Tests if adding accommodations to the ledger using the method addAccommodation() works.
    Also tests error handling for addAccommodation. Should not allow a Lot to be available with a reason or a lot to be unavailable with no reason.
     */
    @Test
    public void testThatAddAccommodationAddsToLedger()
    {

        ArrayList<Reservation> obRes = new ArrayList<>();

        Lot lotTesta = new Lot(0, LotType.ServicedIndividual, obRes, 0, "N/A", true);
        Lot lotTestb = new Lot(1, LotType.ServicedIndividual, obRes, 1, "Test", false);
        Lot lotTestc = new Lot(2, LotType.ServicedIndividual, obRes, 2, "Test", true);
        Lot lotTestd = new Lot(3, LotType.ServicedIndividual, obRes, 3, "N/A", false);

        BL.addAccommodation(lotTesta);
        BL.addAccommodation(lotTestb);
        BL.addAccommodation(lotTestc);
        BL.addAccommodation(lotTestd);

        Lot lotTesta1 = BL.querySearchCampsite(0);
        Lot lotTestb1 = BL.querySearchCampsite(1);
        Lot lotTestc1 = BL.querySearchCampsite(2);
        Lot lotTestd1 = BL.querySearchCampsite(3);

        assertEquals(lotTesta, lotTesta1);
        assertEquals(lotTestb, lotTestb1);
        assertEquals(lotTestc, lotTestc1);
        assertEquals(lotTestd, lotTestd1);

    }



}
