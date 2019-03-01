package Model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class SongService {
    private MusicPlayerDB db;

    public SongService(MusicPlayerDB db) {
        this.db = db;
    }

    //adds aong to the database. Must be COMPLETE information
    public boolean add(Song s) throws SQLException {
        String query = "INSERT INTO song VALUE (?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";
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
            // Write the file into the database
            FileInputStream songfile = new FileInputStream(s.getSongfile());
            statement.setBinaryStream(10, songfile);

            boolean added = statement.execute();

            return added;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
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
                s.setSize(rs.getFloat("size"));
                // sets the name to "Artist-title"
                s.setFilename(s.getArtist() + "-"+ s.getName());

                //gets the song from the databse and make put it in a File datatype
                File theFile = new File(s.getName());
                OutputStream out = new FileOutputStream(theFile);
                InputStream input = rs.getBinaryStream("songfile");
                byte[] buffer = new byte[4096];  // how much of the file to read/write at a time
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }
                s.setSongfile(theFile);
                //takes the exact location of the song
                s.setFilelocation(theFile.getAbsolutePath());

                songs.add(s);
            }
            return songs;
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

    //gets one specific song with the id of the song
    public Song getSong(String songid) throws SQLException {
        Connection connection = db.getConnection();

        String query ="SELECT * FROM song WHERE idsong = '" + songid + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
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

                // sets the name to "Artist-title"
                s.setFilename(s.getArtist() + "-" + s.getName());

                //gets the song from the databse and make put it in a File datatype
                File theFile = new File(s.getArtist() + "-" + s.getName());
                OutputStream out = new FileOutputStream(theFile);
                InputStream input = rs.getBinaryStream("songfile");
                byte[] buffer = new byte[4096];  // how much of the file to read/write at a time
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }
                s.setSongfile(theFile);
                //takes the exact location of the song
                s.setFilelocation(theFile.getAbsolutePath());
                return s;
            }

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

    //get songs with the same name
    public ArrayList<Song> getSongName(String songname) throws SQLException {
        Connection connection = db.getConnection();
        ArrayList<Song> songs = new ArrayList<>();

        String query ="SELECT * FROM song WHERE idsong = '" + songname + "'";
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

                // sets the name to "Artist-title"
                s.setFilename(s.getArtist() + "-"+ s.getName());

                //gets the song from the databse and make put it in a File datatype
                File theFile = new File(s.getName());
                OutputStream out = new FileOutputStream(theFile);
                InputStream input = rs.getBinaryStream("songfile");
                byte[] buffer = new byte[4096];  // how much of the file to read/write at a time
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }
                s.setSongfile(theFile);
                //takes the exact location of the song
                s.setFilelocation(theFile.getAbsolutePath());

                songs.add(s);

            }
            return songs;
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

    //pass the song id to delete the specific song
    public boolean delete(String songid) throws SQLException {
        String query = "DELETE FROM song WHERE songid = ?";
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, songid);
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
    public boolean update(String songid, Song s) throws SQLException {
        Connection connection = db.getConnection();

        String query = "UPDATE song SET "
                + "songname = ?, "
                + "genre = ?, "
                + "artist = ?, "
                + "album = ?, "
                + "year = ?, "
                + "trackNumber = ?, "
                + "length = ?, "
                + "size = ?, "
                + "songfile = ?, "
                + " WHERE username= ?";

        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setString(1, s.getName());
            statement.setString(2, s.getGenre());
            statement.setString(3, s.getArtist());
            statement.setString(4, s.getAlbum());
            statement.setInt(5, s.getYear());
            statement.setInt(6, s.getTrackNumber());
            statement.setInt(7, s.getLength());
            statement.setDouble(8, s.getSize());
            // Write the file into the database
            FileInputStream songfile = new FileInputStream(s.getSongfile());
            statement.setBinaryStream(9, songfile);
            statement.setString(10, s.getSongid());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return false;
    }
}
