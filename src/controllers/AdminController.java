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

    @FXML
    private TextField nazovField;
    @FXML
    private TextField ingrediencieField;
    @FXML
    private TextField cenaField;
    @FXML
    private TextField idField;
    @FXML
    private TextField idEdit;
    @FXML
    private TextField nazovEdit;
    @FXML
    private TextField ingrediencieEdit;
    @FXML
    private TextField cenaEdit;

    @FXML
    private Label errorTextAdmin;

    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    public void onKosAdminButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on KosAdmin.");

        try {

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("kosAdmin");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPridatButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on PridatButton.");

        if (nazovField.getText().isEmpty() == true) {
            System.out.println("Empty NazovField!");
            errorTextAdmin.setText("Vyplnte nazov pizze!");
        } else if (ingrediencieField.getText().isEmpty() == true) {
            System.out.println("Empty IngredniencieField");
            errorTextAdmin.setText("Vyplnte ingredniecie pizze!");
        } else if (cenaField.getText().isEmpty() == true) {
            System.out.println("Empty CenaField!");
            errorTextAdmin.setText("Vyplnte cenu pizze!");
        } else {
            String namePizza = nazovField.getText();
            String ingredienciePizza = ingrediencieField.getText();
            String pricePizza = cenaField.getText();

            try {
                Connection connection = DbConnector.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                "INSERT INTO menu " +
                                        "(nazov_pizza, ingrediencie, cena) " +
                                        "VALUES " +
                                        "(?, ?, ?)");

                preparedStatement.setString(1, namePizza);
                preparedStatement.setString(2, ingredienciePizza);
                preparedStatement.setString(3, pricePizza);

                int result = preparedStatement.executeUpdate();

                if (result == 1) {
                    System.out.println("INSERT INTO menu");
                    nazovField.setText("");
                    ingrediencieField.setText("");
                    cenaField.setText("");
                    errorTextAdmin.setText("");

                } else {
                    System.out.println("insert error");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onShowButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on UkazButton.");

        tableAdmin.getItems().clear();

        try {
            Connection connection = DbConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM pizza_app.menu");

            while (rs.next()) {
                observableList.add(new ModelTable(
                        rs.getString("id_pizza"), rs.getString("nazov_pizza"),
                        rs.getString("ingrediencie"), rs.getString("cena")));
            }

        } catch (SQLException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void onEditButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on EditButton.");

        if (idEdit.getText().isEmpty() == true) {
            System.out.println("Empty idEditField!");
            errorTextAdmin.setText("Vyplnte ID pizze pre editaciu!");
        } else if (nazovEdit.getText().isEmpty() == true && ingrediencieEdit.getText().isEmpty() == true && cenaEdit.getText().isEmpty() == true) {
            System.out.println("Every field is epmty!");
            errorTextAdmin.setText("Vyplnte polia!");
        } else {
            if (nazovEdit.getText().isEmpty() == true) {
                System.out.println("Empty nazovEditField!");
                errorTextAdmin.setText("Vyplnte nazov pizze pre editaciu!");
            } else {
                try {
                    Connection connection = DbConnector.getConnection();

                    PreparedStatement st = connection.prepareStatement(
                            "UPDATE pizza_app.menu SET nazov_pizza = '" + nazovEdit.getText() + "' WHERE id_pizza = " + idEdit.getText() + ";");
                    st.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                idEdit.setText("");
                nazovEdit.setText("");
                errorTextAdmin.setText("");
            }

            if (ingrediencieEdit.getText().isEmpty() == true) {
                System.out.println("Empty ingrediencieField!");
                errorTextAdmin.setText("Vyplnte ingredienciu pizze pre editaciu!");
            } else {
                try {
                    Connection connection = DbConnector.getConnection();

                    PreparedStatement st = connection.prepareStatement(
                            "UPDATE pizza_app.menu SET ingrediencie = '" + ingrediencieEdit.getText() + "' WHERE id_pizza = " + idEdit.getText() + ";");
                    st.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                idEdit.setText("");
                ingrediencieEdit.setText("");
                errorTextAdmin.setText("");
            }

            if (cenaEdit.getText().isEmpty() == true) {
                System.out.println("Empty cenaField!");
                errorTextAdmin.setText("Vyplnte cenu pizze pre editaciu!");
            } else {
                try {
                    Connection connection = DbConnector.getConnection();

                    PreparedStatement st = connection.prepareStatement(
                            "UPDATE pizza_app.menu SET cena = '" + cenaEdit.getText() + "' WHERE id_pizza = " + idEdit.getText() + ";");
                    st.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                idEdit.setText("");
                cenaEdit.setText("");
                errorTextAdmin.setText("");
            }
            errorTextAdmin.setText("");
        }
    }

    public void onVymazatButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on VymazatButton.");

        if (idField.getText().isEmpty() == true) {
            System.out.println("Empty idFiled!");
            errorTextAdmin.setText("Vyplnte ID pizze pre vymazanie!");
        } else {
            try {
                Connection connection = DbConnector.getConnection();

                PreparedStatement st = connection.prepareStatement("DELETE FROM pizza_app.menu WHERE id_pizza = " + idField.getText() + ";");
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            idField.setText("");
            errorTextAdmin.setText("");
        }
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

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection connection = DbConnector.getConnection();

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM pizza_app.menu");

            while (rs.next()) {
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
