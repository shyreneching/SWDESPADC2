package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AlbumPlaylist implements PlaylistList{

    public static ObservableList<PlaylistInterface> createPlaylist(String username) throws SQLException {
        Service songService = new SongService();
        ObservableList<SongInterface> songs = ((SongService)songService).getUserSong(username);
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
