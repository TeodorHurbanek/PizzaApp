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

//This is test comment
public class MainController implements Initializable {

    public BorderPane mainPage;

    @FXML
    private ImageView logoPizza;

    /*BUTTON UVOD - metoda*/
    public void onUvodClick(ActionEvent event) throws IOException {
        mainPage.setCenter(loadFX("uvod"));
        System.out.println("U ve clicked on UVODpage");
    }

    /*BUTTON MENU - metoda*/
    public void onMenuClick() {
        mainPage.setCenter(loadFX("menu"));
        System.out.println("U ve clicked on MENUpage");
    }

    /* cesta k fxmlkam*/
    private Parent loadFX(String name) {

        String pathToMain = "../sample/"+ name +".fxml";

        URL mainURL = getClass().getResource(pathToMain);

        Parent parent = null;
        try {
            parent = FXMLLoader.load(mainURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* staticky nastaveny UVOD do stredu stranky */
        mainPage.setCenter(loadFX("uvod"));
        Image logo = new Image("/images/pizzaIcon.png");
        logoPizza.setImage(logo);
    }
}
