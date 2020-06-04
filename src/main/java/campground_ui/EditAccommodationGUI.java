package campground_ui;

import campground_data.BookingsLedger;
import campground_data.Lot;
import campground_data.LotType;
import campground_data.Reservation;
import javafx.application.Application;
import javafx.collections.ObservableList;
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

import java.util.List;

import static campground_ui.MainGui.obBookingsLedger;

public class EditAccommodationGUI extends Stage {

    //JavaFX Elements
    Scene scene;
    Scene scene2;
    Text txtID;
    ComboBox<String> cboType;
    ComboBox<String> cboAvailable;
    TextArea txtReason;
    public static ListView accomList;



    public EditAccommodationGUI(Stage primaryStage) {

        //Pane created for the first scene that displays the list of lots. -EB
        BorderPane mainPane = new BorderPane();

        //Creating the top pane for the label. -EB
        HBox paneTop = new HBox();
        paneTop.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        //Adding the label to the top pane. -EB
        Label lblTop = new Label("Select an Accommodation to edit:");
        paneTop.getChildren().add(lblTop);

        //Creating the centre pane for the ListView displaying Accommodation data. -EB
        HBox paneCentre = new HBox();
        paneCentre.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        paneCentre.setAlignment(Pos.TOP_CENTER);

        //Adding the ListView to the centre pane. -EB
        accomList = new ListView();
        accomList.setMinSize(425, 100);
        accomList.setMaxHeight(200);

        //Add the Accommodation information to the ListView. -EB
        for (Lot obLot : obBookingsLedger.getLotList())
        {
            accomList.getItems().add(obLot);
        }

        paneCentre.getChildren().add(accomList);

        // Creating the bottom pane for the "Back" and "Confirm" buttons. -EB
        HBox paneBottom = new HBox();
        paneBottom.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        //Create the nodes to be added to the Bottom Pane. -EB
        final Pane Spacer = new Pane();
        HBox.setHgrow(Spacer, Priority.ALWAYS);
        Spacer.setMinSize(10, 1);

        Button btBack = new Button("Back");
        Button btConfirm = new Button("Confirm");

        //Code for clicking Back. Takes closes the current window. -EB
        btBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Back();
            }
        });



        //Adding the nodes to the Bottom Pane. -EB
        paneBottom.getChildren().add(btBack);
        paneBottom.getChildren().add(Spacer);
        paneBottom.getChildren().add(btConfirm);

        mainPane.setTop(paneTop);
        mainPane.setBottom(paneBottom);
        mainPane.setCenter(paneCentre);

        //Creates the second Scene for the editing section. -EB
        BorderPane secondPane = new BorderPane();

        HBox paneBottom2 = new HBox();
        paneBottom2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

            //Create the nodes to be added to the Bottom Pane. -EB
        final Pane Spacer2 = new Pane();
        HBox.setHgrow(Spacer2, Priority.ALWAYS);
        Spacer2.setMinSize(10, 1);

        Button btBack2 = new Button("Back");
        Button btConfirm2 = new Button("Confirm");

            //Creating the center gridpane for the edit fields. -EB
        GridPane gPane2 = new GridPane();
        gPane2.setAlignment(Pos.CENTER);
        gPane2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gPane2.setHgap(10);
        gPane2.setVgap(10);

            //Adding nodes to center pane. -EB
        Label lblID = new Label("Accomodation ID:");
        txtID = new Text("");
        Label lbltype = new Label("Accommodation Type:");
        cboType = new ComboBox<>();
        Label lblAvailable = new Label("Availability:");
        cboAvailable = new ComboBox<>();
        txtReason = new TextArea();
        txtReason.setMaxSize(300, 100);
        txtReason.setVisible(false);

        gPane2.add(lblID, 0, 0);
        gPane2.add(txtID, 1, 0);
        gPane2.add(lbltype, 0, 1);
        gPane2.add(cboType, 1, 1);
        gPane2.add(lblAvailable, 0, 2);
        gPane2.add(cboAvailable, 1, 2);
        gPane2.add(txtReason, 0, 3, 2, 3);

        String[] aTypes = {"Non-Serviced Individual", "Serviced Individual", "Non-Serviced Group", "Serviced Group", "Cabin", "Deluxe Cabin"};
        cboType.getItems().addAll(aTypes);

        String[] aAvails = {"Available", "Not Available"};
        cboAvailable.getItems().addAll(aAvails);

            //Functionality for adding removal reason. -EB
        cboAvailable.setOnAction(e -> setAreaVisibility(txtReason));

            //Adding the nodes to the Bottom Pane. -EB
        paneBottom2.getChildren().add(btBack2);
        paneBottom2.getChildren().add(Spacer2);
        paneBottom2.getChildren().add(btConfirm2);

        secondPane.setBottom(paneBottom2);
        secondPane.setCenter(gPane2);

            //Confirm Button Code -EB
        btConfirm2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int nID = Integer.parseInt(txtID.getText());

                Lot editLot = obBookingsLedger.querySearchCampsite(nID);


                ConfirmEdit(editLot);

                EditAccommodationGUI.accomList.getItems().clear();

                for (Lot obLot1 : obBookingsLedger.getLotList())
                {
                    accomList.getItems().add(obLot1);
                }

            }
        });





        //Code for clicking Back. Takes closes the current window. -EB
        btBack2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                backToEdit();
            }
        });


        scene2 = new Scene(secondPane, 500, 500);

        //Click handler for Confirm button. Takes you to the edit section. -EB
        btConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (accomList.getSelectionModel().getSelectedIndex() == -1)
                {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please select an Accommodation before clicking confirm.", ButtonType.CLOSE);
                    error.showAndWait();
                    if (error.getResult() == ButtonType.CLOSE)
                    {
                        error.close();
                    }
                }
                else
                {
                    Lot listLot = obBookingsLedger.querySearchCampsite(accomList.getSelectionModel().getSelectedIndex());
                    Confirm(listLot);
                }






            }
        });



        //Setting the initial Scene for when the Window is loaded in. -EB
        scene = new Scene(mainPane, 500, 500);
        this.setTitle("Edit Accommodation");
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    //Sets the visibility of the TextArea depending on the setting of the availability. -EB
    public void setAreaVisibility(TextArea txtArea)
    {
        txtArea.setVisible(!txtArea.isVisible());

    }

    //Closes the window. -EB
    public void Back()
    {
        this.close();
    }

    //Opens the window for editing the specific Accommodation and sets the combo boxes to the values of the Accommodation. -EB
    public void Confirm(Lot obLot)
    {

        this.setScene(scene2);

        String sID = Integer.toString(obLot.getLotID());
        txtID.setText(sID);

        switch (obLot.getLotType())
        {
            case ServicedIndividual:
                cboType.setValue("Serviced Individual");
                break;

            case NonServicedIndividual:
                cboType.setValue("Non-Serviced Individual");
                break;

            case ServicedGroup:
                cboType.setValue("Serviced Group");
                break;

            case NonServicedGroup:
                cboType.setValue("Non-Serviced Group");
                break;

            case Cabin:
                cboType.setValue("Cabin");
                break;

            case DeluxeCabin:
                cboType.setValue("Deluxe Cabin");
                break;

            default:
                break;


        }

        if (obLot.getAvailability())
        {
            cboAvailable.setValue("Available");
            txtReason.setVisible(false);
        }
        else
        {
            cboAvailable.setValue("Not Available");
            txtReason.setVisible(true);

        }


    }

    public void ConfirmEdit(Lot obLot)
    {
        String sReason = txtReason.getText();


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

                obBookingsLedger.getLotList().set(obLot.getLotID(), obLot);
                txtReason.setText("");
                obLot.setRemovalReason(sReason);

                Alert confirm = new Alert(Alert.AlertType.INFORMATION, "Accommodation Successfully Edited.", ButtonType.CLOSE);
                confirm.showAndWait();
                if (confirm.getResult() == ButtonType.CLOSE)
                {
                    confirm.close();
                }

                this.setScene(scene);

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
                    obBookingsLedger.getLotList().set(obLot.getLotID(), obLot);
                    obLot.setAvailability(false);
                    txtReason.setText("");
                    obLot.setRemovalReason(sReason);

                    Alert confirm2 = new Alert(Alert.AlertType.INFORMATION, "Accommodation Successfully Edited.", ButtonType.CLOSE);
                    confirm2.showAndWait();
                    if (confirm2.getResult() == ButtonType.CLOSE)
                    {
                        confirm2.close();
                    }

                    this.setScene(scene);
                }


                break;

            default:
                break;
        }






    }

    //Returns back to the list of Accommodations. -EB
    public void backToEdit()
    {
        this.setScene(scene);
    }


}
