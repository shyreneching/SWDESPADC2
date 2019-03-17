/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.FacadeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

/**
 *
 * @author Stanley Sie
 */
class PlaylistView {
    
    private FacadeModel model;
    private ObservableList<HBox> songList;
    
    public PlaylistView(FacadeModel model) {
        this.model = model;
        songList = FXCollections.observableArrayList();
        
        init();
    }
    
    private void init() {
        
    }
    
    public void setModel(FacadeModel model) {
        this.model = model;
    }
    
    public void update() {
        
    }
}
