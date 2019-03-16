package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class YearPlaylistConcreteFactory extends PlaylistFactory {
    private PlaylistList userplaylist;

    public YearPlaylistConcreteFactory() {
        this.userplaylist = new UserPlaylist();
    }

    public ObservableList<Playlist> playlistFactoryMethod(String username) throws SQLException {
        return userplaylist.createPlaylist(username);
    }
}
