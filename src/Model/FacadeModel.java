/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.DashboardView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Stanley Sie
 */
public class FacadeModel extends Model {
    private Account user;
    private ObservableList<Song> songs;
    private ObservableList<Playlist> groups;
    private Song currentSong;
    private MusicPlayerDB database;
    private AccountService accountService;
    private PlaylistService playlistService;
    private SongService songService;
    private AudioParser parser;

    public FacadeModel() {
        songs = FXCollections.observableArrayList();
        groups = FXCollections.observableArrayList();
        database = new MusicPlayerDB();
        accountService = new AccountService(database);
        playlistService = new PlaylistService(database);
        songService = new SongService(database);
        parser = new AudioParser();
    }
    
    public FacadeModel(DashboardView view) {
        super.attach(view);
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
        super.update();
    }
    
    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
        super.update();
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ObservableList<Song> songs) {
        this.songs = songs;
        super.update();
    }

    public ObservableList<Playlist> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<Playlist> groups) {
        this.groups = groups;
        super.update();
    } 
    
    //METHOD CONNECTED TO DATABASE
    public boolean login(String username, String password){
        return false;
    }
    
    public boolean addSong() {
        return false;
    }
    
    public boolean deleteSong() {
        return false;
    }
    
    public boolean updateSong() {
        return false;
    }
    
    public boolean addPlaylist() {
        return false;
    }
    
    public boolean deletePlaylist() {
        return false;
    }
    
    public boolean updatePlaylist() {
        return false;
    }
    
    public boolean createUser() {
        return false;
    }
    
    //METHOD CONNECTED TO DATABASE
    
    public FacadeModel getState() {
        return this;
    }
}
