package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nazov.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ingrediencie.setCellValueFactory(new PropertyValueFactory<>("ingrediencies"));
        col_cena.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
