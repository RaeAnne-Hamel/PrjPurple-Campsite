package campground_ui;

import campground_data.BookingsLedger;
import campground_data.Customer;
import campground_data.Lot;
import campground_data.Reservation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainGui extends Application {

    public static BookingsLedger obBookingLedger ;

    ArrayList<Customer> obCustList;
    ArrayList<Reservation> obResList;

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

                obBookingLedger = new BookingsLedger();


        /**
         * TEST DATA!!!!!!!!!!!!!!
         */

        obCustList = new ArrayList<>();
        obResList = new ArrayList<>();

        Date startDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date endDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 13).getTime();

        Customer obCustomer = new Customer(0,"John Doe","Addr","Email@Email",1L,1L,1L,1,true,1);
        Customer obCustomer1 = new Customer(1,"Doe John","Addr","Email@Email",1L,1L,1L,1,true,1);

        obCustList.add(obCustomer);
        obCustList.add(obCustomer1);

        Reservation testReservation = new Reservation(new Lot(),startDate,endDate,obCustList,9, 0);
        Reservation testReservation2 = new Reservation(new Lot(),startDate,endDate,obCustList,9, 1);

        obResList.add(testReservation);
        obResList.add(testReservation2);


        /**
         * DELETE!!!!!!!!!!!!!!!!!!!!!!
         */





        mainPane.getChildren().add(btnExit);
                stage.setScene(new Scene(mainPane, 400, 600));
                stage.setTitle("Main Window");
                stage.show();
            }

            public static void main(String[] args) {
                launch(args);
            }
}