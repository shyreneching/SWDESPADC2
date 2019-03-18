/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.FacadeModel;
import Model.PlaylistInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Stanley Sie
 */
public class PlaylistView extends View {
    
    private FacadeModel model;
    private Label listLabel;
    private TableView table;
    private TableColumn title;
    
    private ObservableList<PlaylistInterface> playlists;
    
    public PlaylistView(FacadeModel model, TableView table, Label listLabel) {
        this.model = model;
        this.table = table;
        this.listLabel = listLabel;
        playlists = FXCollections.observableArrayList();
        this.model.attach(this);
        
        init();
    }
    
    private void init() {
        
        update();
    }
    
    public void loadPlaylist() {
        
    }
    
    @Override
    public void update() {
        
    }
}
