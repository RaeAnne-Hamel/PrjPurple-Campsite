package campground_ui;

import campground_data.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
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
        The main loop of the program, containing all paths and programs that are to be run by the program. --EB
         */
        do {

            /*
            System asks which section of the program the user wants to go to. User responds with one of the letters
            and the program takes them to that section using switch cases. -EB
             */
            System.out.print("Actions: [L]ots, [C]ustomers, [R]eservations, [Q]uit: ");

            //Named switch for breaking functionality
            mainSwitch:
            switch (read.nextLine().toUpperCase()) {

                case "L":
                    System.out.print("Actions: [S]earch for a lot");
                    switch (read.nextLine().toUpperCase()) {
                        case "S":
                            String lotID = Prompt("Please enter a Valid Lot ID to search for: ");

                            int nLotID = Integer.parseInt(lotID);

                            BookingLedger.displayLot(nLotID);
                            Lot obLot;

                            try {
                                obLot = BookingLedger.querySearchCampsite(nLotID);
                            } catch (NullPointerException e) {
                                break mainSwitch;
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
                    }
                    //Break for "L"
                    break;

                //Reservations option
                case "R":
                    System.out.print("Actions: [A]dd Reservation, [RR]emove Reservation, [S]earch to make a new reservation");
                    switch (read.nextLine().toUpperCase()) {
                        case "RR":
                            try {
                                int searchID = Integer.parseInt(Prompt("List a reservation ID to Delete."));
                                BookingLedger.removeReservation(searchID);
                            } catch (Exception x) {
                                System.out.println("Please enter a number ID");
                            }
                            break;
                        case "S":
                            System.out.println("Actions: [G]et available reservations for specific dates");
                            LotType obType = null;
                            switch (read.nextLine().toUpperCase()) {
                                case "G":
                                    int nType = Integer.parseInt(Prompt("Pick your Lot Type. 1: Non-Serviced Individual Campsite, 2: Serviced Individual Campsite," +
                                            "3: Non Serviced Group Campsite, 4: Serviced Group Campsite, 5: Cabin, 6: Deluxe Cabin"));

                                    switch (nType){
                                        case 1:
                                            obType = LotType.NonServicedIndividual;
                                        case 2:
                                            obType = LotType.ServicedIndividual;
                                        case 3:
                                            obType = LotType.NonServicedGroup;
                                        case 4:
                                            obType = LotType.ServicedGroup;
                                        case 5:
                                            obType = LotType.Cabin;
                                        case 6:
                                            obType = LotType.DeluxeCabin;
                                        default:
                                            System.out.println("Please choose an available lot type.");
                                            break;
                                    }
                                    int nStartYear = Integer.parseInt(Prompt("Enter a 4 digit starting year (2020)"));
                                    int nStartMonth = Integer.parseInt(Prompt("Enter a starting month from 1 to 12"));
                                    int nStartDay = Integer.parseInt(Prompt("Enter a starting day of the month"));
                                    int nEndYear = Integer.parseInt(Prompt("Enter a 4 digit ending year (2020)"));
                                    int nEndMonth = Integer.parseInt(Prompt("Enter an ending month from 1 to 12"));
                                    int nEndDay = Integer.parseInt(Prompt("Enter the ending day of the month"));
                                    ArrayList<Lot> obLots = new NewLotSearch(BookingLedger).chooseDate(obType, nStartYear, nStartMonth, nStartDay, nEndYear, nEndMonth, nEndDay);
                                    for (Lot obLot : obLots)
                                    {
                                        System.out.println(obLot.toString());
                                    }
//                                case "S":
//                                    switch (nType){
//                                        case 1:
//                                            obType = LotType.NonServicedIndividual;
//                                        case 2:
//                                            obType = LotType.ServicedIndividual;
//                                        case 3:
//                                            obType = LotType.NonServicedGroup;
//                                        case 4:
//                                            obType = LotType.ServicedGroup;
//                                        case 5:
//                                            obType = LotType.Cabin;
//                                        case 6:
//                                            obType = LotType.DeluxeCabin;
//                                        default:
//                                            System.out.println("Non-standard type entered.");
//                                            break;
//                                    }
//                                    ArrayList<LocalDate> obDates= new NewLotSearch(BookingLedger).getDates(obType);
//                                    for (LocalDate LD : obDates)
//                                    {
//                                        System.out.println(LD.toString());
//                                    }
                                    break mainSwitch;
                                default:
                                    break mainSwitch;
                            }

                        //Start of Add reservation option --Andrew
                        case "A":
                            int nARLotID = Integer.parseInt(Prompt("Please enter a lot ID to select a lot"));
                            Date obARStartDate = PromptDate("Please Enter the date of arrival");
                            System.out.println();
                            Date obAREndDate = PromptDate("Please enter the date of departure");
                            int nARCustCount = Integer.parseInt(Prompt("Please enter if there will be 1 or 2 Customers"));
                            ArrayList<Customer> obARCustomers = new ArrayList<>();
                            //Switch statement handles how many customers are being added
                            switch (nARCustCount) {
                                case 2:
                                    obARCustomers.add(BookingLedger.getCustomerByID(
                                            Integer.parseInt(Prompt("Please enter customer ID"))
                                    ));
                                    if (obARCustomers.get(obARCustomers.size()-1) == null) {
                                        System.out.println("Customer not found");
                                        break mainSwitch;
                                    }

                                case 1:
                                    obARCustomers.add(BookingLedger.getCustomerByID(
                                            Integer.parseInt(Prompt("Please Enter customer ID"))
                                    ));
                                    if (obARCustomers.get(obARCustomers.size()-1) == null) {
                                        System.out.println("Customer not found");
                                        break mainSwitch;
                                    }
                                    break;
                            }
                            int nARPeople = Integer.parseInt(Prompt("Please enter how many people will be using the reservation"));
                            BookingLedger.addReservation(nARLotID, obARStartDate, obAREndDate, obARCustomers, nARPeople);
                            break;
                        default:
                            break;
                        //End of Add Reservation option --Andrew
                    }
                    //Break for case "R"
                    break;

                case "C":
                    System.out.print("Actions:[C]ustomer: ");
                    switch (read.nextLine().toUpperCase()) {
                        case "C":

                            break;
                    }
                    //Break for "C"
                    break;

                default:
                    quit = true;
                    break;
            }


            System.out.println("");
            System.out.println("");

        }
        while (!quit);
        System.out.println("Quit Application");
    }

            public static String Prompt (String Message)
            {
                System.out.println(Message);
                return read.nextLine().toUpperCase();
            }

            /**
             * This method will prompt the user with a message of your choice.
             * It will then prompt the user for Year, Month and Day and construct a Date class
             * The will be returned
             * @param Message
             * @return
             */
            public static Date PromptDate(String Message)
            {
                System.out.println(Message);
                System.out.println("Please enter year in the format \"2020\"");
                int nYear = Integer.parseInt(read.nextLine().toUpperCase());
                System.out.println("Please enter the month in the format \"5\"");
                int nMonth = Integer.parseInt(read.nextLine().toUpperCase());
                System.out.println("Please enter the day in the format \"27\"");
                int nDay = Integer.parseInt(read.nextLine().toUpperCase());

                Date obDate = new GregorianCalendar(nYear, nMonth, nDay).getTime();
                return obDate;
            }





}