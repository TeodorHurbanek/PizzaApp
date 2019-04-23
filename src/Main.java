import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        String pathToMain = "sample/main.fxml";

        URL mainURL = getClass().getResource(pathToMain);

        Parent parent = FXMLLoader.load(mainURL);

        Scene scene = new Scene(parent);

        //metoda setMaximized, kt. zvacsuje okno po zapnuti
        primaryStage.setMaximized(true);
        primaryStage.setTitle("PizzaApp");
        //primaryStage.getIcons().add(new Image("images/pizzaIcon.png")); <- ZADNA SLOZKA IMAGES NIKDE NENI KOKOTKO
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
