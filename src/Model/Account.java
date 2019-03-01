package Model;

import java.util.ArrayList;

public class Account {
    
    private String username, password, name;
    private ArrayList<Playlist> playlists;
    private ArrayList<Song> songs;

    public Account(){

    }

    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        playlists = new ArrayList<>();
        songs = new ArrayList<>();
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

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
    
    
}
