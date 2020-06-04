package campground_ui;

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

public class RemoveReservationGui extends Application {



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
    private Label lblCustomers, lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID, lblLotID2 , lblArrivalYear,
            lblArrivalDay, lblArrivalMonth, lblDepartDay, lblDepartMonth, lblDepartYear, lblGuest2, lblCust1, lblCust2;;

    //Textfield
    private TextField  txtResID2;



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
        lblLotID2 = new Label("");
        lblCustomers = new Label("Customer Names: ");
        lblLotID = new Label("Lot ID:");
        lblLotType = new Label("");
        lblArrival = new Label("Arrival Date (yyyy/m/d): ");
        lblDeparture = new Label("Departure Date (yyyy/m/d): ");
        lblGuest = new Label("Number of Guests: ");


        txtResID2= new TextField();
        txtResID2.setMaxSize(70, 50);



        lblArrivalYear = new Label("");
        lblArrivalMonth = new Label("");
        lblArrivalDay = new Label("");
        lblDepartYear = new Label("");
        lblDepartMonth = new Label("");
        lblDepartDay = new Label("");
        lblDepartDay.setMaxSize(50, 50);
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
        obGrid.add(lblArrivalYear, 1,4);
        obGrid.add(lblArrivalMonth, 2, 4);
        obGrid.add(lblArrivalDay, 3,4);

        obGrid.add(lblDeparture, 0, 5);
        obGrid.add(lblDepartYear, 1,5);
        obGrid.add(lblDepartMonth, 2,5);
        obGrid.add(lblDepartDay, 3, 5);

        obGrid.add(lblGuest, 0, 6);
        obGrid.add(lblGuest2,1,6);

        obGrid.setPadding(new Insets(10,10,10,10));


        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(430);
        obHBox.setPadding(new Insets(15,10,10,10));
        obHBox.getChildren().addAll(btnBack, btnConfirm);

        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);









        obStage.setScene(new Scene(obBorder, 650, 500));
        obStage.setTitle("Remove Reservation");
        obStage.show();


    }







    public static void main(String[] args) {
        launch(args);
    }
}
