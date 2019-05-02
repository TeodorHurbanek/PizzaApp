package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public BorderPane mainPage;

    @FXML
    private TextField meno;
    @FXML
    private PasswordField heslo;
    @FXML
    private Label errorText;
    @FXML
    private Label errorText1;

    private Connection connection;

    public void onLoginButtonClick(ActionEvent event) {
        String name = this.meno.getText();
        String pass = this.heslo.getText();

        /*try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("");

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    private Parent loadFX(String name) {

        String pathToMain = "../sample/"+ name +".fxml";

        URL mainURL = getClass().getResource(pathToMain);

        Parent parent = null;
        try {
            parent = FXMLLoader.load(mainURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*String url = "jdbc:mysql://localhost:3306/pizza_app?useSSL=false";
        String user = "root";
        String password = "root";

        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            e.printStackTrace();
        }*/
    }
}
