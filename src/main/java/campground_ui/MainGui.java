package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import campground_data.*;




public class MainGui extends Application {
    //Loads image once so it isn't wasteful. Declares the separate panes here for general use.
    Image Camp = new Image("file:images/campground.jpg");
    BorderPane custPane;
    BorderPane accomPane;
    BorderPane resPane;
    Scene mainScene;

    public static BookingsLedger obBookingsLedger;


    @Override
    public void start(Stage stage) {

        obBookingsLedger = new BookingsLedger();

        Lot obLot1 = new Lot(0);
        Lot obLot2 = new Lot(1);
        Lot obLot3 = new Lot(2);
        Lot obLot4 = new Lot(3);
        Lot obLot5 = new Lot(4);
        Lot obLot6 = new Lot(5);
        Lot obLot7 = new Lot(6);
        Lot obLot8 = new Lot(7);
        Lot obLot9 = new Lot(8);
        Lot obLot10 = new Lot(9);
        Lot obLot11 = new Lot(10);

        obBookingsLedger.addAccommodation(obLot1);
        obBookingsLedger.addAccommodation(obLot2);
        obBookingsLedger.addAccommodation(obLot3);
        obBookingsLedger.addAccommodation(obLot4);
        obBookingsLedger.addAccommodation(obLot5);
        obBookingsLedger.addAccommodation(obLot6);
        obBookingsLedger.addAccommodation(obLot7);
        obBookingsLedger.addAccommodation(obLot8);
        obBookingsLedger.addAccommodation(obLot9);
        obBookingsLedger.addAccommodation(obLot10);
        obBookingsLedger.addAccommodation(obLot11);

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


        //Code for the Accommodations Section. -EB
        Button btnBack1 = new Button("Back");





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
        Button btnRemove = new Button("Remove Reservation");
        btnRemove.setPrefWidth(200);
        Button btnTrans = new Button("Transactions");
        btnTrans.setPrefWidth(200);

        paneLeft.getChildren().add(btnBack3);
        paneRight.getChildren().addAll(btnSearch, btnAdd, btnEdit, btnRemove, btnTrans);

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

        btnBack2.setOnAction(e -> stage.setScene(mainScene));
    }

    public static void main(String[] args) {
        launch(args);
    }
}