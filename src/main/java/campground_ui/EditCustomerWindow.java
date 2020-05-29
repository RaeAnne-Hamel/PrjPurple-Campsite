package campground_ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditCustomerWindow extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        TextField txtName = new TextField();
        TextField txtAddress = new TextField();
        TextField txtCountry = new TextField();
        TextField txtProvince = new TextField();
        TextField txtCity = new TextField();
        TextField txtPostal = new TextField();
        TextField txtEmail = new TextField();
        TextField txtPhone = new TextField();
        TextField txtSecPhone = new TextField();
        TextField txtFax = new TextField();

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

        GridPane obGrid = new GridPane();
        obGrid.setVgap(5);
        obGrid.setHgap(5);

        obGrid.addColumn(0, lblName, lblAddress);
        obGrid.addColumn(1, txtName, txtAddress);

        stage.setTitle("Edit Customer");
        stage.setScene(new Scene(obGrid, 400, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
