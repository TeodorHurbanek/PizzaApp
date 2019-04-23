package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public BorderPane mainPage;

    public void onUvodClick(ActionEvent event) throws IOException {
        mainPage.setCenter(loadFX("uvod"));
        System.out.println("U ve clicked on UVODpage");
    }

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
        mainPage.setCenter(loadFX("uvod"));
    }
}
