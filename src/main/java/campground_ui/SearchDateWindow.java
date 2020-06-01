package campground_ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SearchDateWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        //Grid pane will be used for Scene
        GridPane obGridPane = new GridPane();
        //VBox will hold both HBoxes
        VBox obFields = new VBox();
        //HBoxes will be used for entry fields
        HBox obFirstRow = new HBox();
        HBox obSecondRow = new HBox();

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

        //Increase Spacing in VBox
        obFields.setSpacing(12);

        //Set up VBox with HBoxes
        obFields.getChildren().addAll(obFirstRow,obSecondRow,obSearch);

        obGridPane.add(obFields,1,0);
        obGridPane.setPadding(new Insets(30,30,30,30));

        primaryStage.setTitle("Search for Available Accommodations");
        primaryStage.setScene(new Scene(obGridPane,950,500));
        primaryStage.show();
    }
}
