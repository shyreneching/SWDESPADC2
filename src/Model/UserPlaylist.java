package Model;

import javafx.collections.ObservableList;
import java.sql.SQLException;

public class UserPlaylist implements PlaylistList {
    public PlaylistService playlistService;

    public UserPlaylist() {
        this.playlistService = new PlaylistService();
    }

    public ObservableList<Playlist> createPlaylist(String username) throws SQLException {
        ObservableList<Playlist> playlists = playlistService.getUserPlaylist(username);
        return playlists;
    }

}
