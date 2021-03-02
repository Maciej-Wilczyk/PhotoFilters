

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    Stage window;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("zadanie 3");

        Parent root = FXMLLoader.load(getClass().getResource("gui/MainWindow.fxml"));
        Scene scene = new Scene(root, 1200, 1000);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


