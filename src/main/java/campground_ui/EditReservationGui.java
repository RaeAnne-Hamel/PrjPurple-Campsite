package campground_ui;

import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditReservationGui extends Application {

    //this is for testing purposes
    private Date obStart = new Date(2020, Calendar.JUNE, 11);
    private Date obEnd = new Date(2020, Calendar.JUNE, 15);


    //this will be a basic reservation
    Reservation GlobalRegularReservation = new Reservation(null, 3, obStart, obEnd, new Lot());




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
    private Label lblCustomers, lblCust1, lblCust2,  lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID;

    //Textfield
    private TextField txtArrivalYear, txtArrivalDay, txtArrivalMonth, txtDepartDay, txtDepartMonth, txtDepartYear,
                        txtGuest, txtLotID, txtResID2;


    //button
    Button btnBack, btnConfirm, btnTransaction, btnEdit;


    @Override
    public void start(Stage obStage) {

        //Creating panes
        obGrid = new GridPane();
        obBorder = new BorderPane();
        obHBox = new HBox();

        //Creating Labels
        //reservation label.
        lblResID = new Label("Reservation Id: ");
        txtResID2 = new TextField();
        lblResID.setMaxSize(70, 50);
        //only let numbers be put into this test feild
        txtResID2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtResID2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        btnEdit = new Button("Edit");
        btnBack.setOnAction(ActionEvent ->{
            allowEdits();

                }
        );


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
        btnTransaction = new Button("Edit Pricing Information");

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


        //add the buttons onto the plane.
        obHBox.setAlignment(Pos.BASELINE_CENTER);
        obHBox.setSpacing(125);
        obHBox.setPadding(new Insets(15,10,10,10));
        obHBox.getChildren().addAll(btnBack, btnTransaction, btnConfirm);
        onClickForButtons();

        obBorder.setCenter(obGrid);
        obBorder.setBottom(obHBox);

        obStage.setScene(new Scene(obBorder, 650, 500));
        obStage.setTitle("Edit Reservation");
        obStage.show();


    }

    private void allowEdits() {
        return;
    }

    public void onClickForButtons()
    {
        btnTransaction.setOnAction(actionEvent -> {
                TransactionGUI transGui = new TransactionGUI(GlobalRegularReservation.getTransaction());
                transGui.showAndWait();
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
