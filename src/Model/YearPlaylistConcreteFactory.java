package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class YearPlaylistConcreteFactory extends PlaylistFactory {

    public YearPlaylistConcreteFactory() {
        super.playlistList = new UserPlaylist();
    }

    public ObservableList<PlaylistInterface> playlistFactoryMethod(String username) throws SQLException {
        return playlistList.createPlaylist(username);
    }
}
