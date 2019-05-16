package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KosAdresaController implements Initializable {

    @FXML
    private Label objednavkaText;

    @FXML
    private TextField menoAdresaField;
    @FXML
    private TextField ulicaAdresaField;
    @FXML
    private TextField cisloDomuAdresaField;
    @FXML
    private TextField telcisloAdresaField;

    public void onSpatClick(ActionEvent event) {
        System.out.println("U ve clicked on SpatButton.");

        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("kos");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onObjednatButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on ObjednatButton.");

        if (menoAdresaField.getText().isEmpty() == true) {
            System.out.println("Empty MenoAdresa!");
            objednavkaText.setText("Vyplnte meno!");
        } else if (ulicaAdresaField.getText().isEmpty() == true) {
            System.out.println("Empty UlicaAdresa!");
            objednavkaText.setText("Vyplnte ulicu!");
        } else if (cisloDomuAdresaField.getText().isEmpty() == true) {
            System.out.println("Empty CisloDomu!");
            objednavkaText.setText("Vyplnte cislo domu!");
        } else if (telcisloAdresaField.getText().isEmpty() == true) {
            System.out.println("Empty TelCislo!");
            objednavkaText.setText("Vyplnte tel.cislo!");
        } else {
            String meno = menoAdresaField.getText();
            String ulica = ulicaAdresaField.getText();
            String cisloDomu = cisloDomuAdresaField.getText();
            String telCislo = telcisloAdresaField.getText();
            try {
                Connection connection = DbConnector.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                "INSERT INTO pizza_app.objednavky " +
                                        "(meno, ulica, cislo_domu, tel_cislo) " +
                                        "VALUES " +
                                        "(?, ?, ?, ?)");

                preparedStatement.setString(1, meno);
                preparedStatement.setString(2, ulica);
                preparedStatement.setString(3, cisloDomu);
                preparedStatement.setString(4, telCislo);

                int result = preparedStatement.executeUpdate();

                if (result == 1) {
                    System.out.println("INSERT INTO menu");
                    menoAdresaField.setText("");
                    ulicaAdresaField.setText("");
                    cisloDomuAdresaField.setText("");
                    telcisloAdresaField.setText("");
                    objednavkaText.setText("Uspesna objednavka");

                } else {
                    System.out.println("insert error");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
