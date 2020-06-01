package campground_ui;

import campground_data.Customer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    TextField txtPhone2;
    TextField txtPhone3;
    TextField txtSecPhone;
    TextField txtSecPhone2;
    TextField txtSecPhone3;
    TextField txtFax;
    TextField txtFax2;
    TextField txtFax3;
    Button btnConfirm;


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
        String sPhone = String.valueOf(obCustomer.getPhone());
        txtPhone = new TextField(sPhone.substring(0,3));
        txtPhone2 = new TextField(sPhone.substring(3,6));
        txtPhone3 = new TextField(sPhone.substring(6));
        String sSecPhone = String.valueOf(obCustomer.getSecPhone());
        txtSecPhone = new TextField(sSecPhone.substring(0,3));
        txtSecPhone2 = new TextField(sSecPhone.substring(3,6));
        txtSecPhone3 = new TextField(sSecPhone.substring(6));
        String sFax = String.valueOf(obCustomer.getFax());
        txtFax = new TextField(sFax.substring(0,3));
        txtFax2 = new TextField(sFax.substring(3,6));
        txtFax3 = new TextField(sFax.substring(6));

        txtName.setEditable(false);
        txtName.setMinWidth(100);
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

        Label lblID = new Label("ID");
        Label lblGetID = new Label(String.valueOf(obCustomer.getCustomerID()));
        Label lblName = new Label("Name");
        Label lblAddress = new Label("Address");
        Label lblCountry = new Label("Country");
        Label lblProvince = new Label("Province/State");
        Label lblCity = new Label("City");
        Label lblPostal = new Label("Postal Code");
        Label lblEmail = new Label("Email");
        Label lblPhone = new Label("Phone Number");
        Label lblSecPhone = new Label("Secondary Phone");
        lblSecPhone.setMinWidth(100);
        Label lblFax = new Label("Fax Number");

        BorderPane obBorder = new BorderPane();
        HBox obBox = new HBox();
        GridPane obGrid = new GridPane();

        obBorder.setBottom(obBox);
        obBorder.setCenter(obGrid);

        obGrid.setVgap(10);
        obGrid.setHgap(10);
        obGrid.setAlignment(Pos.CENTER_LEFT);
        obGrid.setPadding(new Insets(25, 25, 25, 25));

        obGrid.addColumn(0, lblID, lblName, lblAddress, lblCountry, lblProvince, lblCity, lblPostal, lblEmail,
                lblPhone, lblSecPhone, lblFax);

        obGrid.add(lblGetID, 1, 0);
        obGrid.add(txtName, 1, 1, 3, 1);
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

        Button btnBack = new Button("Back to Home");
        Button btnEdit = new Button("Edit");
        btnConfirm = new Button("Confirm");
        btnConfirm.setDisable(true);

        btnEdit.setOnAction(e -> Edit());
        btnConfirm.setOnAction(e -> {
            String sPhone = "";
            String sVal = obCustomer.updateCustomer(txtName.getText(), txtAddress.getText(),
                            txtProvince.getText(), txtCity.getText(), txtPostal.getText(), txtCountry.getText(), txtEmail.getText(),
                            Long.parseLong(txtPhone.getText()), Long.parseLong(txtFax.getText()), Long.parseLong(txtSecPhone.getText()));
            if (sVal.equals("Successfully changed")){
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
        });

        obBox.getChildren().addAll(btnBack, btnEdit, btnConfirm);

        stage.setTitle("Edit Customer Information");
        stage.setScene(new Scene(obBorder, 500, 600));
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

    public static void main(String[] args) {
        launch(args);
    }
}
