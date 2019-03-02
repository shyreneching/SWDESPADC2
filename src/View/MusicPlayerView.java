/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MusicPlayerController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MusicPlayer;

/**
 *
 * @author Stanley Sie
 */
public class MusicPlayerView implements View {

    private MusicPlayer master;
    private MusicPlayerController controller;
    
    private Stage stage;
    
    public MusicPlayerView() {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MusicPlayerView.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("musicplayer.css").toExternalForm());
            controller = loader.getController(); 
            controller.setMaster(master);
            controller.init();

            stage.setTitle("Music Player");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException ex) {
        } 
    }
    
    public void closeStage() {
        stage.hide();
    }
    
    public void showStage() {
        stage.show();
    }
    
    @Override
    public void update() {
        
    }
    
}
