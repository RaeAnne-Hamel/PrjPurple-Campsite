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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AddReservationGui extends Application {



    //Access to specific array list

    public ArrayList<Customer> custNameList = new ArrayList<>();
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

    //variables for dates
    int year = Integer.parseInt(txtArrivalYear.getText());
    int month = Integer.parseInt(txtDepartMonth.getText());
    int day = Integer.parseInt(txtDepartDay.getText());

    int year2 =  Integer.parseInt(txtDepartYear.getText());
    int month2 = Integer.parseInt(txtDepartMonth.getText());
    int day2 = Integer.parseInt(txtDepartDay.getText());

    @Override
    public void start(Stage obStage) {

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


        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(430);
        obHBox.setPadding(new Insets(15,10,10,10));
        obHBox.getChildren().addAll(btnBack, btnConfirm);

        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);

     //EVENT HANDLERS

        //set the label to return the lot type that match the id
        txtLotID.setOnKeyReleased(e-> {
            lblLotType.setText(MainGui.obBookingLedger.querySearchCampsite(Integer.parseInt(txtLotID.getText())).toString());
        });

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
            ArrayList<Customer> obRet = MainGui.obBookingLedger.getCustomerList();

            //Loop through the customer list until you match customer names
            for (Customer obCust : obRet) {
                //Get that customer object back if the names match
                if (obCust.getName().equals(custName))
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
            ArrayList<Customer> obRet = MainGui.obBookingLedger.getCustomerList();

            //Loop through the customer list until you match customer names
            for (Customer obCust : obRet) {
                //Get that customer object back if the names match
                if (obCust.getName().equals(custName))
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
            int lotID = Integer.parseInt(txtLotID.getText());
            //Entered amount of guests
            int guest = Integer.parseInt(txtGuest.getText());

            //Streams the list of lots
            List<Lot> filteredLots = MainGui.obBookingLedger.aLot.stream()
                    .filter(x -> x.getLotID() == lotID)
                    .collect(Collectors.toList());

            //gets the lost type
            Lot obLot = filteredLots.get(Integer.parseInt(txtLotID.getText()));

                    if (filteredLots.size() == 0) {
                        lblValid2.setText("LotId not found");
                    }else if(lotID > filteredLots.size()){
                        lblValid2.setText("LotId not found");
                    }else{
                        lblValid2.setText("");
                    }
    });


        //validation for Arrival dates
        txtArrivalDay.setOnKeyReleased(e->{
            int year = Integer.parseInt(txtArrivalYear.getText());
            int month = Integer.parseInt(txtDepartMonth.getText());
            int day = Integer.parseInt(txtDepartDay.getText());

            int year2 =  Integer.parseInt(txtDepartYear.getText());
            int month2 = Integer.parseInt(txtDepartMonth.getText());
            int day2 = Integer.parseInt(txtDepartDay.getText());
            //current
            Date obToday = new Date();

            Date obYearAfter = new Date();

            //Arrival date
            Date obEnteredArrival = new Date(year, month, day);

            //Departure date
            Date obEnteredDepart = new Date(year2, month2, day2);


            obYearAfter.setYear(obYearAfter.getYear() + 1);

            if (obEnteredArrival.compareTo(obToday) < 0)
            {
                lblValid3.setText("Invalid Date: In Past.");
            }else if(obEnteredArrival.compareTo(obYearAfter) > 0)
            {
                lblValid3.setText("Invalid: too far in future.");
            }else if(obEnteredArrival.compareTo(obEnteredDepart) > 0){
                lblValid3.setText("Invalid Dates");
            }else{
                lblValid3.setText("");
            }

        });


        //validation for departure date
        txtDepartDay.setOnKeyReleased(e-> {
            int year = Integer.parseInt(txtArrivalYear.getText());
            int month = Integer.parseInt(txtDepartMonth.getText());
            int day = Integer.parseInt(txtDepartDay.getText());

            int year2 =  Integer.parseInt(txtDepartYear.getText());
            int month2 = Integer.parseInt(txtDepartMonth.getText());
            int day2 = Integer.parseInt(txtDepartDay.getText());
            //current
            Date obToday = new Date();

            Date obYearAfter = new Date();

            //Arrival date
            Date obEnteredArrival = new Date(year, month, day);

            //Departure date
            Date obEnteredDepart = new Date(year2, month2, day2);


            obYearAfter.setYear(obYearAfter.getYear() + 1);

            if (obEnteredDepart.compareTo(obToday) < 0) {
                lblValid4.setText("Invalid Date: In Past.");
            }else if(obEnteredDepart.compareTo(obEnteredArrival) > 0){
                lblValid4.setText("Invalid Dates");
            }else{
                lblValid4.setText("");
            }

        });

        //Guest Validation
        txtGuest.setOnKeyReleased(e -> {
           // lot ID
           int lotID = Integer.parseInt(txtLotID.getText());
           //Entered amount of guests
           int guest = Integer.parseInt(txtGuest.getText());

           //Streams the list of lots
            List<Lot> filteredLots = MainGui.obBookingLedger.aLot.stream()
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
        });

        //add the reservation
        btnConfirm.setOnAction(e -> {
            Date obEnteredArrival = new Date(year, month, day);
            Date obEnteredDepart = new Date(year2, month2, day2);
            int lotID = Integer.parseInt(txtLotID.getText());
            int guest = Integer.parseInt(txtGuest.getText());


            MainGui.obBookingLedger.addReservation(lotID, obEnteredArrival, obEnteredDepart, custCount, guest);
        });

        btnBack.setOnAction(e-> {
            //obStage.setScene(MainGui.mainScene);
        });


        obStage.setScene(new Scene(obBorder, 650, 500));
        obStage.setTitle("Add Reservation");
        obStage.show();


    }


    /**
     * this method will take an array list and grab all the name from it and convert it to a string array for the combo
     * boxes
     * @returns  String[]
     */
    private ArrayList<String> getCustNameList()
    {
        ArrayList<Customer> custList = MainGui.obBookingLedger.getCustomerList();
        ArrayList<String> names = new ArrayList<>();

        for(Customer obCust : custList)
        {
           names.add(obCust.getName());
        }

        return names;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
