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
    @FXML
    private TextField idAdresa;

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

        if (idAdresa.getText().isEmpty() == true) {
            System.out.println("Empty idAdresa!");
            objednavkaText.setText("Vyplnte ID objednavky");
        } else {
            if (menoAdresaField.getText().isEmpty() == true || ulicaAdresaField.getText().isEmpty() == true ||
                    cisloDomuAdresaField.getText().isEmpty() == true || telcisloAdresaField.getText().isEmpty() == true) {
                System.out.println("One or more isEmpty");
                objednavkaText.setText("Vyplnte vsetky polia!");
            } else if (telcisloAdresaField.getText().toCharArray().length < 10 || telcisloAdresaField.getText().toCharArray().length > 13) {
                System.out.println("phoneNumber has wrong lenght!");
                objednavkaText.setText("Zla dlzka cislo!");
            } else {
                try {
                    Connection connection = DbConnector.getConnection();

                    PreparedStatement st = connection.prepareStatement(
                            "UPDATE pizza_app.objednavky SET meno = '" + menoAdresaField.getText() + "', ulica = '"+ ulicaAdresaField.getText()
                                    +"', cislo_domu = '"+ cisloDomuAdresaField.getText() +"', tel_cislo = '"+
                                    telcisloAdresaField.getText() +"' WHERE id_objednavky = " + idAdresa.getText() + ";");
                    st.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                objednavkaText.setText("Uspesne objednane:)");
                menoAdresaField.setText("");
                ulicaAdresaField.setText("");
                cisloDomuAdresaField.setText("");
                telcisloAdresaField.setText("");
                idAdresa.setText("");
            }
            //else {
//            if (menoAdresaField.getText().isEmpty() == true) {
//                System.out.println("Empty MenoAdresa!");
//                objednavkaText.setText("Vyplnte meno!");
//            } /*else {
//                /*try {
//                    Connection connection = DbConnector.getConnection();
//
//                    PreparedStatement st = connection.prepareStatement(
//                            "UPDATE pizza_app.objednavky SET meno = '" + menoAdresaField.getText() + "' WHERE id_objednavky = " + idAdresa.getText() + ";");
//                    st.executeUpdate();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }*/
//                menoAdresaField.setText("");
//            }
//
//            if (ulicaAdresaField.getText().isEmpty() == true) {
//                System.out.println("Empty Ulica!");
//                objednavkaText.setText("Vyplnte ulicu!");
//            } else {
//                /*try {
//                    Connection connection = DbConnector.getConnection();
//
//                    PreparedStatement st = connection.prepareStatement(
//                            "UPDATE pizza_app.objednavky SET ulica = '" + ulicaAdresaField.getText() + "' WHERE id_objednavky = " + idAdresa.getText() + ";");
//                    st.executeUpdate();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }*/
//                ulicaAdresaField.setText("");
//            }
//
//            if (cisloDomuAdresaField.getText().isEmpty() == true) {
//                System.out.println("Empty CisloDomu!");
//                objednavkaText.setText("Vyplnte cislo domu!");
//            } else {
//                /*try {
//                    Connection connection = DbConnector.getConnection();
//
//                    PreparedStatement st = connection.prepareStatement(
//                            "UPDATE pizza_app.objednavky SET cislo_domu = '" + cisloDomuAdresaField.getText() + "' WHERE id_objednavky = " + idAdresa.getText() + ";");
//                    st.executeUpdate();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }*/
//                cisloDomuAdresaField.setText("");
//            }
//
//            int dlzkaTelCisla = telcisloAdresaField.getText().toCharArray().length;
//
//            if (telcisloAdresaField.getText().isEmpty() == true) {
//                System.out.println("Empty Tel.Cislo!");
//                objednavkaText.setText("Vyplnte tel.cislo!");
//            } else if (dlzkaTelCisla > 13  || dlzkaTelCisla < 10) {
//                System.out.println("Wrong lenght of PhoneNumber");
//                objednavkaText.setText("Nespravna dlzka cisla!");
//            } else {
//                /*try {
//                    Connection connection = DbConnector.getConnection();
//
//                    PreparedStatement st = connection.prepareStatement(
//                            "UPDATE pizza_app.objednavky SET tel_cislo = '" + telcisloAdresaField.getText() + "' WHERE id_objednavky = " + idAdresa.getText() + ";");
//                    st.executeUpdate();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }*/
//                telcisloAdresaField.setText("");
//            }
//            try {
//                Connection connection = DbConnector.getConnection();
//
//                PreparedStatement st = connection.prepareStatement(
//                        "UPDATE pizza_app.objednavky SET tel_cislo = '" + telcisloAdresaField.getText() + "' WHERE id_objednavky = " + idAdresa.getText() + ";");
//                st.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//
//            objednavkaText.setText("Uspesne objednane.");
//            idAdresa.setText("");
//        }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
