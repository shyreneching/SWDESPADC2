package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongService {
    private MusicPlayerDB db;

    public SongService(MusicPlayerDB db) {
        this.db = db;
    }

    //adds aong to the database. Must be COMPLETE information
    public boolean add(Song s) throws SQLException {
        String query = "INSERT INTO song VALUE (?, ?, ?, ?, ? ,?, ?, ?, ?)";
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        try {
            statement.setString(1, s.getSongid());
            statement.setString(2, s.getName());
            statement.setString(3, s.getGenre());
            statement.setString(4, s.getArtist());
            statement.setString(5, s.getAlbum());
            statement.setInt(6, s.getYear());
            statement.setInt(7, s.getTrackNumber());
            statement.setInt(8, s.getLength());
            statement.setDouble(9, s.getSize());

            boolean added = statement.execute();

            return added;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }


        return false;
    }

    //gets all the songs in the parameter in an arraylist
    public ArrayList<Song> getAll() throws SQLException {
        Connection connection = db.getConnection();
        ArrayList <Song> songs = new ArrayList<>();

        String query ="SELECT * FROM song";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Song s = new Song();
                s.setSongid(rs.getString("idsong"));
                s.setName(rs.getString("songname"));
                s.setGenre(rs.getString("genre"));
                s.setArtist(rs.getString("artist"));
                s.setAlbum(rs.getString("album"));
                s.setYear(rs.getInt("year"));
                s.setTrackNumber(rs.getInt("trackNumber"));
                s.setLength(rs.getInt("length"));
                s.setSize(rs.getFloat("size"));
                songs.add(s);
            }
            return songs;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }

    //gets one specific song with the id of the song
    public Song getSong(String songid) throws SQLException {
        Connection connection = db.getConnection();

        String query ="SELECT * FROM song WHERE idsong = " + songid;
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            Song s = new Song();
            s.setSongid(rs.getString("idsong"));
            s.setName(rs.getString("songname"));
            s.setGenre(rs.getString("genre"));
            s.setArtist(rs.getString("artist"));
            s.setAlbum(rs.getString("album"));
            s.setYear(rs.getInt("year"));
            s.setTrackNumber(rs.getInt("trackNumber"));
            s.setLength(rs.getInt("length"));
            s.setSize(rs.getFloat("size"));

            return s;

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }

    //get songs with the same name
    public ArrayList<Song> getSongName(String songname) throws SQLException {
        Connection connection = db.getConnection();
        ArrayList<Song> songs = new ArrayList<>();

        String query ="SELECT * FROM song WHERE idsong = " + songname;
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Song s = new Song();
                s.setSongid(rs.getString("idsong"));
                s.setName(rs.getString("songname"));
                s.setGenre(rs.getString("genre"));
                s.setArtist(rs.getString("artist"));
                s.setAlbum(rs.getString("album"));
                s.setYear(rs.getInt("year"));
                s.setTrackNumber(rs.getInt("trackNumber"));
                s.setLength(rs.getInt("length"));
                s.setSize(rs.getFloat("size"));
                songs.add(s);
            }
            return songs;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }


    //pass the username of the account that you want to view the playlist
    public List<Playlist> getUserPlaylist(String username) throws SQLException {
        Connection connection = db.getConnection();
        List <Playlist> property = new ArrayList<>();

        String query ="SELECT * FROM playlist INNER JOIN accounts ON username = username " +
                "WHERE accounts.username = '" + username + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){

            }
            return property;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return property;
    }

    //pass the username to delete an account
    public boolean delete(String username) throws SQLException {
        String query = "DELETE FROM accounts WHERE username = ?";
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, username);

            boolean deleted  = statement.execute();
            return deleted;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return false;
    }

    //pass the username of the account that wants to be change and an account class with COMPLETE information including the updates
    public boolean update(String username, Account a) throws SQLException {
        //UPDATE
        Connection connection = db.getConnection();

        String query = "UPDATE accounts SET "
                + "name = ?, "
                + "password = ?, "
                + " WHERE username= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, a.getName());
            statement.setString(2, a.getPassword());
            statement.setString(3, username);

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return false;
    }
}
