package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class GenrePlaylistConcreteFactory extends PlaylistFactory {

    public GenrePlaylistConcreteFactory()  {
        super.playlistList = new UserPlaylist();
    }

    public ObservableList<PlaylistInterface> playlistFactoryMethod(String username) throws SQLException {
        return PlaylistList.createPlaylist(username);
    }
}
