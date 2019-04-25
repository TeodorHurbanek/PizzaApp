package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    /* TO DO
    ...
     */
    private Connection connection;

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

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM menu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
