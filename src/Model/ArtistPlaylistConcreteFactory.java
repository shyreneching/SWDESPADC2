package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ArtistPlaylistConcreteFactory extends PlaylistFactory {

    public ArtistPlaylistConcreteFactory() {
        super.playlistList = new UserPlaylist();
    }

    public ObservableList<PlaylistInterface> playlistFactoryMethod(String username) throws SQLException {
        return playlistList.createPlaylist(username);
    }
}
