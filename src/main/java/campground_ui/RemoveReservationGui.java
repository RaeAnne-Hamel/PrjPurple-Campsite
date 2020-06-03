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



    public RemoveReservationGui(Stage obStage) {




        //Creating panes
        obGrid = new GridPane();
        obBorder = new BorderPane();
        obHBox = new HBox();

        //Creating Labels
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
        obGrid.add(lblDepartDate, 3, 5);

        obGrid.add(lblGuest, 0, 6);
        obGrid.add(lblGuest2,1,6);

        obGrid.setPadding(new Insets(10,10,10,10));


        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(430);
        obHBox.setPadding(new Insets(15,10,10,10));
        obHBox.getChildren().addAll(btnBack, btnConfirm);

        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);



        txtResID2.setOnKeyReleased(e ->{

            int resID = Integer.parseInt(txtResID2.getText());

           // Reservation obRes = MainGui.obBookingLedger.getReservation(MainGui.obBookingLedger.aReservation, resID);

            //Reservation obRes = BookingsLedger.getReservation(MainGui.obBookingLedger.getAllReservations(), resID);

           Reservation obRes = MainGui.obBookingLedger.NonStaticgetReservation(MainGui.obBookingLedger.getAllReservations(), resID);

            String lotID = Integer.toString(obRes.getID());
            String guest = Integer.toString(obRes.getCustomerCount());
            DateFormat stDate = new SimpleDateFormat("yyyy/MM/dd");
            String startDate = stDate.format(obRes.getObStartDate());
            DateFormat endDate = new SimpleDateFormat("yyyy/MM/dd");
            String departDate = endDate.format(obRes.getObEndDate());


           // Customer name1 = obRes.getCustomerList().get(0);
           /// lblCust1.setText(name1.getName());

            lblLotID2.setText(lotID);
            lblLotType.setText(MainGui.obBookingLedger.querySearchCampsite((obRes.getID())).toString());

            lblArrivalDate.setText(startDate);
            lblDepartDate.setText(departDate);

            lblGuest2.setText(guest);


        });

        btnConfirm.setOnAction(e -> {
            int resID = Integer.parseInt(txtResID2.getText());
            MainGui.obBookingLedger.removeReservation(resID);
        });

        btnBack.setOnAction(e ->{
            //obStage.setScene(MainGui.mainScene);
        });

        obStage.setScene(new Scene(obBorder, 650, 500));
        obStage.setTitle("Remove Reservation");
        obStage.show();


    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}
