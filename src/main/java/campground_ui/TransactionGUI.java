package campground_ui;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import campground_data.*;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.util.Calendar;
import java.util.Date;


public class TransactionGUI extends Stage {

    //this will dynamically change as we make edits to the reservation
    private static Text scenetitle; //this is the information for the current reservation
    private static Label discountError; //this is an error handeling box.
    private static Label PriceError;
    private static Button btnSave, btnExit;


    /**
     * this will take in the transactions that we will want to edit from the reservation
     * this will return a GridPane that will be able to edit these section of the
     * Transaction that you pass though.
     * @param transaction
     * @return
     */
    public TransactionGUI(Transaction transaction)
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
        scenetitle = new Text(transaction.toString());
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
        Label discountinfo = new Label("Discount %:");
        grid.add(discountinfo, 0, 3);
        //place a textinput and a empty label to display an error if needed
        TextField discountBox = new TextField(); //error handeling will be in the Save button.
        // force the field to be numeric only
        discountBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    discountBox.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        grid.add(discountBox,1,3);
        discountError = new Label();
        grid.add(discountError, 2,3);


//this is the price information
        Label Priceinfo = new Label("Change Price:");
        grid.add(Priceinfo, 0, 4);
        //place a textinput for the price infomration
        TextField Price = new TextField(); //error handeling will be in the Save button.
        Price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    Price.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        grid.add(Price,1,4);
        PriceError = new Label();
        grid.add(PriceError, 2, 4);

//this is the total price of the reservation after the discount and everything has been applied.
        Label Total = new Label("Total Price:");
        grid.add(Total, 0, 5);
        //display the price after all of these changes
        //get the price of this reservation and display it.
        Text adustedPrice = new Text("$ CAD " + Double.toString(transaction.getPrice()));
        adustedPrice.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(adustedPrice, 1,5);

//this radio button will set weather or not this reservation has been paid or still needs to be
        CheckBox paidBox = getPaidBox(transaction); //erroe handeling and setting will be in the save button.
        grid.add(paidBox, 2, 5);

        //this button will clode the window and not update any of the inforamtion that was selected
        btnExit = new Button("Exit");
        btnExit.setOnAction(actionEvent -> {
            if(btnExit.getText().equalsIgnoreCase("Back to edit"))
            {
                btnExit.setText("Exit");
                btnSave.setText("Save");
            }
            else
            {
                this.close();
            }
        });

//this will update all of the information for the current transaction and reservation.
        btnSave = new Button("Save");
        btnSave.setOnAction(actionEvent ->  {
                //if all the errors have been shown and the data has been adjusted.
                if(btnSave.getText().equals("Confirm Changes"))
                {
                    this.close();
                    //i Would put the save method here !!!!!!
                }
                else {
                    //change the Exit button to a back button
                    btnExit.setText("Back to edit");
                    //change our button to a confirm button
                    btnSave.setText("Confirm Changes");
                    //check the input placed into the boxes
                    checkPrice(Price.getText(), transaction);
                    checkDiscount(discountBox.getText(), transaction);
                    //calculate a new ammount to dispaly for the toal price.
                    String newAmmount = changeNewammount(transaction.discount, transaction.getPrice(), transaction);
                    adustedPrice.setText("$ CAD " + newAmmount);


                }
        });

        //exit this
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().addAll(btnSave, btnExit);
        grid.add(hbBtn, 1, 7);

        //return the Grid pane we have made
        this.setScene(new Scene(grid, 600, 600));
        this.setTitle("Pricing and Payment Information");
        this.show();
    }

    /**
     * this method wil return a string ammount of how much the new reservation will cost
     * after all of the changes place in the boxes are applyied.
     * @param price
     * @param discount
     * @return
     */
    private static String changeNewammount(double discount, double price, Transaction transaction)
    {
        //if no discount if given then just display the price.
        if(discount == 0.0)
        {
            return Double.toString(transaction.getPrice());
        }
        else if(price == 0.0)
        {
            return Double.toString(price);
        }
        else
        {
            //find the discounted price
            price = price - (price * discount);
            return Double.toString(price);
        }
    }

    /**
     * this method will check the new price inputted into the text box if one if put into the text box
     * It will return no error if eveything is correct and place a error into PriceError if something is wrong
     * This Method uses the setPrice in the reservation method.
     * @param price - the new price
     * @param transaction - the current transaction we are working with.
     */
    private static void checkPrice(String price, Transaction transaction) {
        if(price.equals(""))
        {
            PriceError.setText("Price not changed");
        }
        else
        {
            Double newPrice = Double.parseDouble(price);

            if(newPrice == 0.0)
            {
                PriceError.setText("Price Not Set");
            }
            else
            {
                transaction.setPrice(newPrice);
                scenetitle.setText(transaction.toString());
            }
        }
    }

    /**
     * this method will check the discount and new price information
     * And will return a blank string depending on what was restuned from
     * the diecount method in the reservaation class.
     * @return
     */
    private static void checkDiscount(String discount, Transaction transaction) {
        if(discount.equals(""))
        {
            discountError.setText("No Discount added");
        }
        else
        {
            int newDiscount = Integer.parseInt(discount);
            Double results = transaction.setDiscount(newDiscount);
            if(results == transaction.obRes.getPrice())
            {
                discountError.setText("No Discount added");
            }
            else
            {
                discountError.setText("");
            }
        }
    }

    /**
     * this method will create a check box that will either be
     * this being a paid reservation or is it still needs to be paid.
     * @param transaction
     * @return
     */
    private static CheckBox getPaidBox(Transaction transaction) {
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
    private static ComboBox<PaymentMethod> PaymentMethodCombo(Transaction transaction) {

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
                        scenetitle.setText(transaction.toString());
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
    private static ComboBox<PaymentType> PaymentTypeCombo(Transaction transaction)
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
                        scenetitle.setText(transaction.toString());
                    }
                };

        // Set on action
        comboBox.setOnAction(event);
        return comboBox;

    }
}
