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
    	//LoginView view = new LoginView(primaryStage);
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
            loader.setController(this);
            Parent root = (Parent) loader.load();
            System.out.println("test");
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ie) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
