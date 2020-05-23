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

            System.out.print("Actions:[L]ots, [R]eservations, [C]ustomers, [Q]uit: ");

            switch (read.nextLine().toUpperCase()) {
                case "L":
                    System.out.printf("Actions: [S]earch for a lot: ");
                    case "S":
                        String lotID = Prompt("Please enter a Valid Lot ID to search for");

                        int nLotID = Integer.parseInt(lotID);

                        BookingLedger.displayLot(nLotID);
                        Lot obLot;

                        obLot = BookingLedger.querySearchCampsite(nLotID);

                        System.out.print("[S]et Lot availability");
                        switch (read.nextLine().toUpperCase())
                        {
                            case "S":
                            {
                                System.out.println("[A]vailable, [U]navailable");

                                switch (read.nextLine().toUpperCase())
                                {
                                    case "A":

                                        BookingLedger.setLotAvailability(obLot, true);
                                        System.out.println("Lot has been set to available");
                                        break;

                                        case "U":
                                        BookingLedger.setLotAvailability(obLot, false);

                                        String sReason = Prompt("Why is this Lot being made unavailable?");
                                        obLot.setRemovalReason(sReason);
                                        System.out.println("Lot has been set to unavailable");
                                        break;

                                    default:
                                        break;
                                }
                            }
                            default:
                                break;
                        }
                        break;

                case "R":
                    inputSomething();
                    break;

                case "RR":
                        try {
                            int searchID = Integer.parseInt(Prompt("List a reservation ID to Delete."));
                            BookingLedger.removeReservation(searchID);
                        } catch (Exception x){System.out.println("Please enter a number ID");}
                    break;

                case "C":
                    System.out.print("[A]dd Customer\n");

                    case "A":
                        int customerID = 0;
                        String name = PromptNormal("Please enter a name");
                        String address = PromptNormal("Please enter an address");
                        String email = PromptNormal("Please enter an email");
                        long fax = Long.parseLong(PromptNormal("Please enter a fax"));
                        long phone = Long.parseLong(PromptNormal("Please enter a phone"));
                        long secPhone = Long.parseLong(PromptNormal("Please enter a secondary phone"));
                        int nVisits = 0;
                        boolean bFreq = true;
                        int idPool = 0;

                        Customer customer = new Customer(customerID, name, address, email, fax, phone, secPhone, nVisits, bFreq, idPool);
                        BookingLedger.addCustomer(customer);
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

    public static String PromptNormal(String Message)
    {
        System.out.println(Message);
        return read.nextLine();
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
