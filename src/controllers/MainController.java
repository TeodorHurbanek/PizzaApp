package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    /* cestka k adminovi snad.. */

    private static MainController mainController;

    public static MainController getMainController() {
        return mainController;
    }

    public FXMLLoader loadFXML(String name) {

        String pathToMain = "../sample/" + name + ".fxml";

        URL mainURL = getClass().getResource(pathToMain);

        FXMLLoader fxmlLoader = new FXMLLoader(mainURL);

        return fxmlLoader;
    }

    /* koneeeeeec */

    public BorderPane mainPage;

    @FXML
    private ImageView logoPizza;

    /*BUTTON UVOD - metoda*/
    public void onUvodClick(ActionEvent event) throws IOException {
        try {

            FXMLLoader fxmlLoader = loadFXML("uvod");

            Parent parent = fxmlLoader.load();
            mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("U ve clicked on UVODpage");
    }

    /*BUTTON MENU - metoda*/
    public void onMenuClick(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = loadFXML("menu");

            Parent parent = fxmlLoader.load();
            mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("U ve clicked on MENUpage");
    }

    public void onLoginClick(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = loadFXML("login");

            Parent parent = fxmlLoader.load();
            mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("U ve clicked on LOGINpage");
    }

    /* cesta k fxmlkam*/
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
        /* staticky nastaveny UVOD do stredu stranky */
        mainController = this;
        try {
            FXMLLoader fxmlLoader = loadFXML("uvod");

            Parent parent = fxmlLoader.load();

            mainPage.setCenter(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Image logo = new Image("/images/pizzaIcon.png");
        logoPizza.setImage(logo);
    }
}
