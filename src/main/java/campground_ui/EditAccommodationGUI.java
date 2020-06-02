package campground_ui;

import campground_data.BookingsLedger;
import campground_data.Lot;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditAccommodationGUI extends Stage {

    BookingsLedger BL;
    Scene scene;
    Scene scene2;

    public EditAccommodationGUI(Stage primaryStage) {
        BL = new BookingsLedger();
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
        BL.addAccommodation(obLot1);
        BL.addAccommodation(obLot2);
        BL.addAccommodation(obLot3);
        BL.addAccommodation(obLot4);
        BL.addAccommodation(obLot5);
        BL.addAccommodation(obLot6);
        BL.addAccommodation(obLot7);
        BL.addAccommodation(obLot8);
        BL.addAccommodation(obLot9);
        BL.addAccommodation(obLot10);
        BL.addAccommodation(obLot11);

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
        paneCentre.setAlignment(Pos.TOP_CENTER);

        //Adding the ListView to the centre pane. -EB
        ListView accomList = new ListView();
        accomList.setMinSize(425, 100);
        accomList.setMaxHeight(200);

        for (Lot obLot : BL.getLotList())
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
        Text txtID = new Text("");
        Label lbltype = new Label("Accommodation Type");
        ComboBox cboType = new ComboBox();

            //Adding the nodes to the Bottom Pane. -EB
        paneBottom2.getChildren().add(btBack2);
        paneBottom2.getChildren().add(Spacer2);
        paneBottom2.getChildren().add(btConfirm2);

        secondPane.setBottom(paneBottom2);
        secondPane.setCenter(gPane2);





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
                Lot listLot = BL.querySearchCampsite(accomList.getSelectionModel().getSelectedIndex());
                System.out.println(listLot.toString());
                Confirm(listLot);
            }
        });




        scene = new Scene(mainPane, 500, 500);
        this.setTitle("Edit Accommodation");
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    public void Back()
    {
        this.close();
    }

    public void Confirm(Lot obLot)
    {
        this.setScene(scene2);
    }

    public void backToEdit()
    {
        this.setScene(scene);
    }
}
