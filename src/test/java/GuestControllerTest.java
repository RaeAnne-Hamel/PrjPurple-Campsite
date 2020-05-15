import campground_data.*;
import org.junit.*;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;
public class GuestControllerTest {
    /***
     * Test that valid car CAN BE added to car fleet
     * also test that car with same vin CAN NOT be added
     */
    @Test
    public void testAddGuestWithValidGuest() {

/*        GuestController gc = new GuestController((new HashMap<Integer, Guest>()),(new ValidationHelper()));

        Guest g1 = new Guest();
        g1.setGuestID(1);
        g1.setFirstName("Bob");

        Guest g2 = new Guest();
        g2.setGuestID(1);
        g2.setFirstName("Sally");

        assertSame( "successful- returns original guest", gc.addGuest(g1), g1) ;

        //add different guest with same id
        assertNull( " add guest with same id already  exists  returns null", gc.addGuest(g2));

        //add guest with different id
        g2.setGuestID(2);

        assertSame( "successful second add guest returns original car", gc.addGuest(g2), g2) ;*/

    }

    /***
     * Test that invalid guest CAN NOT be added to guests
     */
    @Test
    public void testAddGuestWithInvalidGuest() {
/*        GuestController gc = new GuestController((new HashMap<Integer, Guest>()),(new ValidationHelper()));
        assertNull( "Add invalid guest expect null", gc.addGuest((new Guest())));*/
    }
}
