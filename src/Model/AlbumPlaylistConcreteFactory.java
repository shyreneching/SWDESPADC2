package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class AlbumPlaylistConcreteFactory extends PlaylistFactory {


    public AlbumPlaylistConcreteFactory() {
        super.playlistList = new UserPlaylist();
    }

    public ObservableList<PlaylistInterface> playlistFactoryMethod(String username) throws SQLException {
        return PlaylistList.createPlaylist(username);
    }
}
