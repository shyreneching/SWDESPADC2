/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Account;
import Model.Dashboard;
import Model.Model;
import Model.MusicPlayer;
import Model.Playlist;
import Model.Song;
import View.DashboardView;
import javafx.stage.Stage;

/**
 *
 * @author Stanley Sie
 */
public class DashboardController implements Controller {
    
    private Dashboard dashboardModel;
    private DashboardView dashboardView;
    private MusicPlayerController musicPlayerController;
    private MusicPlayer musicPlayerModel;
    
    public DashboardController(Dashboard model, Stage stage) {
        this.dashboardModel = model;
        dashboardView = new DashboardView(this, dashboardModel, stage);
        dashboardModel.attach(dashboardView);
    }
    
    public void buildMusicPlayer() {
        if(this.musicPlayerController == null) {
            musicPlayerModel = new MusicPlayer();
        } 
        this.musicPlayerController = new MusicPlayerController(musicPlayerModel);
    }
    
    public void setCurrentSong(Song song) {
        dashboardModel.setCurrentSong(song);
        musicPlayerModel.setCurrentSong(song);
        musicPlayerController.setCurrentSong(song);
    }
    
    public Song getCurrentSong() {
        return dashboardModel.getCurrentSong();
    }
    
    public void addSong(Song song) {
        dashboardModel.getSongs().add(song);
    }
    
    public void addPlaylist(Playlist playlist) {
        dashboardModel.getGroups().add(playlist);
    }
    
    public void addUser(Account user) {
        dashboardModel.setUser(user);
    }
    
    public void deleteSong(Song song) {
        dashboardModel.getSongs().remove(song);
    }
    
    public void deletePlaylist(Playlist playlist) {
        dashboardModel.getGroups().remove(playlist);
    }
    
    public void deleteUser(Account user) {
        dashboardModel.setUser(null);
    }

    @Override
    public void setModel(Model model) {
        this.dashboardModel = (Dashboard) model;
    }
    
    
}
