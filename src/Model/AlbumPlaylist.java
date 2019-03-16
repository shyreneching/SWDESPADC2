package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AlbumPlaylist implements PlaylistList{
    public SongService songService;

    public AlbumPlaylist() {
        this.songService = new SongService();
    }

    public ObservableList<Playlist> createPlaylist(String username) throws SQLException {
        ObservableList<Song> songs = songService.getUserSong(username);
        ObservableList<Playlist> playlists = FXCollections.observableArrayList();
        boolean added = false;

        for(Song s: songs){
            for(Playlist play: playlists){
                if(play.getName().equals(s.getAlbum())){
                    ObservableList<Song> temp = play.getSongs();
                    temp.add(s);
                    play.setSongs(temp);
                    added = true;
                }
            }
            if(!added){
                ObservableList<Song> temp = FXCollections.observableArrayList();
                Playlist p = new Playlist();
                p.setName(s.getAlbum());
                temp.add(s);
                p.setSongs(temp);
                playlists.add(p);
            }
        }
        return playlists;
    }
}
