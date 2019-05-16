package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController implements Initializable {

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

    private Connection connection;

    @FXML
    private TextField idMenu;
    @FXML
    private Label menuText;

    public void onAddClick(ActionEvent event) {
        System.out.println("U ve clicked on AddButton.");

        if (idMenu.getText().isEmpty() == true) {
            System.out.println("Empty IdMenu");
            menuText.setText("Vyplnte ID objednavky!");
        } else {
            String id = idMenu.getText();

            String obj = tableAdmin.getId();

            try {

                    Connection connection = DbConnector.getConnection();

                    PreparedStatement preparedStatementSelect =
                            connection.prepareStatement(
                                    "SELECT * FROM pizza_app.menu WHERE id_pizza = ?"
                            );

                preparedStatementSelect.setInt(1, Integer.valueOf(id));

                ResultSet resultSet = preparedStatementSelect.executeQuery();

                if (resultSet.next()) {
                    PreparedStatement preparedStatementInsert =
                            connection.prepareStatement(
                                    "INSERT INTO pizza_app.objednavky " +
                                            "(nazov, ingrediencie, cena) VALUES (?,?,?)"
                            );
                    preparedStatementInsert.setString(1, resultSet.getString("nazov_pizza"));
                    preparedStatementInsert.setString(2, resultSet.getString("ingrediencie"));
                    preparedStatementInsert.setString(3, resultSet.getString("cena"));

                    int result = preparedStatementInsert.executeUpdate();

                    if (result == 1) {
                        System.out.println("INSERT INTO menu");
                        menuText.setText("Uspesne pridane do kosika.");
                        idMenu.setText("");
                    } else {
                        System.out.println("insert error");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
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
