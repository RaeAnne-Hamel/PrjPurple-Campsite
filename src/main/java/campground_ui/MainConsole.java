package campground_ui;

import campground_data.*;

import java.util.*;

public class MainConsole {

    /*
    Ledger is started up so that data can be added and edited to it. -EB
     */
    public static BookingsLedger BookingLedger = new BookingsLedger();

    /*
    Scanner is created to take input from the user -EB
     */
    private static Scanner read = new Scanner(System.in);

    /*
    Main Method
     */
    public static void main(String[] args) {
        /*
        ArrayList for Lots is created so that there is data to use -EB
         */
        ArrayList<Lot> aLot = new ArrayList<>();

        /*
        Sample Lot is created and added to the ArrayList and then the Ledger -EB
         */
        Lot obLot1 = new Lot();
        aLot.add(obLot1);
        BookingLedger.setLotList(aLot);

        /*
        Creating a Customer to add to the Reservation using an ArrayList -EB
         */
        Customer obCustomer1 = new Customer(0, "John Smith", "223 Real St", "Johnsmith@hotmail.com",
                1233211234, 1233322221, 1399587473, 0, false, 0);
        ArrayList<Customer> obCustArray = new ArrayList<>();
        obCustArray.add(obCustomer1);

        /*
        Creating a Reservation and Reservation ArrayList to add to the Ledger for data -EB
         */
        Date startDate = new GregorianCalendar(2020, Calendar.JANUARY, 14).getTime();
        Date endDate = new GregorianCalendar(2020, Calendar.JANUARY, 17).getTime();
        Reservation obReservation = new Reservation(obLot1, startDate, endDate, obCustArray, 1);
        ArrayList<Reservation> aReservations = new ArrayList<>();
        aReservations.add(obReservation);
        BookingLedger.setReservationsList(aReservations);

        boolean quit = false;

        /*
        The main loop of the program, containing all paths and programs that are to be run by the program. -EB
         */
        do {


            /*
            System asks which section of the program the user wants to go to. User responds with one of the letters
            and the program takes them to that section using switch cases. -EB
             */
            System.out.print("Actions: [L]ots, [C]ustomers, [T]ransactions,  [R]eservations, [Q]uit: ");
            switch (read.nextLine().toUpperCase()) {
                case "L":
                    System.out.print("Actions: [S]earch for a lot: ");
                    switch (read.nextLine().toUpperCase()) {
                        case "S":
                            String lotID = Prompt("Please enter a Valid Lot ID to search for: ");

                            int nLotID = Integer.parseInt(lotID);

                            BookingLedger.displayLot(nLotID);
                            Lot obLot;

                            try {
                                obLot = BookingLedger.querySearchCampsite(nLotID);
                            } catch (NullPointerException e) {
                                break;
                            }


                            System.out.print("Actions: [S]et Lot availability, [V]iew Reservations: ");
                            switch (read.nextLine().toUpperCase()) {
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
                                    for (Reservation rReservation : BookingLedger.getReservations(nLotID)) {
                                        System.out.println(rReservation.toString());
                                    }
                                    break;

                                default:
                                    break;

                            }

                        default:
                            break;


                    }
                    break;
                case "C":
                    System.out.print("Actions:[C]ustomer: ");
                    switch (read.nextLine().toUpperCase()) {
                        case "C":

                            break;
                    }
                    break;

                case "R":

                    System.out.print("Actions: [RR]emove Reservation");
                    switch (read.nextLine().toUpperCase()) {
                        case "RR":
                            try {
                                int searchID = Integer.parseInt(Prompt("List a reservation ID to Delete."));
                                BookingLedger.removeReservation(searchID);
                            } catch (Exception x) {
                                System.out.println("Please enter a number ID");
                            }
                            ;
                            break;
                        default:
                            break;
                    }

                    break;
                case "T":
                    System.out.println("Please Enter [G]Get Reservation, [PT]SetPayment Type, [PM]Set Payment Method, " +
                            " [S]setStatus and [C]Check for Price");
                        String choice = read.nextLine();
                        switch(choice)
                        {
                            case "G":
                                promptReservation();
                                break;
                            case "PT":
                                setPayType();
                                break;
                            case "PM":
                                setPayMethod();
                                break;
                            case "S":
                                setStatus();
                                break;
                            case "C":
                                checkPrice();
                                break;
                            default:
                                break;
                        }
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
    private static void inputSomething() {
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

    /**
     * method helper: to set the payment type
     * @param - RaeAnne Hamel CST212
     */
    public static void setPayType()
    {
        // prompt user for a specific reservation
       Reservation obRes =  promptReservation();

       //prompt for a Payment type
       System.out.println("Choose a payment Type: [CA]Cash, [DB] debit and [CC]CreditCard");

       // set the variable
       String input1 = read.nextLine();

       //the variable to set the paytype
       PaymentType payType;

       //sets the actual type
        switch(input1)
        {
            case "CA":
               payType = PaymentType.CASH;
               break;
            case "DB":
                payType = PaymentType.DEBIT;
                break;
            case "CC":
                payType = PaymentType.CREDITCARD;
                break;
            default:
                payType = PaymentType.CREDITCARD;
                break;
        }

        // builds transaction
        Transaction obTransaction = new Transaction(obRes, payType, PaymentMethod.INPERSON);

        //prints out the information
        System.out.printf("%s", obTransaction);
    }


    /**
     * prompts user for a Id number to call a reservation
     * @return reservation - RaeAnne Hamel CST212
     *
     */
    public static Reservation promptReservation()
    {
        System.out.println("Enter Reservation ID number:");
        int resId = read.nextInt();
        return BookingsLedger.getReservation(BookingLedger.getAllReservations(), resId);
    }

    /**
     * sets payment method - RaeAnne Hamel CST212
     */
    public static void setPayMethod()
    {
        // prompt user for a specific reservation
        Reservation obRes =  promptReservation();

        //prompt for a Payment type
        System.out.println("Choose a payment Type: [IN]inPerson, [FX] Fax, [EM]Email and [PH]Phone");

        // set the variable
        String input2 = read.nextLine();

        //the variable to set the paytype
        PaymentMethod payMeth;

        //set payment method
        switch (input2)
        {
            case "IN":
                payMeth = PaymentMethod.INPERSON;
                break;
            case "FX":
                payMeth = PaymentMethod.FAX;
                break;
            case "EM":
                payMeth = PaymentMethod.EMAIL;
                break;
            case "PH":
                payMeth = PaymentMethod.PHONE;
            default:
                payMeth = PaymentMethod.INPERSON;
                break;
        }

        //builds transaction object
        Transaction obTransaction = new Transaction(obRes, PaymentType.CREDITCARD, payMeth);

        //prints out the information
        System.out.printf("%s", obTransaction);
    }

    /**
     * helper method: sets status of a reservation through transaction
     * RaeAnne Hamel CST212
     *
     */
    public static void setStatus()
    {
        //prompts to get an id
        Reservation obRes = promptReservation();

        //set a Transaction
        Transaction obTrans = new Transaction(obRes, PaymentType.CREDITCARD, PaymentMethod.INPERSON);

        //set status
        obTrans.setStatus();

        //Prints information
        System.out.printf("%s", obTrans);

    }

    /**
     * helper method: check for the price in a Reservation
     * RaeAnne Hamel
     */
    public static void checkPrice()
    {
        //prompt for reservation
        Reservation obRes = promptReservation();

        //build transaction object
        Transaction obTrans = new Transaction(obRes, PaymentType.CREDITCARD, PaymentMethod.INPERSON);

        //get the price
        obTrans.getPrice();

        //print out information
        System.out.printf("%s", obTrans);

    }

}


