import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    static {
        Font.loadFont(Main.class.getResource("sample/css/fontawesome/font/fontawesome-webfont.ttf").toExternalForm(), 10);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        String pathToMain = "sample/main.fxml";

        URL mainURL = getClass().getResource(pathToMain);

        Parent parent = FXMLLoader.load(mainURL);

        Scene scene = new Scene(parent);


        //metoda setMaximized, kt. zvacsuje okno po zapnuti
        primaryStage.setMaximized(true);
        primaryStage.setTitle("PizzaApp");
//        primaryStage.getIcons().add(new Image("sk/holic/chat/IT6.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
