package campground_ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import campground_data.*;

import java.util.Date;

public class TransactionGUI  extends Application {
    //this is  just test information so that i can see how the grid works.
    Reservation testRes = new Reservation(null, 3, new Date(), new Date(), new Lot());
    //this will dynamically change as we make edits to the reservation
    Text scenetitle = new Text(testRes.getTransaction().toString()); //this is the information for the current reservation

    @Override
    public void start(Stage stage) throws Exception {
        //you will need to pass in the transaction you want to edit from
        //the reservation.
        GridPane grid = EditTransaction(testRes.getTransaction());

        stage.setScene(new Scene(grid, 600, 600));
        stage.setTitle("Pricing and Payment Information");
        stage.show();
    }

    /**
     * this will take in the transactions that we will want to edit from the reservation
     * this will return a GridPane that will be able to edit these section of the
     * Transaction that you pass though.
     * @param transaction
     * @return
     */
    private GridPane EditTransaction(Transaction transaction)
    {
        GridPane grid = new GridPane();
        //have all the elements letf align
        grid.setAlignment(Pos.CENTER_LEFT);
        //make it look preatty and uniform
        grid.setHgap(10);
        grid.setVgap(10);
        //add in padding for readability
        grid.setPadding(new Insets(25, 25, 25, 25));


//this is the information for the form section.
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid.add(scenetitle, 0, 0, 2, 1);

        //creat a place for the user to put in a new Payment Type.
        Label paymenttype = new Label("Payment Type:");
        grid.add(paymenttype, 0, 1);
        //a combo Box that will set the update the information title above.
        ComboBox<PaymentType> PaymentTypeBox = PaymentTypeCombo(transaction);
        grid.add(PaymentTypeBox, 1, 1);


//this is the section of the form that will change the payment Method.
        Label paymentMethod = new Label("Payment Method:");
        grid.add(paymentMethod, 0, 2);
        //Combo Box for the Payment Method
        ComboBox<PaymentMethod> PaymentMethodBox = PaymentMethodCombo(transaction);
        grid.add(PaymentMethodBox, 1, 2);

//this is the discount Informtion
        Label discountinfo = new Label("Discount:");
        grid.add(discountinfo, 0, 3);
        //place a textinput and a empty label to display an error if needed
        TextField discountBox = new TextField(); //error handeling will be in the Save button.
        grid.add(discountBox,1,3);


//this is the price information
        Label Priceinfo = new Label("Price:");
        grid.add(Priceinfo, 0, 4);
        //place a textinput for the price infomration
        TextField Price = new TextField(); //error handeling will be in the Save button.
        grid.add(Price,1,4);

//this is the total price of the reservation after the discount and everything has been applied.
        Label Total = new Label("Total Price:");
        grid.add(Total, 0, 5);
        //display the price after all of these changes
        //get the price of this reservation and display it.
        Text adustedPrice = new Text(Double.toString(transaction.obRes.getPrice()) + "$");
        adustedPrice.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(adustedPrice, 1,5);

//this radio button will set weather or not this reservation has been paid or still needs to be
        CheckBox paidBox = getPaidBox(transaction); //erroe handeling and setting will be in the save button.
        grid.add(paidBox, 2, 5);




//this will update all of the information for the current transaction and reservation.
        Button btnSave = new Button("Save");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

            }
        });
        //this button will clode the window and not update any of the inforamtion that was selected
        Button btnExit = new Button("Exit");
        //exit this
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().addAll(btnSave, btnExit);
        grid.add(hbBtn, 1, 7);

        //return the Grid pane we have made
        return grid;
    }

    /**
     * this method will create a check box that will either be
     * this being a paid reservation or is it still needs to be paid.
     * @param transaction
     * @return
     */
    private CheckBox getPaidBox(Transaction transaction) {
        CheckBox returnThis = new CheckBox("Paid");
        if(transaction.obRes.getStatus())
        {
            returnThis.setSelected(true);
        }
        else
        {
            returnThis.setSelected(false);
        }

        return returnThis;
    }

    /**
     * this method takes in the current transaction information
     * it will create a comboBox with all the Payment Method infomration and make a combo Box
     * this combo box will chnage the payment method type.
     * @param transaction
     * @return
     */
    private ComboBox<PaymentMethod> PaymentMethodCombo(Transaction transaction) {

        ComboBox<PaymentMethod> comboBox = new ComboBox<PaymentMethod>();
        comboBox.getItems().addAll( //put all of the enumerations into the combo Box
                PaymentMethod.INPERSON,
                PaymentMethod.EMAIL,
                PaymentMethod.FAX,
                PaymentMethod.PHONE
        );
        // Create action event
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        //when a new type is selected change the value of the trnsaction
                        transaction.setPayMethod(comboBox.getValue());
                        //change the title so that is shows the change
                        scenetitle.setText(testRes.getTransaction().toString());
                    }
                };

        // Set on action
        comboBox.setOnAction(event);
        return comboBox;


    }

    /**
     * this method will take in the current transaction that we are editing
     * and creat a combo box that has all of the paymentTypes
     * when a new one is selected it will update the transaction that was sent in.
     * @param transaction
     * @return
     */
    private ComboBox<PaymentType> PaymentTypeCombo(Transaction transaction)
    {
        ComboBox<PaymentType> comboBox = new ComboBox<PaymentType>();
        comboBox.getItems().addAll( //put all of the enumerations into the combo Box
                PaymentType.DEBIT,
                PaymentType.CASH,
                PaymentType.CREDITCARD
        );
        // Create action event
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        //when a new type is selected change the value of the trnsaction
                        transaction.setPayType(comboBox.getValue());
                        //change the title so that is shows the change
                        scenetitle.setText(testRes.getTransaction().toString());
                    }
                };

        // Set on action
        comboBox.setOnAction(event);
        return comboBox;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
