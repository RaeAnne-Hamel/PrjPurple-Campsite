package campground_ui;

import campground_data.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainConsole {

    public static BookingsLedger BookingLedger = new BookingsLedger();



    //private static CampLedger campLedger = new CampLedger();
    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args)
    {
        ArrayList<Lot> aLot = new ArrayList<>();
        Lot obLot1 = new Lot();
        aLot.add(obLot1);
        BookingLedger.setLotList(aLot);

        boolean quit = false;
        do{

            System.out.print("Actions: [L]ots, [R]eservations, [Q]uit: ");

            switch (read.nextLine().toUpperCase()) {
                case "L":
                    System.out.print("Actions: [S]earch for a lot: ");
                    switch(read.nextLine().toUpperCase())
                    {
                        case "S":
                            String lotID = Prompt("Please enter a Valid Lot ID to search for: ");

                            int nLotID = Integer.parseInt(lotID);

                            BookingLedger.displayLot(nLotID);
                            Lot obLot;

                            try
                            {
                                obLot = BookingLedger.querySearchCampsite(nLotID);
                            }
                            catch(NullPointerException e)
                            {
                                break;
                            }


                            System.out.print("Actions: [S]et Lot availability, [V]iew Reservations: ");
                            switch (read.nextLine().toUpperCase())
                            {
                                case "S":
                                    System.out.print("Actions: [A]vailable, [U]navailable: ");

                                    switch (read.nextLine().toUpperCase()) {
                                        case "A":

                                            BookingLedger.setLotAvailability(obLot, true);

                                            String sReason = "";
                                            obLot.setRemovalReason(sReason);
                                            System.out.println("Lot has been set to available");
                                            break;
                                        case "U":
                                            BookingLedger.setLotAvailability(obLot, false);

                                            sReason = Prompt("Why is this Lot being made unavailable?");
                                            obLot.setRemovalReason(sReason);
                                            System.out.println("Lot has been set to unavailable");
                                            break;

                                        default:
                                            break;
                                    }
                                    break;

                                case "V":

                                    break;

                                default:
                                    break;

                            }

                        default:
                            break;
                    }
                    break;

                case "R":

                    System.out.print("Actions: [RR]emove Reservation");
                    switch(read.nextLine().toUpperCase())
                    {
                        case "RR":
                            try {
                                int searchID = Integer.parseInt(Prompt("List a reservation ID to Delete."));
                                BookingLedger.removeReservation(searchID);
                            } catch (Exception x){System.out.println("Please enter a number ID");};
                            break;
                        default:
                            break;
                    }

                    break;

                default:
                    quit = true;
                    break;
            }
            System.out.println("");
            System.out.println("");
        } while (!quit);
        System.out.println("Quit Application");
    }

    public static String Prompt(String Message)
    {
        System.out.println(Message);
        return read.nextLine().toUpperCase();
    }

    private static void listSomething(){
/*

        if(campLedger.getGuests().values().size()>0) {
            int i = 0;
            System.out.println("Cars in system:");
            for (Guest guest : campLedger.getGuests().values()) {
                System.out.println(++i + ": " + guest);
            }
        }else{
            System.out.println("No Cars in system:");
        }

*/

    }
    private static void inputSomething()
    {
/*

        Guest guest = new Guest();
        System.out.print("Guest ID: ");
        guest.setGuestID(Integer.parseInt(read.nextLine()));
        System.out.print("First Name: ");
        guest.setFirstName(read.nextLine());

        //output errors if any otherwise output new car
        HashMap<String,String> errors =campLedger.validationHelper().getErrors(guest);
        if(errors.size()==0) {
            System.out.println("Success:");
            System.out.println("Added:" + campLedger.guestController().addGuest(guest));
        }else{
            for(String errMsg : errors.values()) {
                System.out.println("Errors:");
                System.out.println(errMsg);
            }
        }
*/


    }
}
