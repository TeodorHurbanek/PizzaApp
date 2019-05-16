package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KosAdminController implements Initializable {

    @FXML
    private TableView<ModelTableAdmin> tableAdmin2;
    @FXML
    private TableColumn<ModelTableAdmin, String> col_id_obj;
    @FXML
    private TableColumn<ModelTableAdmin, String> col_nazov_obj;
    @FXML
    private TableColumn<ModelTableAdmin, String> col_cena_obj;
    @FXML
    private TableColumn<ModelTableAdmin, String> col_meno_obj;
    @FXML
    private TableColumn<ModelTableAdmin, String> col_ulica_obj;
    @FXML
    private TableColumn<ModelTableAdmin, String> col_cislo_domu_obj;
    @FXML
    private TableColumn<ModelTableAdmin, String> col_tel_cislo_obj;

    ObservableList<ModelTableAdmin> observableList2 = FXCollections.observableArrayList();

    @FXML
    private Label errorTextKosAdmin;
    @FXML
    private TextField idKosAdmin;

    public void onAdminClick(ActionEvent event) {
        System.out.println("U ve clicked on AdminSceneButton.");

        try {

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("admin");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onVymazatKosAdminClick(ActionEvent event) {
        System.out.println("U ve clicked on VymazatKosAdminButton.");

        if (idKosAdmin.getText().isEmpty() == true) {
            System.out.println("Empty idFiled!");
            errorTextKosAdmin.setText("Vyplnte ID objednavky pre vymazanie!");
        } else {
            try {
                Connection connection = DbConnector.getConnection();

                PreparedStatement st = connection.prepareStatement("DELETE FROM pizza_app.objednavky WHERE id_objednavky = " + idKosAdmin.getText() + ";");
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            idKosAdmin.setText("");
            errorTextKosAdmin.setText("Vymazane");
        }
    }

    public void onUkazKosAdminButton(ActionEvent event) {
        System.out.println("U ve clicked on UkazKosAdminButton");

        tableAdmin2.getItems().clear();

        try {
            Connection connection = DbConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM pizza_app.objednavky");

            while (rs.next()) {
                observableList2.add(new ModelTableAdmin(
                        rs.getString("id_objednavky"), rs.getString("nazov"),
                        rs.getString("cena"), rs.getString("meno"), rs.getString("ulica"),
                        rs.getString("cislo_domu"), rs.getString("tel_cislo")));
            }

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection connection = DbConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM pizza_app.objednavky");

            while (rs.next()) {
                observableList2.add(new ModelTableAdmin(
                        rs.getString("id_objednavky"), rs.getString("nazov"),
                        rs.getString("cena"), rs.getString("meno"), rs.getString("ulica"),
                        rs.getString("cislo_domu"), rs.getString("tel_cislo")));
            }

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
        }

        col_id_obj.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nazov_obj.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cena_obj.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_meno_obj.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_ulica_obj.setCellValueFactory(new PropertyValueFactory<>("street"));
        col_cislo_domu_obj.setCellValueFactory(new PropertyValueFactory<>("numberStreet"));
        col_tel_cislo_obj.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


        tableAdmin2.setItems(observableList2);
    }
}
