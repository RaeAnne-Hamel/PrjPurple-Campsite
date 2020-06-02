package campground_ui;

import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import javafx.application.Application;
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

public class EditReservationGui extends Application {



    //Access to specific array list
    public ArrayList<Customer> obCustomer;
    public ArrayList<Reservation> obRes;
    public ArrayList<Lot> obLot;
    public ArrayList<Customer> custNameList = new ArrayList<>();

    //panes
    private GridPane obGrid;
    private BorderPane obBorder;
    private HBox obHBox;

    //Labels
    private Label lblCustomers, lblCust1, lblCust2,  lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID,
            lblResID2;

    //Textfield
    private TextField txtArrivalYear, txtArrivalDay, txtArrivalMonth, txtDepartDay, txtDepartMonth, txtDepartYear,
                        txtGuest, txtLotID;


    //button
    Button btnBack, btnConfirm;


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
        lblCust1 = new Label("");
        lblCust2 = new Label("");
        lblLotID = new Label("Lot ID:");
        lblLotType = new Label("");
        lblArrival = new Label("Arrival Date (yyyy/m/d): ");
        lblDeparture = new Label("Departure Date (yyyy/m/d): ");
        lblGuest = new Label("Number of Guests: ");

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
        obGrid.add(lblCust1, 1, 2);
        obGrid.add(lblCust2, 2, 2);

        obGrid.add(lblLotID, 0,3);
        obGrid.add(txtLotID, 1, 3);
        obGrid.add(lblLotType, 2, 3);

        obGrid.add(lblArrival, 0, 4);
        obGrid.add(txtArrivalYear, 1,4);
        obGrid.add(txtArrivalMonth, 2, 4);
        obGrid.add(txtArrivalDay, 3,4);

        obGrid.add(lblDeparture, 0, 5);
        obGrid.add(txtDepartYear, 1,5);
        obGrid.add(txtDepartMonth, 2,5);
        obGrid.add(txtDepartDay, 3, 5);

        obGrid.add(lblGuest, 0, 6);
        obGrid.add(txtGuest,1,6);

        obGrid.setPadding(new Insets(10,10,10,10));


        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(430);
        obHBox.setPadding(new Insets(15,10,10,10));
        obHBox.getChildren().addAll(btnBack, btnConfirm);

        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);









        obStage.setScene(new Scene(obBorder, 650, 500));
        obStage.setTitle("Edit Reservation");
        obStage.show();


    }







    public static void main(String[] args) {
        launch(args);
    }
}
