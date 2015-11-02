package editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editor.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Cutreditor");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


        Controller controller = (Controller)loader.getController();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
