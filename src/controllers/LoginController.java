package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField meno;
    @FXML
    private PasswordField heslo;
    @FXML
    private Label errorText;
    @FXML
    private Label errorText1;
    @FXML
    private Button loginButton;

    private Connection connection;

    /* TO DO
        Update onLoginButtonClick chybne meno || chybne heslo
     */
    public void onLoginButtonClick(ActionEvent event) throws IOException{
        System.out.println("Uve clicked on LoginButton");
        if (isAdminLoggedIn() == false) {
            System.out.println("Wrong name or password!");
            errorText.setText("Chybne meno.");
            //errorText1.setText("Chybne heslo.");
        } else if (isAdminLoggedInPass() == false) {
            System.out.println("Wrong pass");
            errorText1.setText("Chybne heslo.");
        } else {
            System.out.println("Successfuly logged in.");
            try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("admin");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
            meno.setText("");
            heslo.setText("");
            errorText.setText("");
            errorText1.setText("");
        }
    }

    private boolean isAdminLoggedIn () {
        String name = this.meno.getText();
        //String pass = this.heslo.getText();
        try {
            String query = "SELECT * FROM pizza_app.administracia WHERE meno = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            //preparedStatement.setString(2, pass);

            ResultSet rs = preparedStatement.executeQuery() ;
            if(rs.next()) {
                return true ;
            }else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isAdminLoggedInPass () {
        String pass = this.heslo.getText();

        try {
            String query = "SELECT * FROM pizza_app.administracia WHERE heslo = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, pass);

            ResultSet rs = preparedStatement.executeQuery() ;
            if(rs.next()) {
                return true ;
            }else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String url = "jdbc:mysql://localhost:3306/pizza_app?useSSL=false";
        String user = "root";
        String password = "root";

        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
