package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController implements Initializable {

    private Connection connection;

    @FXML
    private TableView<ModelTable> tableAdmin;
    @FXML
    private TableColumn<ModelTable, String> col_id;
    @FXML
    private TableColumn<ModelTable, String> col_nazov;
    @FXML
    private TableColumn<ModelTable, String> col_ingrediencie;
    @FXML
    private TableColumn<ModelTable, String> col_cena;

    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    public void onVymazatButtonClick(ActionEvent event)  {
        System.out.println("U ve clicked on VymazatButton");
    }

    public void onLogoutButtonClick(ActionEvent event) {
        System.out.println("Admin have loged out.");
        try {
            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("login");

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

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM pizza_app.menu");

            while (rs.next()){
                observableList.add(new ModelTable(
                        rs.getString("id_pizza"), rs.getString("nazov_pizza"),
                        rs.getString("ingrediencie"), rs.getString("cena")));
            }

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nazov.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ingrediencie.setCellValueFactory(new PropertyValueFactory<>("ingrediencies"));
        col_cena.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableAdmin.setItems(observableList);
    }
}
