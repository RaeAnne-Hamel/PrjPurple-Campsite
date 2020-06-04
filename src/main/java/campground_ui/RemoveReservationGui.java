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

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import campground_ui.MainGui;

public class RemoveReservationGui extends Stage {

    //panes
    private GridPane obGrid;
    private BorderPane obBorder;
    private HBox obHBox;

    //Labels
    private Label lblCustomers, lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID, lblLotID2 , lblArrivalDate,
     lblDepartDate, lblGuest2, lblCust1, lblCust2;;

    //Textfield
    private TextField  txtResID2;

    //button
    Button btnBack, btnConfirm;
    Reservation obRes;


    public RemoveReservationGui(Stage obStage) {

        //Creating panes
        obGrid = new GridPane();
        obBorder = new BorderPane();
        obHBox = new HBox();

        //Creating Labels and text field
        lblResID = new Label("Reservation Id: ");
        lblLotID2 = new Label("");
        lblCustomers = new Label("Customer Names: ");
        lblLotID = new Label("Lot ID:");
        lblLotType = new Label("");
        lblArrival = new Label("Arrival Date (yyyy/m/d): ");
        lblDeparture = new Label("Departure Date (yyyy/m/d): ");
        lblGuest = new Label("Number of Guests: ");

        txtResID2= new TextField();
        txtResID2.setMaxSize(70, 50);

        lblArrivalDate = new Label("");
        lblDepartDate = new Label("");
        lblDepartDate.setMaxSize(70, 50);
        lblGuest2 = new Label("");
        lblGuest2.setMaxSize(70, 50);

        //creating combo boxes
        lblCust1 = new Label("");
        lblCust1.setMinSize(150,10);
        lblCust2 = new Label("");
        lblCust2.setMinSize(150,10);

        //creating Buttons
        btnBack = new Button("Back to Reservation");
        btnConfirm = new Button("Confirm");

        //Setting spacing and alignment
        obGrid.setVgap(5);
        obGrid.setHgap(5);
        obGrid.setAlignment(Pos.CENTER);

        //make the Grid
        obGrid.add(lblResID, 0, 0);
        obGrid.add(txtResID2, 1, 0);

        obGrid.add(lblCustomers, 0, 2);
        obGrid.add(lblCust1, 1, 2);
        obGrid.add(lblCust2, 2, 2);


        obGrid.add(lblLotID, 0,3);
        obGrid.add(lblLotID2, 1, 3);
        obGrid.add(lblLotType, 2, 3);

        obGrid.add(lblArrival, 0, 4);
        obGrid.add(lblArrivalDate, 1,4);


        obGrid.add(lblDeparture, 0, 5);
        obGrid.add(lblDepartDate, 1, 5);

        obGrid.add(lblGuest, 0, 6);
        obGrid.add(lblGuest2,1,6);

        obGrid.setPadding(new Insets(10,10,10,10));

        //set button positions
        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(430);
        obHBox.setPadding(new Insets(15,10,10,10));
        obHBox.getChildren().addAll(btnBack, btnConfirm);

        //setting layout positions
        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);

        //EVENT HANDLERS

        //when the key is released the reservation information will be displayed
        txtResID2.setOnKeyReleased(e ->{

            //gets the reservation ID
            int resID = Integer.parseInt(txtResID2.getText());

           // Reservation obRes = MainGui.obBookingLedger.getReservation(MainGui.obBookingLedger.aReservation, resID);

            //Reservation obRes = BookingsLedger.getReservation(MainGui.obBookingLedger.getAllReservations(), resID);
           // obRes = MainGui.obBookingsLedger.getReservation(MainGui.obBookingsLedger.getAllReservations(), resID);
            obRes = MainGui.obBookingsLedger.getAllReservations().get(resID-1);
            System.out.println();
            System.out.printf("%s", obRes);

            //display Lot ID and  number of Guest
            String lotID = Integer.toString(obRes.getID());
            String guest = Integer.toString(obRes.getCustomerCount());

            //formatting dates to display the date
            DateFormat stDate = new SimpleDateFormat("yyyy/MM/dd");
            String startDate = stDate.format(obRes.getObStartDate());
            DateFormat endDate = new SimpleDateFormat("yyyy/MM/dd");
            String departDate = endDate.format(obRes.getObEndDate());

            //Displays the customers paying names
           Customer name1 = obRes.getCustomerList().get(0);
           lblCust1.setText(name1.getName() + " " + name1.getLast());

           Customer name2 = obRes.getCustomerList().get(1);
           lblCust2.setText(name2.getName() + " " + name2.getLast());

           //displays Lot information
            lblLotID2.setText(lotID);
            lblLotType.setText(MainGui.obBookingsLedger.querySearchCampsite((obRes.getID())).getLotType().toString());

            //display dates
            lblArrivalDate.setText(startDate);
            lblDepartDate.setText(departDate);

            //displays number of guest
            lblGuest2.setText(guest);


        });

        //when button is clicked
        btnConfirm.setOnAction(e -> {
            //gets the value
            int resID = Integer.parseInt(txtResID2.getText());

           //calls the boolean for remove reservation -- if true
            if (MainGui.obBookingsLedger.removeReservation(resID))
            {
                //double confirmation
                Alert alert =  new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this reservation?", ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK)
                {
                    //removes the reservation
                    MainGui.obBookingsLedger.getAllReservations().removeIf(obItem -> resID == obItem.getReservationID());


                    //confirmsthe reservation is removed
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "Reservation removed.", ButtonType.CLOSE);
                    alert3.showAndWait();

                    //on button clicked
                    if(alert3.getResult() == ButtonType.CLOSE) {
                        //clears all the fields
                        txtResID2.setText("");
                        lblLotID2.setText("");
                        lblCust1.setText("");
                        lblCust2.setText("");
                        lblGuest2.setText("");
                        lblDepartDate.setText("");
                        lblArrivalDate.setText("");
                        lblLotType.setText("");

                        //closes everything to main reservation window
                        alert3.close();
                        this.close();


                    }
                    //closes everything to main reservation window
                    alert.close();
                    this.close();

                }else
                {
                    if(alert.getResult() == ButtonType.CANCEL)
                    {
                        //reservation was not remove, closes to main reservation window
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Reservation was not removed. Go back to main reservation screen", ButtonType.CLOSE);
                        alert2.showAndWait();
                        //on button clickes
                        if(alert2.getResult() == ButtonType.CLOSE)
                        {
                            //clears all fields
                            lblLotID2.setText("");
                            lblCust1.setText("");
                            lblCust2.setText("");
                            lblGuest2.setText("");
                            lblDepartDate.setText("");
                            lblArrivalDate.setText("");

                            //closes everything to main reservation window
                            this.close();
                            alert2.close();
                        }
                    }
                }
            };
        });

        btnBack.setOnAction(e ->{
            this.close();
        });

        Scene scene = new Scene(obBorder, 650, 500);
        obStage.setTitle("Remove Reservation");
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);


    }



    public static void main(String[] args) {
       Application.launch(args);
    }
}
