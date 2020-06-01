package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import campground_data.*;


public class MainGui extends Application {

    @Override
    public void start(Stage stage) {
        javafx.scene.control.Button btnExit = new javafx.scene.control.Button("Exit");

        //Panes for use in the Scenes for each section. -EB
            //The Main Pane for the Main Section. -EB
        BorderPane mainPane = new BorderPane();
        HBox paneCenter = new HBox();
        paneCenter.setAlignment(Pos.CENTER);
        paneCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

            //The Panes used in the Accomodations section. -EB
        BorderPane accomPane = new BorderPane();
        GridPane AccomCenter = new GridPane();
        AccomCenter.setAlignment(Pos.CENTER);
        AccomCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        AccomCenter.setHgap(10);
        AccomCenter.setVgap(10);

        HBox AccomBottom = new HBox();
        AccomBottom.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));


            //The Pane used in the Customer Section. -EB
        BorderPane custPane = new BorderPane();
        HBox custCenter = new HBox();
        custCenter.setAlignment(Pos.CENTER);
        custCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

            //The Pane used in the Reservations Section. -EB
        BorderPane resPane = new BorderPane();
        HBox resCenter = new HBox();
        resCenter.setAlignment(Pos.CENTER);
        resCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        //Scenes are set up here with default sizes and using the Panes.
        Scene mainScene = new Scene(mainPane, 500, 500);
        Scene Accommodations = new Scene(accomPane, 500, 500);
        Scene Customers = new Scene(custPane, 500, 500);
        Scene Reservations = new Scene(resPane, 500, 500);

        //Created Buttons for each section. When clicked they should navigate to new scenes containing the content for each. -EB
        Button btnAccom = new Button("Accommodations");
        Button btnCust = new Button("Customers");
        Button btnRes = new Button("Reservations");

        //Adding Buttons to the center pane and setting the center pane to the center of the main pane.
        paneCenter.getChildren().addAll(btnAccom, btnCust, btnRes, btnExit);
        mainPane.setCenter(paneCenter);

        //Event Handlers for moving to another section of the main window.
        btnAccom.setOnAction(e -> stage.setScene(Accommodations));
        btnCust.setOnAction(e -> stage.setScene(Customers));
        btnRes.setOnAction(e -> stage.setScene(Reservations));

        //Code for the Accommodations Section. -EB
        Button btnBack1 = new Button("Back");
        Button btnAdd = new Button("Add Accommodation");
        Button btnEdit = new Button("Edit Accommodation");
        Button btnSet = new Button("Set Availability");
        Button btnPrice = new Button("Set Accommodation Price");


        AccomCenter.add(btnAdd, 0, 1);
        AccomCenter.add(btnEdit, 0, 2);
        AccomCenter.add(btnSet, 0, 3);
        AccomCenter.add(btnPrice, 0, 4);
        accomPane.setCenter(AccomCenter);

        AccomBottom.getChildren().add(btnBack1);
        accomPane.setBottom(AccomBottom);

        addAccommodationGUI addGUI = new addAccommodationGUI(stage);
        EditAccommodationGUI editGUI = new EditAccommodationGUI(stage);
        setAvailabilityGUI setGUI = new setAvailabilityGUI(stage);

        btnBack1.setOnAction(e -> stage.setScene(mainScene));
        btnAdd.setOnAction(e -> addGUI.showAndWait());
        btnEdit.setOnAction(e -> editGUI.showAndWait());
        btnSet.setOnAction(e -> setGUI.showAndWait());

        //End of Code for the Accommodations Section. -EB

        //Code for the Customer Section goes here -EB
        Button btnBack2 = new Button("Back");

        custCenter.getChildren().add(btnBack2);
        custPane.setCenter(custCenter);

        btnBack2.setOnAction(e -> stage.setScene(mainScene));

        //End of Code for the Customer Section. -EB

        //Code for the Reservation Section goes here. -EB
        Button btnBack3 = new Button("Back");

        resCenter.getChildren().add(btnBack3);
        resPane.setCenter(resCenter);

        btnBack3.setOnAction(e -> stage.setScene(mainScene));

        //End of Code for the Reservation Section. -EB


        //Code for running the initial Scene. -EB

        btnExit.setOnAction(e -> stage.close());

        stage.setScene(mainScene);
        stage.setTitle("Main Window");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
