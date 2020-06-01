package campground_ui;

import campground_data.Lot;
import campground_data.LotType;
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

    public Text txtID;
    ComboBox<String> cboType;
    ComboBox<String> cboAvailable;



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
        txtID = new Text("0");
        cboType = new ComboBox<>();
        cboAvailable = new ComboBox<>();
        TextArea txtReason = new TextArea();
        txtReason.setMaxSize(300, 100);
        txtReason.setVisible(false);

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
        cboAvailable.setOnAction(e -> setAreaVisibility(txtReason));

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

        //Code for clicking Confirm. -EB
        btConfirm.setOnAction(e -> Confirm());

        //Creates the windows where everything goes -EB
        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setTitle("Add Accommodation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void setAreaVisibility(TextArea txtArea)
    {
        txtArea.setVisible(!txtArea.isVisible());

    }

    //Method that creates a Lot and adds in to the Booking Ledger based on information taken from the fields. -EB
    public void Confirm()
    {
        //Getting the ID from the text displaying it
        int nID = Integer.parseInt(txtID.getText());

        //Creating a Lot using the default constructor so the information can be changed based on combo boxes. -EB
        Lot obLot = new Lot(nID);

        //Switch case for changing the Lot Type based on which option was chosen in the field. -EB
        switch (cboType.getValue())
        {
            case "Non-Serviced Group":
                obLot.setLotType(LotType.NonServicedGroup);
                break;

            case "Serviced Group":
                obLot.setLotType(LotType.ServicedGroup);
                break;

            case "Non-Serviced Individual":
                obLot.setLotType(LotType.NonServicedIndividual);
                break;

            case "Serviced Individual":
                obLot.setLotType(LotType.ServicedIndividual);
                break;

            case "Cabin":
                obLot.setLotType(LotType.Cabin);
                break;

            case "Deluxe Cabin":
                obLot.setLotType(LotType.DeluxeCabin);
                break;

            default:
                break;
        }

        /*
        Switch case for setting the availability based on the value chosen in the combo box.
        Also sets the removal reason if the availability is set to false. -EB
         */
        switch(cboAvailable.getValue())
        {

        }

        System.out.println(obLot.toString());

    }



}
