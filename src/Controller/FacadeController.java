/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FacadeModel;
import View.AddPlaylistView;
<<<<<<< HEAD
import View.EditSongView;
import View.LoginView;
import View.PlaylistView;
import View.ProfileView;
import View.SignupView;
import View.SongListView;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
=======
import View.DashboardView;
import View.LoginView;
import View.ProfileView;
import View.SignupView;
>>>>>>> eaf8d0e6ce36a9f59558bf2ae7b03be997a6ac1c
import javafx.stage.Stage;

/**
 *
 * @author Stanley Sie
 */
public class FacadeController {
    
    private FacadeModel model;
<<<<<<< HEAD
    private SongListView songView;
    private PlaylistView playlistView;
    
    public FacadeController(FacadeModel model) {
        this.model = model;
    }    
=======
    
    public FacadeController(FacadeModel model) {
        this.model = model;
    }
>>>>>>> eaf8d0e6ce36a9f59558bf2ae7b03be997a6ac1c
    
    public void createPlaylist() {
        AddPlaylistView view = new AddPlaylistView(model);
    }
    
<<<<<<< HEAD
    public void editSong() {
        EditSongView view = new EditSongView(model);
    }
    
=======
>>>>>>> eaf8d0e6ce36a9f59558bf2ae7b03be997a6ac1c
    public void viewProfile() {
        ProfileView view = new ProfileView(model);
    }
    
    public void signin() {
        SignupView view = new SignupView(model);
    }
    
    public void login() {
<<<<<<< HEAD
        LoginView view = new LoginView(model, songView);
    }
    
    public void init(FacadeModel model, TableView table, Label listLabel, Stage stage) {
        songView = new SongListView(model, table, listLabel, stage);
        playlistView = new PlaylistView(model, table, listLabel);
    }

    public SongListView getSongView() {
        return songView;
    }

    public void setSongView(SongListView songView) {
        this.songView = songView;
    }
    
    
=======
        LoginView view = new LoginView(model);
    }
>>>>>>> eaf8d0e6ce36a9f59558bf2ae7b03be997a6ac1c
}
