package campground_ui;

import campground_data.BookingsLedger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class EditAccommodationGUI extends Application {

    BookingsLedger BL;

    @Override
    public void start(Stage stage) throws Exception {
        BL = new BookingsLedger();

        BorderPane mainPane = new BorderPane();

        //Creating the top pane for the label. -EB
        HBox paneTop = new HBox();
        paneTop.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        //Adding the label to the top pane. -EB
        Label lblTop = new Label("Select an Accommodation to edit:");
        paneTop.getChildren().add(lblTop);

        //Creating the centre pane for the ListView displaying Lot data. -EB
        HBox paneCentre = new HBox();
        paneCentre.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        //Adding the ListView to the centre pane. -EB


        // Creating the bottom pane for the "Back" and "Confirm" buttons. -EB
        HBox paneBottom = new HBox();
        paneBottom.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        //Create the nodes to be added to the Bottom Pane. -EB
        final Pane Spacer = new Pane();
        HBox.setHgrow(Spacer, Priority.ALWAYS);
        Spacer.setMinSize(10, 1);

        Button btBack = new Button("Back");
        Button btConfirm = new Button("Confirm");

        //Adding the nodes to the Bottom Pane. -EB
        paneBottom.getChildren().add(btBack);
        paneBottom.getChildren().add(Spacer);
        paneBottom.getChildren().add(btConfirm);

        mainPane.setTop(paneTop);
        mainPane.setBottom(paneBottom);


        Scene scene = new Scene(mainPane, 500, 500);
        stage.setTitle("Edit Accommodation");
        stage.setScene(scene);
        stage.show();
    }
}
