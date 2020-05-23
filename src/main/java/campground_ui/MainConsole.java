package campground_ui;

import campground_data.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MainConsole {

    public static BookingsLedger BookingLedger = new BookingsLedger();
    //private static CampLedger campLedger = new CampLedger();
    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args)
    {
        boolean quit = false;
        do{

            System.out.print("Actions:[L]ots, [R]eservations, [Q]uit: ");

            //Named switch for breaking functionality
            mainSwitch: switch (read.nextLine().toUpperCase()) {
                case "L":



                    case "S":
                        String lotID = Prompt("Please enter a Valid Lot ID to search for");

                        int nLotID = Integer.parseInt(lotID);

                        BookingLedger.displayLot(nLotID);

                        Lot obLot = BookingLedger.querySearchCampsite(nLotID);

                        System.out.print("[S]et Lot availability");
                        switch (read.nextLine().toUpperCase())
                        {
                            case "S":
                            {
                                System.out.print("[A]vailable, [U]navailable");

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

                //Reservations option
                case "R":
                    switch (Prompt("[A]dd Reservation:")) {


                        //Start of Add reservation option --Andrew
                        case "A":
                            int nARLotID = Integer.parseInt(Prompt("Please enter a lot ID to select a lot"));
                            Date obARStartDate = PromptDate("Please Enter the date of arrival");
                            System.out.println();
                            Date obAREndDate = PromptDate("Please enter the date of departure");
                            int nARCustCount = Integer.parseInt(Prompt("Please enter if there will be 1 or 2 Customers"));
                            Customer[] obARCustomers = new Customer[nARCustCount];
                            //Switch statement handles how many customers are being added
                            switch (nARCustCount) {
                                case 2:
                                    obARCustomers[1] = BookingLedger.getCustomerByID(
                                            Integer.parseInt(Prompt("Please enter customer ID"))
                                    );
                                    if (obARCustomers[1] == null) {
                                        System.out.println("Customer not found");
                                        break mainSwitch;
                                    }

                                case 1:
                                    obARCustomers[0] = BookingLedger.getCustomerByID(
                                            Integer.parseInt(Prompt("Please Enter customer ID"))
                                    );
                                    if (obARCustomers[0] == null) {
                                        System.out.println("Customer not found");
                                        break mainSwitch;
                                    }
                                    break;
                            }
                            int nARPeople = Integer.parseInt(Prompt("Please enter how many people will be using the reservation"));
                            BookingLedger.addReservation(nARLotID, obARStartDate, obAREndDate, obARCustomers, nARPeople);
                            break;
                        //End of Add Reservation option --Andrew
                    }
                    break;

                case "RR":
                        try {
                            int searchID = Integer.parseInt(Prompt("List a reservation ID to Delete."));
                            BookingLedger.removeReservation(searchID);
                        } catch (Exception x){System.out.println("Please enter a number ID");};
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

        Date obDate = new GregorianCalendar(nYear,nMonth,nDay).getTime();
        return obDate;
    }


}

