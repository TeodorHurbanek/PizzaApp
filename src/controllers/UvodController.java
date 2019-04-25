package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class UvodController implements Initializable {

    @FXML
    private ImageView pizzaFav1, pizzaFav2, pizzaFav3;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image1 = new Image("/images/provincionale.jpg");
        pizzaFav1.setImage(image1);
        Image image2 = new Image("/images/sanDaniele.jpg");
        pizzaFav2.setImage(image2);
        Image image3 = new Image("/images/margerita.jpg");
        pizzaFav3.setImage(image3);
    }
}
