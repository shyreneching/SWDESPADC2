/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FacadeModel;
import View.AddPlaylistView;
import View.DashboardView;
import View.LoginView;
import View.ProfileView;
import View.SignupView;
import javafx.stage.Stage;

/**
 *
 * @author Stanley Sie
 */
public class FacadeController {
    
    private FacadeModel model;
    public MusicPlayerController musicPlayer;
    
    public FacadeController(FacadeModel model, Stage stage) {
        this.model = model;
        this.model.attach(new DashboardView(stage, this.model, this));
        musicPlayer = new MusicPlayerController(this.model, stage, this);
    }
    
    public void createPlaylist() {
        AddPlaylistView view = new AddPlaylistView(model);
    }
    
    public void viewProfile() {
        ProfileView view = new ProfileView(model);
    }
    
    public void signin() {
        SignupView view = new SignupView(model);
    }
    
    public void login() {
        LoginView view = new LoginView(model);
    }
}
