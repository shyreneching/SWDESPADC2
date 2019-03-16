package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PlaylistList {
    public ObservableList<Playlist> createPlaylist(String username) throws SQLException;
}
