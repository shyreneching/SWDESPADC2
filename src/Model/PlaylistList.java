package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PlaylistList {
    public ObservableList<PlaylistInterface> createPlaylist(String username) throws SQLException;
}
