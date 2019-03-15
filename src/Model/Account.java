package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Account {
    
    private String username, password, name;
    private ObservableList<Playlist> playlists;
    private ObservableList<Song> songs;

    public Account(){

    }

    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        playlists = FXCollections.observableArrayList();
        songs = FXCollections.observableArrayList();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ObservableList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ObservableList<Song> songs) {
        this.songs = songs;
    }
    
    
}
