package View;

import javafx.stage.*;

import java.io.IOException;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	LoginView view = new LoginView(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
