package Model;

import javafx.collections.ObservableList;
import java.sql.SQLException;

public class UserPlaylist implements PlaylistList {
    public PlaylistService playlistService;

    public UserPlaylist() {
        this.playlistService = new PlaylistService();
    }

    public ObservableList<PlaylistInterface> createPlaylist(String username) throws SQLException {
        ObservableList<PlaylistInterface> playlists = playlistService.getUserPlaylist(username);
        return playlists;
    }

}
