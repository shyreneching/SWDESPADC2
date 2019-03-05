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

public class ProfileView extends View {

    @FXML
    private Button editusername, editemail, editpassword, save, cancel;
    @FXML
    private Label displayusername, displayemail, displaypassword;
    @FXML
    private TextField newusername, newemail, newpassword;

    private FXMLLoader loader;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ProfileView () {
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

    public void initialize () {
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        editemail.setOnAction(event -> {
            newemail.setVisible(true);
            newemail.setEditable(true);
            save.setVisible(true);
            save.setDisable(false);
            cancel.setVisible(true);
            cancel.setDisable(false);
        });

        editpassword.setOnAction(event -> {
            newpassword.setVisible(true);
            newpassword.setEditable(true);
            save.setVisible(true);
            save.setDisable(false);
            cancel.setVisible(true);
            cancel.setDisable(false);
        });

        editusername.setOnAction(event -> {
            newusername.setVisible(true);
            newusername.setEditable(true);
            save.setVisible(true);
            save.setDisable(false);
            cancel.setVisible(true);
            cancel.setDisable(false);
        });

        save.setOnAction(event -> {

        });

        cancel.setOnAction(event -> {

        });
    }

    private void init () {
        newemail.setVisible(false);
        newusername.setVisible(false);
        newpassword.setVisible(false);
        newemail.setEditable(false);
        newusername.setEditable(false);
        newpassword.setEditable(false);
        save.setVisible(false);
        cancel.setVisible(false);
        save.setDisable(true);
        cancel.setDisable(true);
    }
}
