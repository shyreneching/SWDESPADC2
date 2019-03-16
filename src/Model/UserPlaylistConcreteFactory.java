package Model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UserPlaylistConcreteFactory extends PlaylistFactory {

    public UserPlaylistConcreteFactory()  {
        super.playlistList = new UserPlaylist();
    }

    public ObservableList<Playlist> playlistFactoryMethod(String username) throws SQLException {
        return playlistList.createPlaylist(username);
    }

}
