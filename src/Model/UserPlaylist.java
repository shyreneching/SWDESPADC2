package Model;

import javafx.collections.ObservableList;
import java.sql.SQLException;

public class UserPlaylist implements PlaylistList {

    public static ObservableList<PlaylistInterface> createPlaylist(String username) throws SQLException {
        Service playlistService = new PlaylistService();
        ObservableList<PlaylistInterface> playlists = ((PlaylistService)playlistService).getUserPlaylist(username);
        return playlists;
    }

}
