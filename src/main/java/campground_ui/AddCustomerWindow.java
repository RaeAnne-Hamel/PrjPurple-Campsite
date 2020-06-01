package campground_ui;

import campground_data.Customer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AddCustomerWindow extends Application {

//    public AddCustomerWindow(Customer obCustomer)
//    {
//        this.obCustomer = obCustomer;
//    }

    TextField txtName;
    TextField txtAddress;
    TextField txtCity;
    TextField txtProvince;
    TextField txtCountry;
    TextField txtPostal;
    TextField txtFax;
    TextField txtEmail;
    TextField txtPhone;
    TextField txtSecPhone;

    @Override
    public void start(Stage stage) throws Exception {
        Customer obCustomer = new Customer();

//        obCustomer.setName(txtName.getText());
//        obCustomer.setAddress(txtAddress.getText());
//        obCustomer.setCity(txtCity.getText());
//        obCustomer.setProvince(txtProvince.getText());
//        obCustomer.setCountry(txtCountry.getText());
//        obCustomer.setPostal(txtPostal.getText());
//        obCustomer.setEmail(txtEmail.getText());
//        obCustomer.setFax(Long.parseLong(txtFax.getText()));
//        obCustomer.setPhone(Long.parseLong(txtPhone.getText()));
//        obCustomer.setSecPhone(Long.parseLong(txtSecPhone.getText()));

        Label lblID = new Label("ID: ");
        Label lblGetID = new Label(String.valueOf(obCustomer.getCustomerID()));
        Label lblName = new Label("Name: ");
        Label lblAddress = new Label("Address: ");
        Label lblCity = new Label("City: ");
        Label lblProvince = new Label("Province/State: ");
        Label lblCountry = new Label("Country:" );
        Label lblPostal = new Label("Postal Code: ");
        Label lblEmail = new Label("Email: ");
        Label lblPhone = new Label("Phone Number: ");
        Label lblSecPhone = new Label("Secondary Phone Number: ");
        Label lblFax = new Label("Fax Number: ");

        BorderPane obBorder = new BorderPane();
        HBox obBox = new HBox();
        GridPane obGrid = new GridPane();

        obBorder.setBottom(obBox);
        obBorder.setCenter(obGrid);

        obGrid.setVgap(5);
        obGrid.setHgap(15);

        obGrid.addColumn(0, lblID, lblName, lblAddress, lblCountry, lblProvince, lblCity, lblPostal, lblEmail,
                lblPhone, lblSecPhone, lblFax);
        obGrid.addColumn(1,lblGetID, txtName, txtAddress, txtCountry, txtProvince, txtCity, txtPostal, txtEmail,
                txtPhone, txtSecPhone, txtFax);

        Button btnBack = new Button("Back");
        Button btnConfirm = new Button("Confirm");

        obBox.getChildren().addAll(btnBack, btnConfirm);

        stage.setTitle("Add Customer");
        stage.setScene(new Scene(obBorder, 400, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
