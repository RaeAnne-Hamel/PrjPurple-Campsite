package campground_ui;

import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import javafx.application.Application;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

    //Labels
    private Label lblCustomers, lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID, lblResID2;

    //Textfield
    private TextField txtArrivalYear, txtArrivalDay, txtArrivalMonth, txtDepartDay, txtDepartMonth, txtDepartYear,
                        txtGuest, txtLotID;

    //comboBoxes
    private ComboBox<Customer> cboCust1, cboCust2;

    //button


    @Override
    public void start(Stage primaryStage) {

        //Creating panes
        obGrid = new GridPane();
        obBorder = new BorderPane();

        //Creating Labels
        lblResID = new Label("Reservation Id: ");
        lblResID2 = new Label("");
        lblCustomers = new Label("Customer Names: ");
        lblLotID = new Label("Lot ID:");
        lblLotType = new Label("");
        lblArrival = new Label("Arrival Date: ");
        lblDeparture = new Label("Departure Date: ");
        lblGuest = new Label("Number of Guests: ");

        //Creating text fields
        txtLotID = new TextField();
        txtArrivalYear = new TextField();
        txtArrivalMonth = new TextField();
        txtArrivalDay = new TextField();
        txtDepartYear = new TextField();
        txtDepartMonth = new TextField();
        txtDepartDay = new TextField();











    }


    private ArrayList<Customer> getCustNameList(ArrayList<Customer> obCustomer)
    {
        ArrayList<Customer> obRet = new ArrayList<>();

        for(Customer obCust : obCustomer)
        {
            obCust = obCustomer.get
        }


        return obRet;
    }
}
