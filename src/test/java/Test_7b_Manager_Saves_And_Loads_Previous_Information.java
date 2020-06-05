
import campground_data.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class Test_7b_Manager_Saves_And_Loads_Previous_Information {

    /* Create Booking Ledger */
    BookingsLedger bl = new BookingsLedger();


    /* Sample Customers */

    Customer cust0 = new Customer("John", "Doe", "123 Street", "Saskatchewan", "Regine",
                    "S0T4T3", "Canada", "Email@email.com", 1231231234);

    Customer cust1 = new Customer("Jane", "Doe", "321 Street", "Saskatchewan", "Moose Jaw",
            "S0T4T3", "Canada", "Email@email.com", 1231231234);

    /* Sample Manager*/
    Manager manager0 = new Manager();

    /* Lots */


    @Before
    public void setup()
    {
        System.out.println("Start Saving");
        /* Customer */
        bl.addCustomer(cust0);
        bl.addCustomer(cust1);

        bl.setReservationsList(new ArrayList<>());

        /* Lot */
        Lot lot0 = new  Lot(0, LotType.Cabin, bl.getAllReservations(),0, "String sRemovalReason", true);
        bl.addLot(lot0);

        /* Ensures previous reservations are cleared */
        assertEquals(0, bl.getAllReservations().size());

        /* Reservation */
        Reservation reservation0 = new Reservation(bl.getCustomerList(), 1, new Date(2021,7,10), new Date(2021,7,1), lot0);
        bl.addReservation(reservation0);

        /* Transaction */
        Transaction transaction0 = new Transaction(reservation0,PaymentType.CASH, PaymentMethod.INPERSON);
        bl.addTransaction(transaction0);

        /* Manager */
        manager0.setID(0);
        manager0.setPermissions(1);
        bl.addManager(manager0);
    }

    @Test
    public void tManageData()
    {
        /* Save Data */
        PersistentDataManager.saveAll(bl);


        /* Clear All Data */
        bl.setReservationsList(new ArrayList<>());
        bl.setLotList(new ArrayList<>());
        bl.setManagerList(new ArrayList<>());
        bl.setCustomerList(new ArrayList<>());
        bl.setTransactionList(new ArrayList<>());

        bl.setCustomerList(PersistentDataManager.load("src/files/Customers.txt", LoadType.Customer, bl));
        bl.setLotList(PersistentDataManager.load("src/files/Lots.txt", LoadType.Lot, bl));
        bl.setManagerList(PersistentDataManager.load("src/files/Managers.txt", LoadType.Manager, bl));
        bl.setReservationsList(PersistentDataManager.load("src/files/Reservations.txt", LoadType.Reservation, bl));
        bl.setTransactionList(PersistentDataManager.load("src/files/Transactions.txt", LoadType.Transaction, bl));

        System.out.println("Loading");
        System.out.println(bl.getAllReservations().size());
        assertEquals(1, bl.getAllReservations().size());

        System.out.println(bl.getTransactionList().size());
        assertEquals(1, bl.getTransactionList().size());

        System.out.println(bl.getManagerList().size());
        assertEquals(1, bl.getManagerList().size());

        System.out.println(bl.getLotList().size());
        assertEquals(1, bl.getLotList().size());

        System.out.println(bl.getCustomerList().size());
        assertEquals(2, bl.getCustomerList().size());
    }
}

