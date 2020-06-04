package campground_ui;

import campground_data.BookingsLedger;
import campground_data.Customer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Dylan Attwater
 * Adding a valid customer to the system
 */
public class AddCustomerWindow {
    private Customer obCustomer;

    //Declaring all the variables that'll be used in multiple methods.
    TextField txtName = new TextField();
    TextField txtLast = new TextField();
    TextField txtAddress = new TextField();
    TextField txtCountry = new TextField();
    TextField txtProvince = new TextField();
    TextField txtCity = new TextField();
    TextField txtPostal = new TextField();
    TextField txtEmail = new TextField();
    TextField txtPhone = new TextField();
    TextField txtPhone2 = new TextField();
    TextField txtPhone3 = new TextField();
    TextField txtSecPhone = new TextField();
    TextField txtSecPhone2 = new TextField();
    TextField txtSecPhone3 = new TextField();
    TextField txtFax = new TextField();
    TextField txtFax2 = new TextField();
    TextField txtFax3 = new TextField();
    Button btnConfirm = new Button();
    GridPane obGrid = new GridPane();
    Label lblGetID = new Label();

    public AddCustomerWindow(Stage stage) throws Exception {
        obCustomer = new Customer();

        //Setting up all the labels. The customer's ID isn't editable, so it uses a label rather than a text field.
        lblGetID = new Label(String.valueOf(obCustomer.getCustomerID()));
        Label lblName = new Label("First Name");
        Label lblLast = new Label("Last Name");
        Label lblAddress = new Label("Address");
        Label lblCountry = new Label("Country");
        Label lblProvince = new Label("Province/State");
        Label lblCity = new Label("City");
        Label lblPostal = new Label("Postal Code");
        Label lblEmail = new Label("Email");
        Label lblPhone = new Label("Phone Number");
        Label lblSecPhone = new Label("Secondary Phone");
        //Secondary Phone is wider than the other labels, so a minimum width is needed to ensure all text is visible.
        lblSecPhone.setMinWidth(100);
        Label lblFax = new Label("Fax Number");

        //Setting up the GridPane. The labels all go in the first column.
        obGrid = new GridPane();
        obGrid.addColumn(0, lblName, lblLast, lblAddress, lblCountry, lblProvince, lblCity, lblPostal, lblEmail,
                lblPhone, lblSecPhone, lblFax);

        //Everything is housed in a BorderPane. The HBox goes at the bottom to house the buttons, while the grid goes in the middle.
        BorderPane obBorder = new BorderPane();
        HBox obBox = new HBox();
        HBox obBoxRight = new HBox();
        obBorder.setBottom(obBox);
        obBorder.setCenter(obGrid);

        //Sets up spacing for the grid.
        obGrid.setVgap(10);
        obGrid.setHgap(10);
        obGrid.setAlignment(Pos.CENTER_LEFT);
        obGrid.setPadding(new Insets(25, 25, 25, 25));

        //Adds the buttons. Confirm starts out disabled until you actually edit some text.
        Button btnBack = new Button("Back to Home");
        btnBack.setOnAction(e -> stage.setScene(MainGui.mainScene));
        btnConfirm = new Button("Confirm");
        btnConfirm.setOnAction(e -> Confirm(stage));

        //All items are added to the grid. The non-phone entries are multiple columns wide, since they're larger.
        obGrid.add(txtName, 1, 0, 3, 1);
        obGrid.add(txtLast, 1, 1, 3, 1);
        obGrid.add(txtAddress, 1, 2, 3, 1);
        obGrid.add(txtCountry, 1, 3, 3, 1);
        obGrid.add(txtProvince, 1, 4, 3, 1);
        obGrid.add(txtCity, 1, 5, 3, 1);
        obGrid.add(txtPostal, 1, 6, 2, 1);
        obGrid.add(txtEmail, 1, 7, 3, 1);
        obGrid.add(txtPhone, 1, 8);
        obGrid.add(txtSecPhone, 1, 9);
        obGrid.add(txtFax, 1, 10);
        obGrid.add(txtPhone2, 2, 8);
        obGrid.add(txtSecPhone2, 2, 9);
        obGrid.add(txtFax2, 2, 10);
        obGrid.add(txtPhone3, 3, 8);
        obGrid.add(txtSecPhone3, 3, 9);
        obGrid.add(txtFax3, 3, 10);

        //Set up spacing and alignment for the buttons. A second HBox is used so that two buttons can be in the bottom right while the other's in the bottom left.
        obBox.setSpacing(300);
        obBox.setAlignment(Pos.CENTER);
        obBoxRight.getChildren().add(btnConfirm);
        obBoxRight.setSpacing(10);
        obBoxRight.setAlignment(Pos.BOTTOM_RIGHT);
        obBox.getChildren().addAll(btnBack, obBoxRight);

        //Sets up the scene and the stage.
        stage.setTitle("Add Customer Information");
        stage.setScene(new Scene(obBorder, 500, 500));
    }

    /**
     * Method for the confirm button. Will update the customer's information with what it finds in the TextFields, and give a success alert or an error alert
     * depending on whether the information is correct.
     */
    private void Confirm(Stage stage){
        //Set the customer information as the text inputted
        int length = MainGui.obBookingsLedger.aCustomer.size();
        obCustomer.setCustomerID(length++);
        obCustomer.setName(txtName.getText());
        obCustomer.setLast(txtLast.getText());
        obCustomer.setAddress(txtAddress.getText());
        obCustomer.setCity(txtCity.getText());
        obCustomer.setProvince(txtProvince.getText());
        obCustomer.setCountry(txtCountry.getText());
        obCustomer.setPostal(txtPostal.getText());
        obCustomer.setEmail(txtEmail.getText());

        //Concatenates the phone boxes into one string each.
        String sPhone = txtPhone.getText() + txtPhone2.getText() + txtPhone3.getText();
        String sSecPhone = txtSecPhone.getText() + txtSecPhone2.getText() + txtSecPhone3.getText();
        String sFax = txtFax.getText() + txtFax2.getText() + txtFax3.getText();
        //Having the fax or secondary phone fields blank will cause errors, so they're set to 0 if they don't exist.
        if (sFax.equals("")) {
            sFax = "0";
        }
        if (sSecPhone.equals(""))
        {
            sSecPhone = "0";
        }
        String sVal = "";

        //Try/catch block to prevent non-numbers from being entered in phone/fax fields.
        try {
            sVal = obCustomer.updateCustomer(obCustomer.getName(), obCustomer.getLast(), obCustomer.getAddress(),
                    obCustomer.getProvince(), obCustomer.getCity(), obCustomer.getPostal(), obCustomer.getCountry(),
                    obCustomer.getEmail(), Long.parseLong(sPhone), Long.parseLong(sFax), Long.parseLong(sSecPhone), 1);
        }
        catch (NumberFormatException exp){
            sVal = "Please enter only numbers in phone and fax fields.";
        }

        //An alert will pop up denoting success or failure. The error contents come from Customer's UpdateCustomer method.
        //If successful, all fields will revert to being non-editable.
        if (sVal.equals("Successfully added")){
            MainGui.obBookingsLedger.aCustomer.add(obCustomer);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Customer successfully added");
            alert.showAndWait();
            stage.setScene(MainGui.mainScene);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Detected");
            alert.setHeaderText("Not all fields were correctly entered.");
            alert.setContentText(sVal);
            alert.showAndWait();
        }

    }
}
