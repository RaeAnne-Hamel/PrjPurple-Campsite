package campground_ui;

import campground_data.BookingsLedger;
import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class AddReservationGui extends Stage {



    //Access to specific array list
    private ArrayList<Customer> custCount = new ArrayList<>();

    //panes
    private GridPane obGrid;
    private BorderPane obBorder;
    private HBox obHBox;

    //Labels
    private Label lblCustomers, lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID, lblResID2, lblValid1,
            lblValid2, lblValid3,lblValid4, lblValid5;

    //Textfield
    private TextField txtArrivalYear, txtArrivalDay, txtArrivalMonth, txtDepartDay, txtDepartMonth, txtDepartYear,
                        txtGuest, txtLotID;

    //comboBoxes
    private ComboBox<String> cboCust1, cboCust2;

    //button
    Button btnBack, btnConfirm;


    public AddReservationGui(Stage obStage){


        //Creating panes
        obGrid = new GridPane();
        obBorder = new BorderPane();
        obHBox = new HBox();

        //Creating Labels
        lblResID = new Label("Reservation Id: ");
        lblResID2 = new Label("");
        lblCustomers = new Label("Customer Names: ");
        lblLotID = new Label("Lot ID:");
        lblLotType = new Label("");
        lblArrival = new Label("Arrival Date (yyyy/mm/dd): ");
        lblDeparture = new Label("Departure Date (yyyy/mm/dd): ");
        lblGuest = new Label("Number of Guests: ");
        lblValid1 = new Label("");
        lblValid2 = new Label("");
        lblValid3 = new Label("");
        lblValid4 = new Label("");
        lblValid5 = new Label("");


        //Creating text fields
        txtLotID = new TextField();
        txtLotID.setMaxSize(70, 50);



        txtArrivalYear = new TextField();
        txtArrivalYear.setMaxSize(70,50);
        txtArrivalMonth = new TextField();
        txtArrivalMonth.setMaxSize(70, 50);
        txtArrivalDay = new TextField();
        txtArrivalDay.setMaxSize(50, 50);
        txtDepartYear = new TextField();
        txtDepartYear.setMaxSize(70, 50);
        txtDepartMonth = new TextField();
        txtDepartMonth.setMaxSize(70, 50);
        txtDepartDay = new TextField();
        txtDepartDay.setMaxSize(50, 50);
        txtGuest = new TextField();
        txtGuest.setMaxSize(70, 50);

        //creating combo boxes
        cboCust1 = new ComboBox<>();
        cboCust1.setMinSize(150,10);
        cboCust2 = new ComboBox<>();
        cboCust2.setMinSize(150,10);

        //creating Buttons
        btnBack = new Button("Back to Reservation");
        btnConfirm = new Button("Confirm");

        //Setting spacing and alignment
        obGrid.setVgap(5);
        obGrid.setHgap(5);
        obGrid.setAlignment(Pos.CENTER);

        //make the Grid
        obGrid.add(lblResID, 0, 0);
        obGrid.add(lblResID2, 1, 0);

        obGrid.add(lblCustomers, 0, 2);
        obGrid.add(cboCust1, 1, 2);
        obGrid.add(cboCust2, 2, 2);

        obGrid.add(lblValid1, 1,3);

        obGrid.add(lblLotID, 0,4);
        obGrid.add(txtLotID, 1, 4);
        obGrid.add(lblLotType, 2, 4);

        obGrid.add(lblValid2, 1, 5);

        obGrid.add(lblArrival, 0, 6);
        obGrid.add(txtArrivalYear, 1,6);
        obGrid.add(txtArrivalMonth, 2, 6);
        obGrid.add(txtArrivalDay, 3,6);

        obGrid.add(lblValid3, 1, 7);

        obGrid.add(lblDeparture, 0, 8);
        obGrid.add(txtDepartYear, 1,8);
        obGrid.add(txtDepartMonth, 2,8);
        obGrid.add(txtDepartDay, 3, 8);

        obGrid.add(lblValid4, 1, 9);

        obGrid.add(lblGuest, 0, 10);
        obGrid.add(txtGuest,1,10);

        obGrid.add(lblValid5, 1, 11);

        obGrid.setPadding(new Insets(5,5,5,5));

        //positioning the buttons
        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(430);
        obHBox.setPadding(new Insets(15,10,10,10));
        obHBox.getChildren().addAll(btnBack, btnConfirm);

        //positioning the layout
        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);

     //EVENT HANDLERS

    //populates the combo boxes
    //set the list
    ObservableList<String> names = FXCollections.observableList(this.getCustNameList());
    //populates first Combo box
    cboCust1.setItems(names);
    //populates second Combo box
    cboCust2.setItems(names);



    //this add the first customer object to an new ArrayList for validation
    cboCust1.setOnAction(e-> {
        String custName = cboCust1.getValue();
        //Find the full customer object from:
        ArrayList<Customer> obRet = MainGui.obBookingsLedger.getCustomerList();

        //Loop through the customer list until you match customer names
        for (Customer obCust : obRet) {
            String checkName = obCust.getName() + " " + obCust.getLast();
            //Get that customer object back if the names match
            if (checkName.equals(custName))
            {
                //Add it to your reservation array list
                custCount.add(obCust);
            }
        }

        //validation fot duplicate customer names
        if(custName.equalsIgnoreCase(cboCust2.getValue()))
        {
            lblValid1.setText("Invalid: Duplicate Names.");
        }
        else
        {
           lblValid1.setText("");
        }


    });

    //this add the Second customer object to an new ArrayList for validation
    cboCust2.setOnAction(e-> {
        String custName = cboCust2.getValue();
        //Find the full customer object from:
        ArrayList<Customer> obRet = MainGui.obBookingsLedger.getCustomerList();

        //Loop through the customer list until you match customer names
        for (Customer obCust : obRet) {
            String checkName = obCust.getName() + " " + obCust.getLast();
            //Get that customer object back if the names match
            if (checkName.equals(custName))
            {
                //Add it to your reservation array list
                custCount.add(obCust);
            }
        }

        //validation fot duplicate customer names
        if(custName.equalsIgnoreCase(cboCust1.getValue()))
        {
            lblValid1.setText("Invalid: Duplicate Names.");
        }
        else
        {
            lblValid1.setText("");
        }
    });


    //Validation for Lot ID
    txtLotID.setOnKeyReleased(e-> {
        // lot ID
        if(!txtLotID.getText().isEmpty()) {
            int lotID = Integer.parseInt(txtLotID.getText());

            //Streams the list of lots
            List<Lot> filteredLots = MainGui.obBookingsLedger.aLot.stream()
                    .filter(x -> x.getLotID() == lotID)
                    .collect(Collectors.toList());
            //validation
            if (filteredLots.size() == 0) {
                lblValid2.setText("LotId not found");
            } else if (lotID > filteredLots.size()) {
                lblValid2.setText("LotId not found");
            } else {
                lblValid2.setText("");
            }

            //sets the label to display the lot type
            lblLotType.setText(MainGui.obBookingsLedger.querySearchCampsite(Integer.parseInt(txtLotID.getText())).getLotType().toString());

        }

    });


    //validation for Arrival dates
    txtArrivalDay.setOnKeyReleased(e->{
        //validation -- won't work unless all fields for dates are populated
        if(!emptyfield(txtArrivalYear, txtArrivalMonth, txtArrivalDay, txtDepartYear, txtDepartMonth, txtDepartYear))//if the is not empty then
        {
            int year = Integer.parseInt(txtArrivalYear.getText());
            int month = Integer.parseInt(txtDepartMonth.getText());
            int day = Integer.parseInt(txtDepartDay.getText());

            int year2 = Integer.parseInt(txtDepartYear.getText());
            int month2 = Integer.parseInt(txtDepartMonth.getText());
            int day2 = Integer.parseInt(txtDepartDay.getText());

            //current
            Date obToday = new Date();

            Date obYearAfter = new Date();

            //Arrival date
            Date obEnteredArrival = new GregorianCalendar(year, month + 1, day).getTime();

            //Departure date
            Date obEnteredDepart = new GregorianCalendar(year2, month2 + 1, day2).getTime();

            //the following year
            obYearAfter.setYear(obYearAfter.getYear() + 1);

            //validation
            if (obEnteredArrival.compareTo(obToday) < 0) {
                lblValid3.setText("Invalid Date: In Past.");
            } else if (obEnteredArrival.compareTo(obYearAfter) > 0) {
                lblValid3.setText("Invalid: too far in future.");
            } else if (obEnteredArrival.compareTo(obEnteredDepart) > 0) {
                lblValid3.setText("Invalid Dates");
            } else {
                lblValid3.setText("");
            }
        }
    });


    //validation for departure date
    txtDepartDay.setOnKeyReleased(e-> {

        //validation -- won't work unless all fields for dates are populated
        if(!emptyfield(txtArrivalYear, txtArrivalMonth, txtArrivalDay, txtDepartYear, txtDepartMonth, txtDepartYear)) //if the is not empty then
        {
            int year = Integer.parseInt(txtArrivalYear.getText());
            int month = Integer.parseInt(txtDepartMonth.getText());
            int day = Integer.parseInt(txtDepartDay.getText());

            int year2 = Integer.parseInt(txtDepartYear.getText());
            int month2 = Integer.parseInt(txtDepartMonth.getText());
            int day2 = Integer.parseInt(txtDepartDay.getText());

            //current
            Date obToday = new Date();

            //following year
            Date obYearAfter = new Date();

            //Arrival date
            Date obEnteredArrival = getDateValue(year, month, day);

            //Departure date
            Date obEnteredDepart = getDateValue(year2, month2, day2);


            obYearAfter.setYear(obYearAfter.getYear() + 1);

            //validation
            if (obEnteredDepart.compareTo(obToday) < 0) {
                lblValid4.setText("Invalid Date: In Past.");
            } else if (obEnteredDepart.compareTo(obEnteredArrival) > 0) {
                lblValid4.setText("Invalid Dates");
            } else {
                lblValid4.setText("");
            }
        }
    });


    //Guest Validation
    txtGuest.setOnKeyReleased(e -> {
       // lot ID
        if(!txtLotID.getText().isEmpty()) {
            int lotID = Integer.parseInt(txtLotID.getText());
            //Entered amount of guests
            int guest = Integer.parseInt(txtGuest.getText());

            //Streams the list of lots
            List<Lot> filteredLots = MainGui.obBookingsLedger.aLot.stream()
                    .filter(x -> x.getLotID() == lotID)
                    .collect(Collectors.toList());

            //gets the lost type
            Lot obLot = filteredLots.get(Integer.parseInt(txtLotID.getText()));

            //switch case for the amount of people that can stay on a lot
            switch (obLot.getLotType()) {
                case Cabin:
                case DeluxeCabin:
                    break;

                case NonServicedIndividual:
                case ServicedIndividual:
                    if (guest > 4) {
                        lblValid5.setText("Overcapacity.");
                    } else {
                        lblValid5.setText("");
                    }
                    break;

                case NonServicedGroup:
                case ServicedGroup:
                    if (guest > 8) {
                        lblValid5.setText("Overcapacity.");
                    } else {
                        lblValid5.setText("");
                    }
                    break;

                default:
                    lblValid5.setText("");
                    break;
            }
        }
    });


    //add the reservation
    btnConfirm.setOnAction(e -> {
        //set variables
        int year = Integer.parseInt(txtArrivalYear.getText());
        int month = Integer.parseInt(txtArrivalMonth.getText());
        int day = Integer.parseInt(txtArrivalDay.getText());

        int year2 =  Integer.parseInt(txtDepartYear.getText());
        int month2 = Integer.parseInt(txtDepartMonth.getText());
        int day2 = Integer.parseInt(txtDepartDay.getText());

        Date obEnteredArrival = getDateValue(year, month, day);
        Date obEnteredDepart = getDateValue(year2, month2, day2);

        int lotID = Integer.parseInt(txtLotID.getText());
        int guest = Integer.parseInt(txtGuest.getText());


        //checks for reservation overlap
        if (MainGui.obBookingsLedger.checkOverlap(lotID, obEnteredArrival, obEnteredDepart))
        {
            //sends an if there is an overlap
            Alert alert = new Alert(Alert.AlertType.ERROR,"Overlaps with existing reservation.", ButtonType.CLOSE);

            alert.showAndWait();

            //result of button pressed
            if(alert.getResult() == ButtonType.CLOSE)
            {
                //closes the alert window
                alert.close();
            }
        }else{

            //add the reservation
            MainGui.obBookingsLedger.addReservation(lotID, obEnteredArrival, obEnteredDepart, custCount, guest);

            /**
             *
             */
            for(Reservation obRes: BookingsLedger.aReservation)
            {
                System.out.printf("%s", obRes);
            }
            /**
            *
            */


           int size = MainGui.obBookingsLedger.getAllReservations().size();

           int resID = MainGui.obBookingsLedger.getAllReservations().get(size -1).getReservationID();

           lblResID2.setText(Integer.toString(resID));

            //alert stating the reservation was created
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Reservation " + resID + " has been created. Closing to main reservation window", ButtonType.CLOSE);

            alert2.showAndWait();

            //result of button pressed
            if(alert2.getResult() == ButtonType.CLOSE)
            {
                //clear all the fields
                txtLotID.setText("");
                txtArrivalDay.setText("");
                txtArrivalMonth.setText("");
                txtArrivalYear.setText("");
                txtDepartYear.setText("");
                txtDepartMonth.setText("");
                txtDepartDay.setText("");
                txtGuest.setText("");
                lblLotType.setText("");
                lblResID2.setText("");

                //closes to main reservation window
                alert2.close();
                this.close();
            }
        }





        });

        //click back button
        btnBack.setOnAction(e-> {
            //close the window
            this.close();
        });


        Scene scene = new Scene(obBorder, 650, 500);
        obStage.setTitle("Add Reservation");
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);


    }


    /**
     * this method will take an array list and grab all the name from it and convert it to a string array for the combo
     * boxes
     * @returns  String[]
     */
    private ArrayList<String> getCustNameList()
    {
        //get the array list from BookingsLedger
        ArrayList<Customer> custList = MainGui.obBookingsLedger.aCustomer;

        //create a new ArrayList of String
        ArrayList<String> names = new ArrayList<>();

        //for each item in the ArrayList
        for(Customer obCust : custList)
        {
           //add the customer name
           names.add(obCust.getName() + " " + obCust.getLast());
        }

        //return the new ArrayList
        return names;
    }

    /**
     * sets the date values and check day validation
     * @param year
     * @param month
     * @param day
     * @return date
     */
    public Date getDateValue(int year, int month, int day)
    {
        //day validator
        if(!dateValidator(year, month, day))
        {
            lblValid3.setText("Invalid Day");
        }

        //sets the Date
        return new GregorianCalendar(year, month - 1, day).getTime();


    }

    /**
     * Method Helper: check for valid for every month, including leap years
     * @param year
     * @param month
     * @param day
     * @return true or false
     */
    public boolean dateValidator(int year, int month, int day)
    {

        //Months with 31 days
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            if(day < 1 || day > 31)
            {
              return false;
            }
        }
        //covers leap year
        else if(month == 2)
        {
            if(year % 4 == 0)
            {
                if(day < 1 || day > 29)
                {
                    return false;
                }
            }

            if(day > 28)
            {
                if(day < 1 || day > 28)
                {
                    return false;
                }
            }
        }
        else
        {
            //remaining months
            if(day < 1 || day > 30)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Method Helper: checks if a field is empty
     * @param txtBox
     * @param textBox2
     * @param textBox3
     * @param textBox4
     * @param textBox5
     * @param textBox6
     * @return true or false
     */
    public boolean emptyfield(TextField txtBox, TextField textBox2,TextField textBox3,TextField textBox4,
                              TextField textBox5, TextField textBox6)
    {

        //if textboxes are not empty
        if(!txtBox.getText().isEmpty() && !textBox2.getText().isEmpty() && !textBox3.getText().isEmpty() &&
                !textBox4.getText().isEmpty() && !textBox5.getText().isEmpty() &&!textBox6.getText().isEmpty()) // if the text is not empty
        {
            return false; // meaning there is items in it
        }
        return true; //it is empty
    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}
