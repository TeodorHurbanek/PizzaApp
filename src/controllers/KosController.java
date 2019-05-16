package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KosController implements Initializable {

    public  void onVymazatKosButtonClick(ActionEvent event) {
        System.out.println("U ve clicked on VymazatButton.");
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

    }
}
