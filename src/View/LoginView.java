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
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class LoginView extends View {
    //private Login model;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login, signup, closelogin;
    @FXML
    private Label unknownusername, incorrectpassword;

    private FXMLLoader loader;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public LoginView (Stage stage) {
    	this.stage = stage;
        //this.model = model;
    	try {
            loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
            loader.setController(this);
            root = (Parent) loader.load();
            System.out.println("test");
            scene = new Scene(root);

            this.stage.setTitle("Login");
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.initStyle(StageStyle.UNDECORATED);
            this.stage.show();
        } catch (IOException ie) {
        }


    }

    public void initialize () {
        closelogin.setOnAction(event -> {
           stage.close();
        });

        login.setOnAction(event -> {
            System.out.println("Logged in");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Profile.fxml"));
                Parent root = (Parent) loader.load();
                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
            }
        });

        signup.setOnAction(event -> {
            System.out.println("Sign Up??");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SignUp.fxml"));
                Parent root = (Parent) loader.load();
                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
            }

        });
    }

    private void init () {
        unknownusername.setVisible(false);
        incorrectpassword.setVisible(false);
    }

    @Override
    public void update() {

    }
}
