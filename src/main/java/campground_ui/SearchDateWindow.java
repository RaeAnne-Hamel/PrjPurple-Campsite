package campground_ui;

import campground_data.BookingsLedger;
import campground_data.Lot;
import campground_data.LotType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;


public class SearchDateWindow extends Stage {

    /**
     * This stage allows the user to enter Two dates and have a label show all the available accomodations -AE
     * @param parent
     */
    public SearchDateWindow(Stage parent){

        ArrayList<Lot> lotArray = new ArrayList<>();
        lotArray.add(new Lot(0));
        MainGui.obBookingsLedger.setLotList(lotArray);

        //Grid pane will be used for Scene
        GridPane obGridPane = new GridPane();
        //VBox will hold both HBoxes
        VBox obFields = new VBox();
        //HBoxes will be used for entry fields
        HBox obFirstRow = new HBox();
        HBox obSecondRow = new HBox();

        //HBox for holding buttons
        HBox obButtonRow = new HBox();

        Label obArrivalLabel = new Label("Arrival Date: (YYYY,M,D)\t\t\t");
        Label obDepartureLabel = new Label("Departure Date: (YYYY,M,D)\t\t");

        //Set font size of labels to 20
        obArrivalLabel.setFont(new Font(20));
        obDepartureLabel.setFont(new Font(20));

        //Because you cannot use a label multiple times we need to make a array of slashes.
        Label[] obSlash = new Label[4];
        for(int i=0;i<obSlash.length;i++)
        {
            obSlash[i] = new Label("\t/\t");
        }

        //Arrival Text Fields
        TextField obAYear = new TextField();
        TextField obAMonth = new TextField();
        TextField obADay = new TextField();

        //Departure Text Fields
        TextField obDYear = new TextField();
        TextField obDMonth = new TextField();
        TextField obDDay = new TextField();

        //Set up First Row
        obFirstRow.getChildren().addAll(obArrivalLabel,obAYear,obSlash[0],obAMonth,obSlash[1],obADay);

        //Set up Second Row
        obSecondRow.getChildren().addAll(obDepartureLabel,obDYear,obSlash[2],obDMonth,obSlash[3],obDDay);

        //Search Button
        Button obSearch = new Button("Search");
        obSearch.setFont(new Font(20));

        //Exit Button
        Button obExit = new Button("Exit");
        obExit.setFont(new Font(20));

        //Set up buttonRow
        obButtonRow.getChildren().addAll(obExit,obSearch);
        //Increase Spacing in buttonRow
        obButtonRow.setSpacing(12);

        //Increase Spacing in VBox
        obFields.setSpacing(12);

        //Set up VBox with HBoxes
        obFields.getChildren().addAll(obFirstRow,obSecondRow,obButtonRow);

        //Set up label for displaying Accommodations.
        //This label will be changed to reflect the accommodation list
        Label obAccommodations = new Label("");

        //Exit button will close window
        obExit.setOnAction(e-> { this.close(); });


        /**
         * This button contains all the code used to verify all fields are positive numbers,
         * Use those numbers to make a StartDate and EndDate, Retrieve a lot List using checkAvailability,
         * and output that lot List as a string into a label to display to the user. -AE
         */
        obSearch.setOnAction(new EventHandler<ActionEvent>() {
            //event handler needed to change label
            @Override
            public void handle(ActionEvent actionEvent) {

                //Verify all fields have positive numbers
                if (testForPosInt(obAYear.getText())
                        && testForPosInt(obAMonth.getText())
                        && testForPosInt(obADay.getText())
                        && testForPosInt(obDYear.getText())
                        && testForPosInt(obDMonth.getText())
                        && testForPosInt(obDDay.getText())) {


                    int[] arrivalDate = {
                            Integer.parseInt(obAYear.getText()),
                            Integer.parseInt(obAMonth.getText()),
                            Integer.parseInt(obADay.getText()),
                    };
                    int[] departureDate = {
                            Integer.parseInt(obDYear.getText()),
                            Integer.parseInt(obDMonth.getText()),
                            Integer.parseInt(obDDay.getText())
                    };

                    //Create Dates using numbers user has inputted
                    Date obStartDate = new GregorianCalendar(arrivalDate[0], arrivalDate[1], arrivalDate[2]).getTime();
                    Date obEndDate = new GregorianCalendar(arrivalDate[0], arrivalDate[1], arrivalDate[2]).getTime();

                    //Lot List loaded from dates
                    List<Lot> obLotList = MainGui.obBookingsLedger.checkAvailability(obStartDate, obEndDate);
                    String obOutput = "";
                    if(obLotList.size() > 0)
                    for (Lot obLot : obLotList) {

                        //String gets lotType as a properly formated string
                        String sLotType = lotTypeAsString(obLot.getLotType());
                        obOutput += sLotType + ", ID:" + obLot.getLotID() + "\n";
                    }
                    else{
                        System.out.println("None found");
                        obOutput = "No Accommodations Available";

                    }

                    //Display list
                    obAccommodations.setText(obOutput);
                    obAccommodations.setFont(new Font(20));

                }


                //If one of the fields is not a number display error
                else {
                    Alert obAlert = new Alert(Alert.AlertType.ERROR);
                    obAlert.setTitle("Error Detected");
                    obAlert.setHeaderText("All Fields must be positive numbers");
                    obAlert.showAndWait();
                }
            }

        });

        obGridPane.add(obFields,1,0);
        obGridPane.add(obAccommodations,1,2);
        obGridPane.setPadding(new Insets(30,30,30,30));

        this.setTitle("Search for Available Accommodations");
        this.setScene(new Scene(obGridPane,950,500));
        this.initOwner(parent);
        this.initModality(Modality.APPLICATION_MODAL);

    }

    private boolean testForPosInt(String sField)
    {
        //returns false if the string has any non numeric characters or if the number is negative
        return Pattern.compile("^\\d+$").matcher(sField).matches();

    }

    private String lotTypeAsString(LotType obType)
    {
        switch (obType) {
            case NonServicedIndividual:
                return "Non-Serviced Individual";

            case ServicedIndividual:
                return "Serviced Individual";
            case NonServicedGroup:
                return "Non-Serviced Group";
            case ServicedGroup:
                return "Serviced Group";
            case Cabin:
                return "Cabin";
            case DeluxeCabin:
                return "Deluxe Cabin";
            default:
                return "ERROR LOT TYPE NOT FOUND";
        }
    }


}
