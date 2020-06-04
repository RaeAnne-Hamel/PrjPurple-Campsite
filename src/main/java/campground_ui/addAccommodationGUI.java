package campground_ui;

import campground_data.BookingsLedger;
import campground_data.Lot;
import campground_data.LotType;
import campground_data.Reservation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Stack;

import static campground_ui.MainGui.obBookingsLedger;


public class addAccommodationGUI extends Stage {

    //JavaFX Elements. -EB
    Text txtID;
    ComboBox<String> cboType;
    ComboBox<String> cboAvailable;
    TextArea txtReason;

    public addAccommodationGUI(Stage primaryStage)
    {

        //Creating Center Pane where input fields go. -EB
        GridPane paneCenter = new GridPane();
        paneCenter.setAlignment(Pos.CENTER);
        paneCenter.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneCenter.setHgap(10);
        paneCenter.setVgap(10);

        // Creating the bottom pane for the "Back" and "Confirm" buttons. -EB
        HBox paneBottom = new HBox();
        paneBottom.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(paneCenter);
        mainPane.setBottom(paneBottom);

        //Create the nodes to be added to the Center pane. -EB
        Label lblID = new Label("Accommodation ID:");
        Label lblType = new Label("Accomodation Type:");
        Label lblAvailability = new Label("Availability:");

        txtID = new Text("");
        String sID = Integer.toString(MainGui.obBookingsLedger.getLotList().size());
        txtID.setText(sID);

        cboType = new ComboBox<>();
        cboAvailable = new ComboBox<>();
        txtReason = new TextArea();
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

        //Code for clicking Back. Takes closes the current window. -EB
        btBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Back();
            }
        });

        //Code for clicking Confirm. Runs the Confirm() method. -EB
        btConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Confirm();

            }
        });

        //Creates the windows where everything goes -EB
        Scene scene = new Scene(mainPane, 500, 500);
        this.setTitle("Add Accommodation");
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);


    }


    /*
    Sets the TextArea visibility so that the box appears when set to not Available and disappears when set to available.
    The TextArea should only be available to the user when it is needed. So when the lot is set to unavailable there needs to be a reason for it. -EB
     */
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

        String sReason = txtReason.getText();
        txtReason.setText("");

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
            case "Available":
                obLot.setAvailability(true);
                obLot.setRemovalOverride("N/A");
                MainGui.obBookingsLedger.addAccommodation(obLot);
                txtReason.setText("");

                int nID2 = Integer.parseInt(txtID.getText());
                nID2++;
                txtID.setText(Integer.toString(nID2));

                Alert confirm = new Alert(Alert.AlertType.INFORMATION, "Accommodation Successfully Added.", ButtonType.CLOSE);
                confirm.showAndWait();
                if (confirm.getResult() == ButtonType.CLOSE)
                {
                    confirm.close();
                }

                this.close();

                break;

            case "Not Available":
                if (sReason.equals(""))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Removal Reason must contain at least one character", ButtonType.CLOSE);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.CLOSE)
                    {
                        alert.close();
                    }
                }
                else if (sReason.length() > 255)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Removal Reason must be less than 256 characters in length", ButtonType.CLOSE);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.CLOSE)
                    {
                        alert.close();
                    }
                }
                else
                {
                    //Adds the accommodation created to the List of accommodations.
                    MainGui.obBookingsLedger.addAccommodation(obLot);
                    obLot.setAvailability(false);
                    txtReason.setText("");
                    obLot.setRemovalReason(sReason);

                    int nID3 = Integer.parseInt(txtID.getText());
                    nID3++;
                    txtID.setText(Integer.toString(nID3));

                    Alert confirm2 = new Alert(Alert.AlertType.INFORMATION, "Accommodation Successfully Added.", ButtonType.CLOSE);
                    confirm2.showAndWait();
                    if (confirm2.getResult() == ButtonType.CLOSE)
                    {
                        confirm2.close();
                    }

                    this.close();
                }

                break;

            default:
                break;
        }

        ArrayList<Reservation> obRes = new ArrayList<>();
        obLot.setReservations(obRes);




        //Clears the accommodation listview and reinserts the updated information into it. -EB
        EditAccommodationGUI.accomList.getItems().clear();
        for (Lot obLot1 : obBookingsLedger.getLotList())
        {
            EditAccommodationGUI.accomList.getItems().add(obLot1);
        }

        //Increments the ID so that the next ID listed is a new one that has not been used yet. -EB






    }

    //Closes the window. -EB
    public void Back()
    {
        this.close();
    }

    /**
     * Add Save methods passed here. -EB
     */



}
