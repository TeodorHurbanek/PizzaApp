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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KosController implements Initializable {

    @FXML
    private TableView<ModelTableKos> tableAdmin2;
    @FXML
    private TableColumn<ModelTableKos, String> col_id_kos;
    @FXML
    private TableColumn<ModelTableKos, String> col_nazov_kos;
    @FXML
    private TableColumn<ModelTableKos, String> col_cena_kos;

    ObservableList<ModelTableKos> observableList3 = FXCollections.observableArrayList();

    @FXML
    private Label errorKosText;
    @FXML
    private TextField idKos;
    @FXML
    private Label countCena;

    public  void onVymazatKosButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on VymazatButton.");

        if (idKos.getText().isEmpty() == true) {
            System.out.println("Empty idKos!");
            errorKosText.setText("Vyplnte ID objednavky pre vymazanie!");
        } else {

            try {
                Connection connection = DbConnector.getConnection();

                PreparedStatement st = connection.prepareStatement("DELETE FROM pizza_app.objednavky WHERE id_objednavky = " + idKos.getText() + ";");
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Product have been deleted.");
            idKos.setText("");
            errorKosText.setText("Uspesne vymazane.");
        }
    }

    public  void onPokracovatKosButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on PokracovatButton.");

        try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("kosAdresa");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DbConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT id_objednavky, nazov, cena FROM pizza_app.objednavky");

            while (rs.next()) {
                observableList3.add(new ModelTableKos(
                        rs.getString("id_objednavky"), rs.getString("nazov"),
                        rs.getString("cena")));
            }

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
        }

        col_id_kos.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nazov_kos.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cena_kos.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableAdmin2.setItems(observableList3);
    }
}
