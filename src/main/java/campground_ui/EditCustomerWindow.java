package campground_ui;

import campground_data.Customer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Author: Dustin Wiebe
 * Class for the window where the user will edit a customer's information.
 */
public class EditCustomerWindow extends Application {
    private Customer obCustomer;

//    public EditCustomerWindow(Customer obCustomer)
//    {
//        this.obCustomer = obCustomer;
//    }

    //Declaring all the variables that'll be used in multiple methods.
    TextField txtName;
    TextField txtLast;
    TextField txtAddress;
    TextField txtCountry;
    TextField txtProvince;
    TextField txtCity;
    TextField txtPostal;
    TextField txtEmail;
    TextField txtPhone;
    TextField txtPhone2;
    TextField txtPhone3;
    TextField txtSecPhone = new TextField();
    TextField txtSecPhone2 = new TextField();
    TextField txtSecPhone3 = new TextField();
    TextField txtFax = new TextField();
    TextField txtFax2 = new TextField();
    TextField txtFax3 = new TextField();
    Button btnConfirm;
    GridPane obGrid;
    Label lblGetID;

    @Override
    public void start(Stage stage) throws Exception {
        obCustomer = new Customer("bob", "loblaw", "215 bob street",
                "BC", "VA", "S7N4V2", "Canada", "bob@bob.com",
                1123456789);

        //Setting up all the labels. The customer's ID isn't editable, so it uses a label rather than a text field.
        Label lblID = new Label("ID");
        Label lblName = new Label("Name");
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
        obGrid.addColumn(0, lblID, lblName, lblLast, lblAddress, lblCountry, lblProvince, lblCity, lblPostal, lblEmail,
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
        Button btnEdit = new Button("Edit");
        btnConfirm = new Button("Confirm");
        btnEdit.setOnAction(e -> Edit());
        btnConfirm.setOnAction(e -> Confirm());

        setupTextFields();

        //Set up spacing and alignment for the buttons. A second HBox is used so that two buttons can be in the bottom right while the other's in the bottom left.
        obBox.setSpacing(300);
        obBox.setAlignment(Pos.CENTER);
        obBoxRight.getChildren().addAll(btnEdit, btnConfirm);
        obBoxRight.setSpacing(10);
        obBoxRight.setAlignment(Pos.BOTTOM_RIGHT);
        obBox.getChildren().addAll(btnBack, obBoxRight);

        //Sets up the scene and the stage.
        stage.setTitle("Edit Customer Information");
        stage.setScene(new Scene(obBorder, 500, 500));
        stage.show();
    }

    /**
     * Method for adding all the TextFields to the GridPane. They will be populated with the contents of the added customer.
     */
    private void setupTextFields() {
        txtName = new TextField(obCustomer.getName());
        txtLast = new TextField(obCustomer.getLast());
        txtAddress = new TextField(obCustomer.getAddress());
        txtCountry = new TextField(obCustomer.getCountry());
        txtProvince = new TextField(obCustomer.getProvince());
        txtCity = new TextField(obCustomer.getCity());
        txtPostal = new TextField(obCustomer.getPostal());
        txtEmail = new TextField(obCustomer.getEmail());
        //Phone numbers are split into three fields to make it clear that we want an area code.
        String sPhone = String.valueOf(obCustomer.getPhone());
        txtPhone = new TextField(sPhone.substring(0,3));
        txtPhone2 = new TextField(sPhone.substring(3,6));
        txtPhone3 = new TextField(sPhone.substring(6));
        lblGetID = new Label(String.valueOf(obCustomer.getCustomerID()));

        //SecPhone and Fax will be 0 if they don't exist. Error handling is needed to prevent crashes from substring.
        if (obCustomer.getSecPhone() != 0)
        {
            String sSecPhone = String.valueOf(obCustomer.getSecPhone());
            txtSecPhone.setText(sSecPhone.substring(0,3));
            txtSecPhone2.setText(sSecPhone.substring(3,6));
            txtSecPhone3.setText(sSecPhone.substring(6));
        }
        if (obCustomer.getFax() != 0)
        {
            String sFax = String.valueOf(obCustomer.getFax());
            txtFax.setText(sFax.substring(0,3));
            txtFax2.setText(sFax.substring(3,6));
            txtFax3.setText(sFax.substring(6));
        }

        UnEditable();

        //All items are added to the grid. The non-phone entries are multiple columns wide, since they're larger.
        obGrid.add(lblGetID, 1, 0);
        obGrid.add(txtName, 1, 1, 3, 1);
        obGrid.add(txtLast, 1, 2, 3, 1);
        obGrid.add(txtAddress, 1, 3, 3, 1);
        obGrid.add(txtCountry, 1, 4, 3, 1);
        obGrid.add(txtProvince, 1, 5, 3, 1);
        obGrid.add(txtCity, 1, 6, 3, 1);
        obGrid.add(txtPostal, 1, 7, 2, 1);
        obGrid.add(txtEmail, 1, 8, 3, 1);
        obGrid.add(txtPhone, 1, 9);
        obGrid.add(txtSecPhone, 1, 10);
        obGrid.add(txtFax, 1, 11);
        obGrid.add(txtPhone2, 2, 9);
        obGrid.add(txtSecPhone2, 2, 10);
        obGrid.add(txtFax2, 2, 11);
        obGrid.add(txtPhone3, 3, 9);
        obGrid.add(txtSecPhone3, 3, 10);
        obGrid.add(txtFax3, 3, 11);
    }

    /**
     * A method to set all the TextFields to be not-editable. Also sets the confirm button to not be clickable.
     */
    private void UnEditable(){
        txtName.setEditable(false);
        txtLast.setEditable(false);
        txtAddress.setEditable(false);
        txtCity.setEditable(false);
        txtCountry.setEditable(false);
        txtPostal.setEditable(false);
        txtProvince.setEditable(false);
        txtEmail.setEditable(false);
        txtPhone.setEditable(false);
        txtPhone2.setEditable(false);
        txtPhone3.setEditable(false);
        txtSecPhone.setEditable(false);
        txtSecPhone2.setEditable(false);
        txtSecPhone3.setEditable(false);
        txtFax.setEditable(false);
        txtFax2.setEditable(false);
        txtFax3.setEditable(false);
        btnConfirm.setDisable(true);
    }

    /**
     * A method to set all the TextFields to be editable.
     */
    private void Edit(){
        txtName.setEditable(true);
        txtLast.setEditable(true);
        txtAddress.setEditable(true);
        txtCity.setEditable(true);
        txtCountry.setEditable(true);
        txtPostal.setEditable(true);
        txtProvince.setEditable(true);
        txtEmail.setEditable(true);
        txtPhone.setEditable(true);
        txtPhone2.setEditable(true);
        txtPhone3.setEditable(true);
        txtSecPhone.setEditable(true);
        txtSecPhone2.setEditable(true);
        txtSecPhone3.setEditable(true);
        txtFax.setEditable(true);
        txtFax2.setEditable(true);
        txtFax3.setEditable(true);
        btnConfirm.setDisable(false);
    }

    /**
     * Method for the confirm button. Will update the customer's information with what it finds in the TextFields, and give a success alert or an error alert
     * depending on whether the information is correct.
     */
    private void Confirm(){
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
            sVal = obCustomer.updateCustomer(txtName.getText(), txtLast.getText(), txtAddress.getText(),
                    txtProvince.getText(), txtCity.getText(), txtPostal.getText(), txtCountry.getText(), txtEmail.getText(),
                    Long.parseLong(sPhone), Long.parseLong(sFax), Long.parseLong(sSecPhone));
        }
        catch (NumberFormatException exp){
            sVal = "Please enter only numbers in phone and fax fields.";
        }

        //An alert will pop up denoting success or failure. The error contents come from Customer's UpdateCustomer method.
        //If successful, all fields will revert to being non-editable.
        if (sVal.equals("Successfully changed")){
            UnEditable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Customer information successfully updated.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Detected");
            alert.setHeaderText("Not all fields were correctly entered.");
            alert.setContentText(sVal);
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
