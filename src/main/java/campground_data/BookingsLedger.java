package campground_data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingsLedger {
    public static ArrayList<Reservation> aReservation = new ArrayList<>();
    private ArrayList<Lot> aLot = new ArrayList<>();
    ArrayList<Customer> aCustomer;
    ArrayList<Manager> aManager;

    private static Manager user = new Manager();


    /**
     * checkOverlap is a method that with the lotID find a reservation and check if the specified dates overlap
     * with any other reservation in the date range. if the method returns false there is no overlap and if it returns
     * true there is a overlap.
     *
     * @param nLotID
     * @param obStartDate
     * @param obEndDate
     * @return
     */
    public static boolean checkOverlap(int nLotID, Date obStartDate, Date obEndDate) {
        List<Reservation> aFilteredReservation = aReservation.stream()
                .filter(e -> e.getLot().getLotID() == nLotID)
                .collect(Collectors.toList());

        boolean bOverlap = false;
        for (Reservation obReservation : aFilteredReservation) {
            //If dates Overlap set bOverlap to false and break loop
            if (!((obStartDate.compareTo(obReservation.obEndDate) <= 0) && (obEndDate.compareTo(obReservation.obStartDate) >= 0))) {
                bOverlap = true;
                break;
            }
        }
        return bOverlap;
    }

    /*
    Takes in a integer representing the LotID and returns a Lot with an ID matching the integer input.
    If No lot is found the system returns null and prints out a line of text notifying that the lot cannot be found.
     */

        public ArrayList<Lot> getLotList() {
            return aLot;
        }

        public void setLotList(ArrayList<Lot> aLot) {
            this.aLot = aLot;
        }

        public Lot querySearchCampsite(int LotID) {


            for (Lot lot : aLot) {

                if (lot.nLotID == LotID) {
                    return lot;
                }
            }


            System.out.printf("Search could not find the Lot specified.\n");
            return null;
        }

        /*
        Runs through the reservations and removes any reservation associated with the Lot passed in.
         */
        public void removeReservations(Lot obLot) {

            for (Reservation obRes : aReservation) {
                if (obRes.obLot == obLot) {
                    aReservation.remove(obRes);
                }
            }
        }

        /*
        Outputs the information in a good looking format to the console
         */
        public void displayLot(int LotID) {
            Lot displayLot = querySearchCampsite(LotID);
            System.out.printf("Lot ID: " + displayLot.nLotID +
                    "\nLot Type: " + displayLot.obType +
                    "\nRemoval Reason: " + displayLot.sRemovalReason +
                    "\nAvailability " + displayLot.bAvailability);
        }

        /*
        Sets the availability of the lot to the boolean passed in.
         */
        public void setLotAvailability(Lot obLot, boolean bAvailable) {
            obLot.bAvailability = bAvailable;

        }

        /*
        Takes in a LotID and returns an ArrayList of reservations associated with that Lot based on the ID
         */
        public ArrayList<Reservation> getReservations(int LotID) {
            ArrayList<Reservation> obRes = new ArrayList<>();

            for (int i = 0; i < aReservation.size(); i++) {
                if (aReservation.get(i).obLot.nLotID == LotID) {
                    obRes.add(aReservation.get(i));
                }
            }

            return obRes;

        }

        /**
         * Removes a reservation based on the ID input
         *
         * @param ID
         * @return
         */
        public boolean removeReservation(int ID) {
            /* Create a temporary Reservation ArrayList */
            ArrayList<Reservation> tmpReservations = new ArrayList<>();
            boolean resFound = false;

            for (Reservation res : aReservation) {
                /*If the Reservation ID is found*/
                if (res.getReservationID() == ID) {
                    /*Asks for a confirmation from the user if they want to remove the reservatin */
                    String sConfirm = MainConsole.Prompt("Are you sure you want to remove the reservation? (Y , N )");
                    if (sConfirm.equals("Y")) {
                        resFound = true;
                        /* Double check if they want to remove */
                        continue;
                    }
                }

                /* Add a reservation to the tmp resorvation */
                tmpReservations.add(res);
            }

            /*If the reservation is not fount */
            if (resFound == false)
                System.out.println("Reservation Not Found");

            aReservation = tmpReservations;
            return resFound;
        }

        /**
         * Check get a reservation based on the ID
         *
         * @param ID - Inputs an ID
         */
        public static Reservation getReservation(ArrayList<Reservation> aReservation, int ID) {
            for (Reservation res : aReservation) {
                /*If the Reservation ID is found*/
                if (res.getReservationID() == ID) {
                    return res;
                }
            }
            return null;
        }

        /*
        A simple template class. Checks permissions. Always returns true
        because the permission system is not yet implemented.
         */
        public static boolean isValidPermissions(Manager user) {
            return true;
        }

        public static Manager getUser() {
            return user;
        }


    }
}
