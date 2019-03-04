/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MusicPlayerController;
import Model.MusicPlayer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 *
 * @author Stanley Sie
 */
public class MusicPlayerView extends View {

    private MusicPlayer model;
    private MusicPlayerController controller;

    @FXML
    private Button play, next, prev, repeat, shuffle;
    @FXML
    private Slider slider;
    @FXML
    private Label songName, artistName, start, end;

    private FXMLLoader loader;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public MusicPlayerView(MusicPlayerController controller, MusicPlayer model) {
        this.controller = controller;
        this.model = model;

        try {
            loader = new FXMLLoader(getClass().getResource("/View/MusicPlayer.fxml"));
            loader.setController(this);
            root = (Parent) loader.load();
            scene = new Scene(root);

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            stage = new Stage();
            stage.setTitle("Music Player");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setX(1010);
            stage.setY(dim.getHeight() / 2 - 300);
            stage.show();

        } catch (IOException ie) {
        }

        init();
    }

    public void initialize() {

    }

    private void init() {
        if (model.getCurrentSong() != null) {
            songName.setText(model.getCurrentSong().getName());
            artistName.setText(model.getCurrentSong().getArtist());
            end.setText(model.getCurrentSong().getDuration());
        }
    }

    @Override
    public void update() {
        songName.setText(model.getCurrentSong().getName());
        artistName.setText(model.getCurrentSong().getArtist());
        end.setText(model.getCurrentSong().getDuration());
    }

}
