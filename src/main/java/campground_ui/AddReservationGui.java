package campground_ui;

import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import javafx.application.Application;
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

    //panes
    private GridPane obGrid;
    private BorderPane obBorder;

    //Labels
    private Label lblCustomers, lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID, lblResID2;

    //Textfield
    private TextField

    @Override
    public void start(Stage primaryStage) {


    }
}
