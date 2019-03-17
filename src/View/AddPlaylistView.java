/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.FacadeModel;
import Model.Playlist;
import Model.PlaylistInterface;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Stanley Sie
 */
public class AddPlaylistView extends View {
    
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    private Parent root;
    private FacadeModel model;
    
    @FXML
    private Button close, create;
    @FXML
    private TextField name;
    @FXML
    private RadioButton empty, notempty;
    
    private ToggleGroup group;
    
    public AddPlaylistView(FacadeModel model) {
        this.model = model;
        try {
            loader = new FXMLLoader(getClass().getResource("/View/AddPlaylist.fxml"));
            loader.setController(this);
            root = (Parent) loader.load();
            scene = new Scene(root);
            
            stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException io) { }
        
        group = new ToggleGroup();
        empty.setToggleGroup(group);
        notempty.setToggleGroup(group);
    }
    
    public void initialize() {
        close.setOnAction(event -> {
            stage.close();
        });
        create.setOnAction(event -> {
            PlaylistInterface p = new Playlist();
            p.setName(name.getText().trim());
            if(notempty.isSelected()) {
                p.setSongs(model.getSongs());
            }
            
            if(model.getUser() == null) {
                model.getGroups().add(p);
            } else {
                try {
                    model.addPlaylist(p);
                } catch (SQLException ex) { }
            }
        });
    }
    
    @Override
    public void update() {
        
    }
    
}
