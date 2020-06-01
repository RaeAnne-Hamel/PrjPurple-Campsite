package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Stack;

public class addAccommodationGUI extends Application {

    @Override
    public void start(Stage primaryStage)
    {
        GridPane paneCenter = new GridPane();
        paneCenter.setAlignment(Pos.CENTER);
        paneCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneCenter.setHgap(10);
        paneCenter.setVgap(10);

        HBox paneBottom = new HBox();
        paneBottom.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(paneCenter);
        mainPane.setBottom(paneBottom);

        //Create the nodes to be added to the Center pane. -EB
        Label lblID = new Label("Accommodation ID:");
        Label lblType = new Label("Accomodation Type:");
        Label lblAvailability = new Label("Availability:");
        Text txtID = new Text("0");
        ComboBox<String> cboType = new ComboBox<>();
        ComboBox<String> cboAvailable = new ComboBox<>();
        TextArea txtReason = new TextArea();
        txtReason.setMaxSize(10, 10);

        //Create the nodes to be added to the Bottom Pane. -EB
        final Pane Spacer = new Pane();
        HBox.setHgrow(Spacer, Priority.ALWAYS);
        Spacer.setMinSize(10, 1);

        Button btBack = new Button("Back");
        Button btConfirm = new Button("Confirm");

        //Code for ComboBox information. -EB
        String[] aTypes = {"Non-Serviced Individual", "Serviced Individual", "Non-Serviced Group", "Serviced Group", "Cabin", "Deluxe Cabin"};
        cboType.getItems().addAll(aTypes);
        cboType.setValue(aTypes[0]);

        String[] aAvailability = {"Available", "Not Available"};
        cboAvailable.getItems().addAll(aAvailability);
        cboAvailable.setValue(aAvailability[0]);



        //Adding the nodes to the Center Pane. -EB
        paneCenter.add(lblID, 0, 0);
        paneCenter.add(txtID, 1, 0);
        paneCenter.add(lblType, 0, 1);
        paneCenter.add(cboType, 1, 1);
        paneCenter.add(lblAvailability, 0, 2);
        paneCenter.add(cboAvailable, 1, 2);
        paneCenter.add(txtReason, 0, 3, 2, 3);

        //Adding the nodes to the Bottom Pane. -EB
        paneBottom.getChildren().add(btBack);
        paneBottom.getChildren().add(Spacer);
        paneBottom.getChildren().add(btConfirm);



        //Creates the windows where everything goes -EB
        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setTitle("Add Accommodation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
