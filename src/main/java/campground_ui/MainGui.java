package campground_ui;//package campground_ui;


import campground_data.Customer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import campground_data.*;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class MainGui extends Application {

    //Loads image once so it isn't wasteful. Declares the separate panes here for general use.
    //public static BookingsLedger bookingsLedger = new BookingsLedger();


    Image Camp = new Image("file:images/campground.jpg");
    BorderPane custPane;
    BorderPane accomPane;
    BorderPane resPane;
    static Scene mainScene;

    public static BookingsLedger obBookingsLedger;


    @Override
    public void start(Stage stage) {

        /**
         * Test data for adding and editing lots. Feel free to edit, remove or use for yourself. Just used to test the data until
         * saving functionality is added. -EB
         */

        obBookingsLedger = new BookingsLedger();

        obBookingsLedger.setCustomerList(PersistentDataManager.load("src/files/Customers.txt", LoadType.Customer, obBookingsLedger));
        obBookingsLedger.setLotList(PersistentDataManager.load("src/files/Lots.txt", LoadType.Lot, obBookingsLedger));
        obBookingsLedger.setManagerList(PersistentDataManager.load("src/files/Managers.txt", LoadType.Manager, obBookingsLedger));
        obBookingsLedger.setReservationsList(PersistentDataManager.load("src/files/Reservations.txt", LoadType.Reservation, obBookingsLedger));
        obBookingsLedger.setTransactionList(PersistentDataManager.load("src/files/Transactions.txt", LoadType.Transaction, obBookingsLedger));

        Lot obLot1 = new Lot(0);
        Lot obLot2 = new Lot(1);
        Lot obLot3 = new Lot(2);
        Lot obLot4 = new Lot(3);

        obBookingsLedger.addAccommodation(obLot1);
        obBookingsLedger.addAccommodation(obLot2);
        obBookingsLedger.addAccommodation(obLot3);
        obBookingsLedger.addAccommodation(obLot4);


        //Setting up the image to use is the same in each section - DW
        ImageView imgCamp = new ImageView(Camp);
        imgCamp.setFitHeight(353);
        imgCamp.setFitWidth(500);
        javafx.scene.control.Button btnExit = new javafx.scene.control.Button("Exit");

        //Panes for use in the Scenes for each section. -EB

        //The Main Pane for the Main Section. -EB
        BorderPane mainPane = new BorderPane();
        HBox paneCenter = new HBox();
        VBox paneLeft = new VBox();
        VBox paneRight = new VBox();
        paneLeft.setAlignment(Pos.BOTTOM_LEFT);
        paneRight.setAlignment(Pos.CENTER_RIGHT);
        paneRight.setSpacing(10);

        paneCenter.setAlignment(Pos.CENTER);
        paneCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneCenter.setSpacing(200);
        mainPane.setTop(imgCamp);

        //The Pane used in the Accommodations, Customer, and Reservations sections. -EB
        OpenAccommodation(stage);
        OpenCustomer(stage);
        OpenReservation(stage);

        //Scenes are set up here with default sizes and using the Panes.
        mainScene = new Scene(mainPane, 500, 500);
        Scene Accommodations = new Scene(accomPane, 500, 500);
        Scene Customers = new Scene(custPane, 500, 500);
        Scene Reservations = new Scene(resPane, 500, 550);

        //Created Buttons for each section. When clicked they should navigate to new scenes containing the content for each. -EB
        Button btnAccom = new Button("Accommodations");
        btnAccom.setPrefWidth(150);
        Button btnCust = new Button("Customers");
        btnCust.setPrefWidth(150);
        Button btnRes = new Button("Reservations");
        btnRes.setPrefWidth(150);

        //Adding Buttons to the center pane and setting the center pane to the center of the main pane.
        paneLeft.getChildren().add(btnExit);
        paneRight.getChildren().addAll(btnAccom, btnCust, btnRes);
        paneCenter.getChildren().addAll(paneLeft, paneRight);

        mainPane.setCenter(paneCenter);

        //Event Handlers for moving to another section of the main window.
        btnAccom.setOnAction(e -> stage.setScene(Accommodations));
        btnCust.setOnAction(e -> stage.setScene(Customers));
        btnRes.setOnAction(e -> stage.setScene(Reservations));

        //Code for running the initial Scene. -EB

        btnExit.setOnAction(e -> stage.close());

        stage.setScene(mainScene);
        stage.setTitle("Main Window");
        stage.show();
    }

    /**
     * Code for the Reservations section - DW
     * @param stage Takes in the parent stage.
     */
    private void OpenReservation(Stage stage) {
        //Creates new panes for the top and bottom. Separate VBoxes are created for the buttons on the left and right for alignment reasons - DW
        resPane = new BorderPane();
        HBox resCenter = new HBox();
        VBox paneLeft = new VBox();
        VBox paneRight = new VBox();
        paneLeft.setAlignment(Pos.BOTTOM_LEFT);
        paneRight.setAlignment(Pos.CENTER_RIGHT);
        paneRight.setSpacing(10);

        resCenter.setAlignment(Pos.CENTER);
        resCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        resCenter.setSpacing(150);
        ImageView imgCampR = new ImageView(Camp);
        imgCampR.setFitHeight(353);
        imgCampR.setFitWidth(500);
        resPane.setTop(imgCampR);

        Button btnBack3 = new Button("Back to Home");
        Button btnSearch = new Button("Search Available Accommodations");
        Button btnAdd = new Button("Add Reservation");
        btnAdd.setPrefWidth(200);
        Button btnEdit = new Button("Edit Reservation");
        btnEdit.setPrefWidth(200);
        btnEdit.setOnAction(actionEvent -> {
            EditReservationGui EditRes = new EditReservationGui();
            EditRes.showAndWait();
        });
        Button btnRemove = new Button("Remove Reservation");
        btnRemove.setPrefWidth(200);

        //Create Stages opened in Reservation scene -AE
        SearchDateWindow searchDateWindow = new SearchDateWindow(stage);

        //Set event handlers on buttons to open external Stage -AE
        btnSearch.setOnAction(e-> searchDateWindow.showAndWait());


        paneLeft.getChildren().add(btnBack3);
        paneRight.getChildren().addAll(btnSearch, btnAdd, btnEdit, btnRemove);

        resCenter.getChildren().addAll(paneLeft, paneRight);
        resPane.setCenter(resCenter);

        btnBack3.setOnAction(e -> stage.setScene(mainScene));
    }

    /**
     * Code for the Accommodations section - DW
     * @param stage Takes in the parent stage.
     */
    private void OpenAccommodation(Stage stage) {
        //Creates new panes for the top and bottom. Separate VBoxes are created for the buttons on the left and right for alignment reasons - DW
        accomPane = new BorderPane();
        HBox AccomCenter = new HBox();
        VBox AccomLeft = new VBox();
        AccomLeft.setAlignment(Pos.BOTTOM_LEFT);
        VBox AccomRight = new VBox();
        AccomRight.setSpacing(10);
        AccomRight.setAlignment(Pos.CENTER_RIGHT);

        AccomCenter.setAlignment(Pos.CENTER);
        AccomCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        AccomCenter.setSpacing(200);
        ImageView imgCampA = new ImageView(Camp);
        imgCampA.setFitHeight(353);
        imgCampA.setFitWidth(500);
        accomPane.setTop(imgCampA);

        Button btnBack1 = new Button("Back to Home");
        Button btnAddAccom = new Button("Add Accommodation");
        btnAddAccom.setPrefWidth(150);
        Button btnEditAccom = new Button("Edit Accommodation");
        btnEditAccom.setPrefWidth(150);
        Button btnSetPrice = new Button("Set Price");
        btnSetPrice.setPrefWidth(150);

        AccomLeft.getChildren().add(btnBack1);
        AccomRight.getChildren().addAll(btnAddAccom, btnEditAccom, btnSetPrice);
        AccomCenter.getChildren().addAll(AccomLeft, AccomRight);
        accomPane.setCenter(AccomCenter);

        addAccommodationGUI addGUI = new addAccommodationGUI(stage);
        EditAccommodationGUI editGUI = new EditAccommodationGUI(stage);

        btnBack1.setOnAction(e -> stage.setScene(mainScene));
        btnAddAccom.setOnAction(e -> addGUI.showAndWait());
        btnEditAccom.setOnAction(e -> editGUI.showAndWait());

        btnBack1.setOnAction(e -> stage.setScene(mainScene));
    }

    /**
     * Code for the Customers section - DW
     * @param stage Takes in the parent stage
     */
    private void OpenCustomer(Stage stage) {
        //Creates new panes for the top and bottom. Separate VBoxes are created for the buttons on the left and right for alignment reasons - DW
        custPane = new BorderPane();
        HBox custCenter = new HBox();
        VBox custLeft = new VBox();
        custLeft.setAlignment(Pos.BOTTOM_LEFT);
        VBox custRight = new VBox();
        custRight.setSpacing(10);
        custRight.setAlignment(Pos.CENTER_RIGHT);

        custCenter.setAlignment(Pos.CENTER);
        custCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        custCenter.setSpacing(200);
        ImageView imgCampC = new ImageView(Camp);
        imgCampC.setFitHeight(353);
        imgCampC.setFitWidth(500);
        custPane.setTop(imgCampC);

        Button btnBack2 = new Button("Back to Home");
        Button btnAddCustomer = new Button("Add Customer");
        btnAddCustomer.setPrefWidth(150);
        Button btnEditCustomer = new Button("Edit Customer");
        btnEditCustomer.setPrefWidth(150);

        custLeft.getChildren().add(btnBack2);
        custRight.getChildren().addAll(btnAddCustomer, btnEditCustomer);
        custCenter.getChildren().addAll(custLeft, custRight);
        custPane.setCenter(custCenter);

        btnAddCustomer.setOnAction(e -> {
            try {
                new AddCustomerWindow(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            });
        btnEditCustomer.setOnAction(e -> EditCust(stage));
        btnBack2.setOnAction(e -> stage.setScene(mainScene));
    }

    /**
     * Code for what happens when you click the edit customer button. Brings up a search command. -DW
     * @param stage Takes in the parent stage
     */
    private void EditCust(Stage stage) {
        //Creates new panes for the top and bottom. Separate VBoxes are created for the buttons on the left and right for alignment reasons.
        BorderPane editCustPane = new BorderPane();
        HBox custCenter = new HBox();
        VBox custLeft = new VBox();
        custLeft.setAlignment(Pos.BOTTOM_LEFT);
        VBox custRight = new VBox();
        custRight.setSpacing(10);
        custRight.setAlignment(Pos.CENTER_RIGHT);

        custCenter.setAlignment(Pos.CENTER);
        custCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        custCenter.setSpacing(100);

        Button btnBack2 = new Button("Back to Home");
        Button btnSearch = new Button("Search");
        Button btnID = new Button("Edit with Customer ID");

        //Label and TextField for where you search a customer's ID.
        Label lblID = new Label("Customer ID");
        TextField txtID = new TextField();

        //TextField and label for where you search a customer's last name.
        Label lblName = new Label("Last Name");
        TextField txtSearch = new TextField();

        //Label where search results go
        Label lblResults = new Label();

        //GridPane for the labels and buttons and text fields on the bottom, needed so that they would line up.
        GridPane bottomGrid = new GridPane();
        bottomGrid.add(lblID, 0, 0);
        bottomGrid.add(lblName, 0, 1);
        bottomGrid.add(btnBack2, 0, 3);
        bottomGrid.add(txtID, 1, 0);
        bottomGrid.add(txtSearch, 1, 1);

        //The label goes on top with the grid in the bottom and the search buttons in the bottom right.
        editCustPane.setTop(lblResults);
        custRight.getChildren().addAll(btnID, btnSearch);
        custCenter.getChildren().addAll(bottomGrid, custRight);
        editCustPane.setBottom(custCenter);

        //Stream that returns a sorted list of customers with the last name entered. List will include first and last names and ID.
        btnSearch.setOnAction(e -> {
            String sSearch = txtSearch.getText();
            ArrayList<String> obAC = obBookingsLedger.getCustomerList().stream()
                    .filter(x -> x.getLast().equalsIgnoreCase(sSearch))
                    .sorted((x, y) -> (x.getLast().compareTo(y.getLast())))
                    .map(x -> x.getName() + " " + x.getLast() + " " + x.getCustomerID())
                    .collect(Collectors.toCollection(ArrayList::new));
            String sResults = "";

            //Results label shows everyone with that last name.
            for (String sVal : obAC)
            {
                sResults += (sVal + "\n");
            }
            if (sResults.equals("")){
                sResults = "No one with that last name found.";
            }
            lblResults.setText(sResults);
        });

        btnID.setOnAction(e -> {
            //Try/catch block in case ID field is left empty or not a number.
            boolean bFound = false;
            try {
                for (Customer obCust : obBookingsLedger.getCustomerList()) {
                    //Opens a new edit customer window for the customer that was searched for.
                    if (obCust.getCustomerID() == Integer.parseInt(txtID.getText())) {
                        bFound = true;
                        new EditCustomerWindow(stage, obCust);
                    }
                }
            }
            catch (NumberFormatException ignored){
            }
            if (!bFound){
                lblResults.setText("No one with that ID found.");
            }
        });

        //Back button goes back to main scene.
        btnBack2.setOnAction(e -> stage.setScene(mainScene));
        stage.setScene(new Scene(editCustPane, 500, 500));
    }

    public static void main(String[] args) {
        launch(args);
    }
}


