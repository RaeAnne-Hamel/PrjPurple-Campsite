package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    @Override
    public void start(Stage stage) {
        //Setting up the image to use is the same in each section - DW
        ImageView imgCamp = new ImageView(Camp);
        imgCamp.setFitHeight(353);
        imgCamp.setFitWidth(500);
        javafx.scene.control.Button btnExit = new javafx.scene.control.Button("Exit");

        //Panes for use in the Scenes for each section. -EB
        //The Main Pane for the Main Section. -EB
        BorderPane mainPane = new BorderPane();
        HBox paneCenter = new HBox();
        paneCenter.setAlignment(Pos.CENTER);
        paneCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        mainPane.setTop(imgCamp);

        //The Pane used in the Accommodations section. -EB
        OpenAccommodation(stage);
        //The Pane used in the Customer Section. -EB
        OpenCustomer(stage);
        //The Pane used in the Reservations Section. -EB
        OpenReservation(stage);

        //Scenes are set up here with default sizes and using the Panes.
        mainScene = new Scene(mainPane, 500, 500);
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

        //Code for running the initial Scene. -EB
        btnExit.setOnAction(e -> stage.close());

        stage.setScene(mainScene);
        stage.setTitle("Main Window");
        stage.show();
    }

    /**
     * Code for the Reservations section
     * @param stage Takes in the parent stage.
     */
    private void OpenReservation(Stage stage) {
        resPane = new BorderPane();
        HBox resCenter = new HBox();
        resCenter.setAlignment(Pos.CENTER);
        resCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        ImageView imgCampR = new ImageView(Camp);
        imgCampR.setFitHeight(353);
        imgCampR.setFitWidth(500);
        resPane.setTop(imgCampR);

        Button btnBack3 = new Button("Back");

        resCenter.getChildren().add(btnBack3);
        resPane.setCenter(resCenter);

        btnBack3.setOnAction(e -> stage.setScene(mainScene));
    }

    /**
     * Code for the Accommodations section
     * @param stage Takes in the parent stage.
     */
    private void OpenAccommodation(Stage stage) {
        accomPane = new BorderPane();
        HBox AccomCenter = new HBox();
        AccomCenter.setAlignment(Pos.CENTER);
        AccomCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        ImageView imgCampA = new ImageView(Camp);
        imgCampA.setFitHeight(353);
        imgCampA.setFitWidth(500);
        accomPane.setTop(imgCampA);

        Button btnBack1 = new Button("Back");

        AccomCenter.getChildren().add(btnBack1);
        accomPane.setCenter(AccomCenter);

        btnBack1.setOnAction(e -> stage.setScene(mainScene));
    }

    /**
     * Code for the Customers section
     * @param stage Takes in the parent stage
     */
    private void OpenCustomer(Stage stage) {
        custPane = new BorderPane();
        HBox custCenter = new HBox();
        custCenter.setAlignment(Pos.CENTER);
        custCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        ImageView imgCampC = new ImageView(Camp);
        imgCampC.setFitHeight(353);
        imgCampC.setFitWidth(500);
        custPane.setTop(imgCampC);

        Button btnBack2 = new Button("Back");

        custCenter.getChildren().add(btnBack2);
        custPane.setCenter(custCenter);

        btnBack2.setOnAction(e -> stage.setScene(mainScene));
    }

    public static void main(String[] args) {
        launch(args);
    }
}