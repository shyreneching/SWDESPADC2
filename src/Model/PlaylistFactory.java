package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public abstract class PlaylistFactory {

    public abstract ObservableList<Playlist> playlistFactoryMethod(String username) throws SQLException;
}
