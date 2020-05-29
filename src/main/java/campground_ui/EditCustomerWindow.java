package campground_ui;

import campground_data.Customer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EditCustomerWindow extends Application {
    private Customer obCustomer;

//    public EditCustomerWindow(Customer obCustomer)
//    {
//        this.obCustomer = obCustomer;
//    }

    TextField txtName;
    TextField txtAddress;
    TextField txtCountry;
    TextField txtProvince;
    TextField txtCity;
    TextField txtPostal;
    TextField txtEmail;
    TextField txtPhone;
    TextField txtSecPhone;
    TextField txtFax;


    @Override
    public void start(Stage stage) throws Exception {
        obCustomer = new Customer("bob", "215 bob street",
                "BC", "VA", "S7N4V2", "Canada", "bob@bob.com",
                1123456789,1037801234, 1033144567, 4, true);

        txtName = new TextField(obCustomer.getName());
        txtAddress = new TextField(obCustomer.getAddress());
        txtCountry = new TextField(obCustomer.getCountry());
        txtProvince = new TextField(obCustomer.getProvince());
        txtCity = new TextField(obCustomer.getCity());
        txtPostal = new TextField(obCustomer.getPostal());
        txtEmail = new TextField(obCustomer.getEmail());
        txtPhone = new TextField(String.valueOf(obCustomer.getPhone()));
        txtSecPhone = new TextField(String.valueOf(obCustomer.getSecPhone()));
        txtFax = new TextField(String.valueOf(obCustomer.getFax()));

        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtCity.setEditable(false);
        txtCountry.setEditable(false);
        txtPostal.setEditable(false);
        txtProvince.setEditable(false);
        txtEmail.setEditable(false);
        txtPhone.setEditable(false);
        txtSecPhone.setEditable(false);
        txtFax.setEditable(false);

        Label lblID = new Label("ID: ");
        Label lblGetID = new Label(String.valueOf(obCustomer.getCustomerID()));
        Label lblName = new Label("Name: ");
        Label lblAddress = new Label("Address: ");
        Label lblCountry = new Label("Country:" );
        Label lblProvince = new Label("Province/State: ");
        Label lblCity = new Label("City: ");
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

        Button btnBack = new Button("Back to Home");
        Button btnEdit = new Button("Edit");
        Button btnConfirm = new Button("Confirm");

        btnEdit.setOnAction(e -> Edit());

        obBox.getChildren().addAll(btnBack, btnEdit, btnConfirm);

        stage.setTitle("Edit Customer");
        stage.setScene(new Scene(obBorder, 400, 600));
        stage.show();
    }

    private void Edit(){
        txtName.setEditable(true);
        txtAddress.setEditable(true);
        txtCity.setEditable(true);
        txtCountry.setEditable(true);
        txtPostal.setEditable(true);
        txtProvince.setEditable(true);
        txtEmail.setEditable(true);
        txtPhone.setEditable(true);
        txtSecPhone.setEditable(true);
        txtFax.setEditable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
