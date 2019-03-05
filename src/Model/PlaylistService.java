package Model;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistService {
    private MusicPlayerDB db;

    public PlaylistService(MusicPlayerDB db) {
        this.db = db;
    }

    //adds playlist class and the account that owns the playlist. Songs in the playlist must already be imported to the database beforehand
    public boolean add(Playlist p, Account a) throws SQLException {
        Connection connection = db.getConnection();
        String query = "INSERT INTO playlist VALUE (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        String query2 = "INSERT INTO songcollection VALUE (?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query2);

        ArrayList<Song> songs = p.getSongs();
        try {
            statement.setString(1, p.getPlaylistid());
            statement.setString(2, p.getName());
            statement.setString(3, a.getUsername());

            for(Song s: songs) {
                statement2.setString(1, p.getPlaylistid());
                statement2.setString(2, s.getSongid());

                statement2.execute();
            }
            boolean added = statement.execute();
            return added;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //gets all the playlist in the parameter in an arraylist
    public ArrayList<Playlist> getAll() throws SQLException {
        Connection connection = db.getConnection();
        ArrayList <Playlist> playlists = new ArrayList<>();
        ArrayList <Song> songs;

        String query ="SELECT * FROM playlist";
        PreparedStatement statement = connection.prepareStatement(query);
        String query2 ="SELECT * FROM songcollection INNER JOIN song ON idsong = idsong";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        try {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Playlist p = new Playlist();
                p.setPlaylistid(rs.getString("idplaylist"));
                p.setName(rs.getString("playlistname"));
                p.setSongs(new ArrayList<Song>());
                playlists.add(p);
            }

            ResultSet rs2 = statement2.executeQuery();
            while (rs2.next()){
                Song s = new Song();
                String playlistid = rs2.getString("idplaylist");
                s.setSongid(rs2.getString("idsong"));
                s.setName(rs2.getString("songname"));
                s.setGenre(rs2.getString("genre"));
                s.setArtist(rs2.getString("artist"));
                s.setAlbum(rs2.getString("album"));
                s.setYear(rs2.getInt("year"));
                s.setTrackNumber(rs2.getInt("trackNumber"));
                s.setLength(rs2.getInt("length"));
                s.setSize(rs2.getFloat("size"));
                s.setSize(rs2.getFloat("size"));
                // sets the name to "Artist-title"
                s.setFilename(s.getArtist() + "-"+ s.getName()+ ".mp3");
                //gets the song from the databse and make put it in a File datatype
                File theFile = new File(s.getName());
                OutputStream out = new FileOutputStream(theFile);
                InputStream input = rs2.getBinaryStream("songfile");
                byte[] buffer = new byte[4096];  // how much of the file to read/write at a time
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }
                s.setSongfile(theFile);
                //takes the exact location of the song
                s.setFilelocation(theFile.getAbsolutePath());

                for(Playlist p : playlists){
                    if(p.getPlaylistid().compareTo(playlistid) == 0) {
                        songs = p.getSongs();
                        songs.add(s);
                        p.setSongs(songs);
                    }
                }
            }
            return playlists;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }

    //gets one specific playlist with the playlistid
    public Playlist getPlaylist(String playlistid) throws SQLException {
        Connection connection = db.getConnection();
        ArrayList<Song> songs = new ArrayList<>();
        Playlist p = new Playlist();

        String query ="SELECT * FROM playlist WHERE idplaylist = '" + playlistid + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        String query2 ="SELECT * FROM songcollection INNER JOIN song ON idsong = idsong WHERE idplaylist = '" + playlistid + "'";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        try {
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                p.setPlaylistid(rs.getString("idplaylist"));
                p.setName(rs.getString("playlistname"));
                p.setSongs(new ArrayList<Song>());
            }

            ResultSet rs2 = statement2.executeQuery();
            while (rs2.next()) {
                Song s = new Song();
                s.setSongid(rs2.getString("idsong"));
                s.setName(rs2.getString("songname"));
                s.setGenre(rs2.getString("genre"));
                s.setArtist(rs2.getString("artist"));
                s.setAlbum(rs2.getString("album"));
                s.setYear(rs2.getInt("year"));
                s.setTrackNumber(rs2.getInt("trackNumber"));
                s.setLength(rs2.getInt("length"));
                s.setSize(rs2.getFloat("size"));
                s.setSize(rs2.getFloat("size"));
                // sets the name to "Artist-title"
                s.setFilename(s.getArtist() + "-" + s.getName() + ".mp3");
                //gets the song from the database and make put it in a File datatype
                File theFile = new File(s.getName());
                OutputStream out = new FileOutputStream(theFile);
                InputStream input = rs2.getBinaryStream("songfile");
                byte[] buffer = new byte[4096];  // how much of the file to read/write at a time
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }
                s.setSongfile(theFile);
                //takes the exact location of the song
                s.setFilelocation(theFile.getAbsolutePath());
                songs = p.getSongs();
                songs.add(s);
                p.setSongs(songs);
            }
            return p;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }

    //get playlist with the same name
    public ArrayList<Playlist> getPlaylistName(String playlistname) throws SQLException {
        Connection connection = db.getConnection();
        ArrayList <Playlist> playlists = new ArrayList<>();
        ArrayList <Song> songs;

        String query ="SELECT * FROM playlist WHERE playlistname = '" + playlistname + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        String query2 ="SELECT * FROM songcollection INNER JOIN song ON idsong = idsong";
        PreparedStatement statement2 = connection.prepareStatement(query2);

        try {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Playlist p = new Playlist();
                p.setPlaylistid(rs.getString("idplaylist"));
                p.setName(rs.getString("playlistname"));
                p.setSongs(new ArrayList<Song>());
                playlists.add(p);
            }

            ResultSet rs2 = statement2.executeQuery();
            while (rs2.next()){
                Song s = new Song();
                String playlistid = rs2.getString("idplaylist");
                s.setSongid(rs2.getString("idsong"));
                s.setName(rs2.getString("songname"));
                s.setGenre(rs2.getString("genre"));
                s.setArtist(rs2.getString("artist"));
                s.setAlbum(rs2.getString("album"));
                s.setYear(rs2.getInt("year"));
                s.setTrackNumber(rs2.getInt("trackNumber"));
                s.setLength(rs2.getInt("length"));
                s.setSize(rs2.getFloat("size"));
                s.setSize(rs2.getFloat("size"));
                // sets the name to "Artist-title"
                s.setFilename(s.getArtist() + "-"+ s.getName()+ ".mp3");
                //gets the song from the databse and make put it in a File datatype
                File theFile = new File(s.getName());
                OutputStream out = new FileOutputStream(theFile);
                InputStream input = rs2.getBinaryStream("songfile");
                byte[] buffer = new byte[4096];  // how much of the file to read/write at a time
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }
                s.setSongfile(theFile);
                //takes the exact location of the song
                s.setFilelocation(theFile.getAbsolutePath());

                for(Playlist p : playlists){
                    if(p.getPlaylistid().compareTo(playlistid) == 0) {
                        songs = p.getSongs();
                        songs.add(s);
                        p.setSongs(songs);
                    }
                }
            }
            return playlists;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return null;
    }

    //pass the playlist id to delete the specific song
    public boolean delete(String playlistid) throws SQLException {
        Connection connection = db.getConnection();
        String query = "DELETE FROM playlist WHERE idplaylist = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        String query2 = "DELETE FROM songcollection WHERE idplaylist = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        try {

            statement.setString(1, playlistid);
            statement2.setString(1, playlistid);
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

    //pass the songid of the song that wants to be change and song class with COMPLETE information including the updates
    public boolean update(String playlistid, Playlist p) throws SQLException {
        Connection connection = db.getConnection();

        String query = "UPDATE songplaylist, SET "
                + "playlistname = ?,"
                + " WHERE playlist= ?";

        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setString(1, p.getName());
            statement.setString(2, p.getPlaylistid());
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