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
        System.out.println("Uve clicked on LoginButton");
        if (isAdminLoggedIn() == false) {
            errorText.setText("Chybne meno.");
            errorText1.setText("Chybne heslo.");
        } else {
            meno.setText("");
            heslo.setText("");
            mainPage.setCenter(loadFX("admin"));
        }
    }

    private boolean isAdminLoggedIn () {
        String name = this.meno.getText();
        String pass = this.heslo.getText();
        try {
            String query = "SELECT * FROM pizza_app.administracia WHERE meno = ? and heslo = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);

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
