package Model;

import javafx.collections.ObservableList;

public class Playlist {
    
    private String playlistid, name;
    private ObservableList<Song> songs;

    public String getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(String playlistid) {
        this.playlistid = playlistid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ObservableList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ObservableList<Song> songs) {
        this.songs = songs;
    }
    
    
}
