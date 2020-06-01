package campground_ui;

import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import javafx.application.Application;
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

public class AddReservationGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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
    private Label lblCustomers, lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID, lblResID2;

    //Textfield
    private TextField txtArrivalYear, txtArrivalDay, txtArrivalMonth, txtDepartDay, txtDepartMonth, txtDepartYear,
                        txtGuest, txtLotID;

    //comboBoxes
    private ComboBox<Customer> cboCust1, cboCust2;

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
        lblLotID = new Label("Lot ID:");
        lblLotType = new Label("");
        lblArrival = new Label("Arrival Date (yyyy/m/d): ");
        lblDeparture = new Label("Departure Date (yyyy/m/d): ");
        lblGuest = new Label("Number of Guests: ");

        //Creating text fields
        txtLotID = new TextField();
        txtArrivalYear = new TextField();
        txtArrivalMonth = new TextField();
        txtArrivalDay = new TextField();
        txtDepartYear = new TextField();
        txtDepartMonth = new TextField();
        txtDepartDay = new TextField();
        txtGuest = new TextField();

        //creating combo boxes
        cboCust1 = new ComboBox<>();
        cboCust2 = new ComboBox<>();

        //creating Buttons
        btnBack = new Button("Back to Reservation");
        btnBack = new Button("Confirm");

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

        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(100);
        obHBox.getChildren().addAll(btnBack, btnConfirm);

        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);









        obStage.setScene(new Scene(obBorder, 800, 800));
        obStage.setTitle("Add Reservation");
        obStage.show();











    }


    private ArrayList<Customer> getCustNameList(ArrayList<Customer> obCustomer)
    {
        ArrayList<Customer> obRet = new ArrayList<>();

        for(Customer obCust : obCustomer)
        {
           // obCust = obCustomer.get
        }


        return obRet;
    }
}
