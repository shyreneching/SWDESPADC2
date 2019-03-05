package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;

public class SignupView extends View {

    @FXML
    private Button mainsignup;
    @FXML
    private Label emailwarn, passwordwarn1, passwordwarn2;
    @FXML
    private TextField firstname, lastname, email;
    @FXML
    private PasswordField password, conpassword;

    private FXMLLoader loader;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private SignupView (){
        try {
            loader = new FXMLLoader(getClass().getResource("/View/SignUp.fxml"));
            loader.setController(this);
            root = (Parent) loader.load();
            scene = new Scene(root);

            stage.setTitle("Sign Up");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
        }
    }

    private void initialize () {
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        mainsignup.setOnAction(event -> {

        });
    }

    private void init () {
        emailwarn.setVisible(false);
        passwordwarn1.setVisible(false);
        passwordwarn2.setVisible(false);
    }
}
