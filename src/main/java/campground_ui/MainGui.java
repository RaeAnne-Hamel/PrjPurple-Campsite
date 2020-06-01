package campground_ui;

import campground_data.BookingsLedger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class MainGui extends Application {

    public static BookingsLedger BookingLedger = new BookingsLedger();


    @Override
    public void start(Stage stage) {
                javafx.scene.control.Button btnExit = new javafx.scene.control.Button("Exit");

                Pane mainPane = new Pane();
                Circle obCircle = new Circle();
                obCircle.setRadius(50);
                obCircle.radiusProperty().bind(mainPane.widthProperty().divide(4));
                obCircle.setStroke(Color.PINK);
                obCircle.setStrokeWidth(30);
                obCircle.setFill(Color.YELLOW);


                //TextField txtText = new TextField();

               // mainPane.getChildren().addAll(obCircle, btnExit);

                mainPane.getChildren().add(btnExit);
                stage.setScene(new Scene(mainPane, 400, 600));
                stage.setTitle("Main Window");
                stage.show();
            }

            public static void main(String[] args) {
                launch(args);
            }
}