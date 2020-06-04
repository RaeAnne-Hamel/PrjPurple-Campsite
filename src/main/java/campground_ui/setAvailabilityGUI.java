package campground_ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class setAvailabilityGUI extends Stage {

    public setAvailabilityGUI(Stage primaryStage)
    {

        BorderPane mainPane = new BorderPane();

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

        mainPane.setBottom(paneBottom);

        //Code for clicking Back. Takes closes the current window. -EB
        btBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Back();
            }
        });




        Scene scene = new Scene(mainPane, 500, 500);
        this.setTitle("Set Availability");
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    public void Back()
    {
        this.close();
    }

}
