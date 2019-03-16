package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AlbumPlaylistConcreteFactory extends PlaylistFactory {
    private PlaylistList userplaylist;

    public AlbumPlaylistConcreteFactory() {
        this.userplaylist = new UserPlaylist();
    }

    public ObservableList<Playlist> playlistFactoryMethod(String username) throws SQLException {
        return userplaylist.createPlaylist(username);
    }
}
