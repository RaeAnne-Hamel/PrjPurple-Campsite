package campground_ui;

import campground_data.*;

import java.util.Scanner;

/***
 * example of console user interface
 */
public class MainConsole {

    public static BookingsLedger BookingLedger = new BookingsLedger();
    //private static CampLedger campLedger = new CampLedger();
    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args)
    {
        boolean quit = false;
        do{
            System.out.print("Actions:[L]ist Something, [A]dd Something, [Q]uit, [RR]RemoveReservation ");
            switch (read.nextLine().toUpperCase()) {
                case "L":
                    listSomething();
                    break;
                case "A":
                    inputSomething();
                    break;
                case "RR":
                    int searchID = Integer.parseInt(Prompt("List a reservation ID to Delete."));
                    BookingLedger.removeReservation(searchID);
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
