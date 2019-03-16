package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AlbumPlaylist implements PlaylistList{
    public SongService songService;

    public AlbumPlaylist() {
        this.songService = new SongService();
    }

    public ObservableList<PlaylistInterface> createPlaylist(String username) throws SQLException {
        ObservableList<SongInterface> songs = songService.getUserSong(username);
        ObservableList<PlaylistInterface> playlists = FXCollections.observableArrayList();
        boolean added = false;

        for(SongInterface s: songs){
            for(PlaylistInterface play: playlists){
                if(play.getName().equals(s.getAlbum())){
                    ObservableList<SongInterface> temp = play.getSongs();
                    temp.add(s);
                    play.setSongs(temp);
                    added = true;
                }
            }
            if(!added){
                ObservableList<SongInterface> temp = FXCollections.observableArrayList();
                PlaylistInterface p = new Playlist();
                p.setName(s.getAlbum());
                temp.add(s);
                p.setSongs(temp);
                playlists.add(p);
            }
        }
        return playlists;
    }
}
