package campground_ui;

import campground_data.*;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private Label lblCustomers, lblCust1, lblCust2,  lblArrival, lblDeparture, lblLotID, lblLotType, lblGuest, lblResID
            , txtResError, DateError;

    //Textfield
    private TextField txtArrivalYear, txtArrivalDay, txtArrivalMonth, txtDepartDay, txtDepartMonth, txtDepartYear,
                        txtGuest, txtLotID, txtResID2;
    //global reservation
    Reservation GReservation;

    //button
    Button btnBack, btnConfirm, btnTransaction, btnEdit;


    @Override
    public void start(Stage obStage) {
        EditReservationGui(); //create the pane
        obStage.setScene(new Scene(obBorder, 650, 500));
        obStage.setTitle("Edit Reservation");
        obStage.show();


    }

    public void EditReservationGui()
    {
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
        txtResError = new Label("");



        //create the labels.
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
        txtLotID.setDisable(true);
        txtLotID.setMaxSize(70, 50);

        txtArrivalYear = new TextField();
        txtArrivalYear.setDisable(true);
        txtArrivalYear.setMaxSize(70,50);

        txtArrivalMonth = new TextField();
        txtArrivalMonth.setMaxSize(70, 50);
        txtArrivalMonth.setDisable(true);

        txtArrivalDay = new TextField();
        txtArrivalDay.setMaxSize(50, 50);
        txtArrivalDay.setDisable(true);

        txtDepartYear = new TextField();
        txtDepartYear.setMaxSize(70, 50);
        txtDepartYear.setDisable(true);

        txtDepartMonth = new TextField();
        txtDepartMonth.setMaxSize(70, 50);
        txtDepartMonth.setDisable(true);

        txtDepartDay = new TextField();
        txtDepartDay.setMaxSize(50, 50);
        txtDepartDay.setDisable(true);

        //space for possible errors
        DateError = new Label("");

        txtGuest = new TextField();
        txtGuest.setMaxSize(70, 50);
        txtGuest.setDisable(true);


        //creating Buttons
        btnBack = new Button("Back to Reservation");
        btnConfirm = new Button("Confirm");
        btnTransaction = new Button("Edit Pricing Information");

        //Setting spacing and alignment
        obGrid.setVgap(5);
        obGrid.setHgap(5);
        obGrid.setAlignment(Pos.CENTER);

        //make the Grid
        //this is the reservation Row.
        obGrid.add(lblResID, 0, 0);
        obGrid.add(txtResID2, 1, 0);
        obGrid.add(btnEdit,2,0);
        obGrid.add(txtResError,3,0);

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
        obGrid.add(DateError, 0, 7);

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
        obBorder = obBorder;

    }

    /**
     * this method will take in a reservation Id and
     * return the reservation that this person may want to edit,
     * It will then add all the information into the test feilds to be edited.
     */
    private void allowEdits(int reservationID) {
        //this is all for testing at the moment
        //this is for testing purposes
        Date obStart = new Date(2020, Calendar.JUNE, 11);
        Date obEnd = new Date(2020, Calendar.JUNE, 15);


        //this will be a basic reservation
        Reservation GlobalRegularReservation = new Reservation(null, 3, obStart, obEnd, new Lot());
        MainGui.bookingsLedger.addReservation(GlobalRegularReservation);

        for( Reservation reservation : BookingsLedger.aReservation)
        {
            if(reservationID == reservation.getID())
            {
                GReservation = reservation;
                populateControls(reservation);
            }
            else
            {
                txtResError.setText("No Reservation With that ID");
            }
        }
    }

    /**
     * this method will populate the conrtols in the text, and allow the user
     * to be able to make edits or changed to the reservation.
     * @param reservation
     */
    private void populateControls(Reservation reservation) {
        if(reservation.getObCustomerList() != null)
        {
            Customer cust1 = (Customer) reservation.getObCustomerList().get(0);
            lblCust1.setText(cust1.getName());
            if(reservation.getObCustomerList().size() == 1)
            {
                Customer cust2 = (Customer) reservation.getObCustomerList().get(1);
                lblCust1.setText(cust2.getName());
            }
        }


        txtLotID.setDisable(false);
        txtLotID.setText(Integer.toString(reservation.getLot().getLotID()));
        addListenerToTestfeild(txtLotID);

        //set the arriavl day
        txtArrivalYear.setDisable(false);
        txtArrivalYear.setText(Integer.toString(reservation.getObStartDate().getYear()));
        addListenerToTestfeild(txtArrivalYear);
        txtArrivalMonth.setDisable(false);
        txtArrivalMonth.setText(Integer.toString((reservation.getObStartDate().getMonth())+1));
        addListenerToTestfeild(txtArrivalMonth);
        txtArrivalDay.setDisable(false);
        txtArrivalDay.setText(Integer.toString(reservation.getObStartDate().getDay()));
        addListenerToTestfeild(txtArrivalDay);

        //set the leaving day
        txtDepartYear.setDisable(false);
        txtDepartYear.setText(Integer.toString(reservation.getObStartDate().getYear()));
        addListenerToTestfeild(txtDepartYear);
        txtDepartMonth.setDisable(false);
        txtDepartMonth.setText(Integer.toString((reservation.getObStartDate().getMonth())+1));
        addListenerToTestfeild(txtDepartMonth);
        txtDepartDay.setDisable(false);
        txtDepartDay.setText(Integer.toString(reservation.getObStartDate().getDay()));
        addListenerToTestfeild(txtDepartDay);

        txtGuest.setDisable(false);
        txtGuest.setText(Integer.toString(reservation.getnCustomerCount()));
        addListenerToTestfeild(txtGuest);


    }


    /**
     * this method will add in onclick listeners for all of the buttons
     */
    public void onClickForButtons()
    {
                btnEdit.setOnAction(ActionEvent ->{
                    if(txtResID2.getText().equals(""))
                    {
                        txtResError.setText("Input ID to Edit");
                    }
                    else
                    {
                        allowEdits(Integer.parseInt(txtResID2.getText()));
                    }
                }
        );

        btnTransaction.setOnAction(actionEvent -> {
                TransactionGUI transGui = new TransactionGUI(GReservation.getTransaction());
                transGui.showAndWait();
        });

        btnConfirm.setOnAction(actionEvent -> {
            Checkinputs();
            System.out.println("This is just for testing");
        });
    }


    /**
     * this method will all a test listner to all of the label feilds that should only be taking in
     * a number
     * this will stop the user from putting in a letter or strange space.
     * @param field
     */
    private void addListenerToTestfeild(TextField field)
    {
        //only let numbers be put into this test feild
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    /**
     * this method will check all of the data in the test feilds and
     * show errors or accept the data accordingly.
     */
    private void Checkinputs() {

        checkBlank();
        int LotID = Integer.parseInt(txtLotID.getText());
        //this method will check to make sure that non of the controls are blank
        //if they are it will fill the controls will 0's
        checkBlank();
        Date newStart = new Date(Integer.parseInt(txtArrivalYear.getText()),
                (Integer.parseInt(txtArrivalMonth.getText())-1),Integer.parseInt(txtArrivalDay.getText()));
        Date newEnd = new Date(Integer.parseInt(txtDepartYear.getText()),
                (Integer.parseInt(txtDepartMonth.getText())-1),Integer.parseInt(txtDepartDay.getText()));


        //set the new Lot Types.
        GReservation.setSiteType(new Lot(LotID).getLotType());
        txtLotID.setDisable(true);

        //set the new days.
        String PossibleError = GReservation.changeDate(newStart, newEnd);
        //set an error if one accouers
        DateError.setText(PossibleError);


        PossibleError += " " + GReservation.setCustomerNumber(Integer.parseInt(txtGuest.getText()));
        DateError.setText(PossibleError);


    }

    /**
     * this method will check all of the testboxes in the javaFX
     * if any of the controls are blank it will fill these controls with 0.
     */
    private void checkBlank() {
        isBlank(txtLotID);
        isBlank(txtArrivalYear);
        isBlank(txtArrivalMonth);
        isBlank(txtArrivalDay);
        isBlank(txtDepartYear);
        isBlank(txtDepartMonth);
        isBlank(txtDepartDay);
        isBlank(txtGuest);
        
    }

    public void isBlank(TextField txt)
    {
        if(txt.getText().equals(""))
        {
            txt.setText(Integer.toString(000));
        }
        else
        {

        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
