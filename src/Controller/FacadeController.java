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
    
    public FacadeController(FacadeModel model) {
        this.model = model;
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
