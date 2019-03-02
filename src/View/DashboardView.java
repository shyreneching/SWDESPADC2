/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DashboardController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MusicPlayer;

/**
 *
 * @author Stanley Sie
 */
public class DashboardView implements View {

    private MusicPlayer master;
    private DashboardController controller;
    
    public DashboardView(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardView.fxml"));
            Parent root = (Parent) loader.load();
            
            controller = loader.getController();
            controller.setMaster(master);
            controller.init();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/dashboardview.css").toExternalForm());
            
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
        }
    }

    public void setMaster(MusicPlayer master) {
        this.master = master;
        master.setDashboardView(this);
    }
    
    @Override
    public void update() {
        
    }

}
