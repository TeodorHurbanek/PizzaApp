package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField meno;
    @FXML
    private PasswordField heslo;
    @FXML
    private Label errorText;
    @FXML
    private Label errorText1;
    @FXML
    private Button loginButton;

    private Connection connection;

    public void onLoginButtonClick(ActionEvent event) throws IOException{
        System.out.println("Uve clicked on LoginButton");
        if (isAdminLoggedIn() == false) {
            System.out.println("Wrong name or password!");
            errorText.setText("Chybne meno.");
            errorText1.setText("Chybne heslo.");
        } else {
            System.out.println("Successfuly logged in.");
            try{

            FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("admin");

            Parent parent = fxmlLoader.load();

            MainController.getMainController().mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
            //loginButton.getScene().getWindow().hide();

            /*Parent root = FXMLLoader.load(getClass().getResource("../sample/admin.fxml"));
            Node node = (Node) event.getSource();
            MainController mainController = new MainController();
            mainController.getMainPage().setCenter(root);*/
            //Stage stage = (Stage) node.getScene().getWindow();
            //stage.setScene(new Scene(root));

            //Stage adminStage = new Stage();
            //Parent root = FXMLLoader.load(getClass().getResource("../sample/admin.fxml"));
            /*MainController mainController = new MainController();

            mainController.mainPage.setCenter(root);*/



            /* cestka k adminovi snad.. */
            /*try {
                FXMLLoader fxmlLoader = MainController.getMainController().loadFXML("admin");

                Parent parent = fxmlLoader.load();

                MainController.getMainController().mainPage.setCenter(parent);

            } catch (IOException e) {
                e.printStackTrace();
            }*/
            /* koneeeeeec */
            //mainController.mainPage.setCenter(loadFX("admin"));
            meno.setText("");
            heslo.setText("");
            errorText.setText("");
            errorText1.setText("");
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

    public FXMLLoader loadFXML(String name) {

        String pathToMain = "../sample /" + name + ".fxml";

        URL mainURL = getClass().getResource(pathToMain);

        FXMLLoader fxmlLoader = new FXMLLoader(mainURL);

        return fxmlLoader;
    }

    /*private Parent loadFX(String name) {

        String pathToMain = "../sample/"+ name +".fxml";

        URL mainURL = getClass().getResource(pathToMain);

        Parent parent = null;
        try {
            parent = FXMLLoader.load(mainURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parent;
    }*/

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
