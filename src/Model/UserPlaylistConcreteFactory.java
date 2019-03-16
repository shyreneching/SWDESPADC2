package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UserPlaylistConcreteFactory extends PlaylistFactory {
    private PlaylistList userplaylist;

    public UserPlaylistConcreteFactory() {
        this.userplaylist = new UserPlaylist();
    }

    public ObservableList<Playlist> playlistFactoryMethod(String username) throws SQLException {
        return userplaylist.createPlaylist(username);
    }

}
