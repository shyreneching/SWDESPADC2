package Model;

import Mp3agic.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class SongService {
    private MusicPlayerDB db;

    public SongService(MusicPlayerDB db) {
        this.db = db;
    }

    //adds song to the database. Must be COMPLETE information
    public boolean add(Song s) throws SQLException {
        Connection connection = db.getConnection();
        String query = "INSERT INTO song VALUE (?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        String query2 = "INSERT INTO usersong VALUE (?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(query2);

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

            statement2.setString(1, s.getSongid());
            statement2.setString(2, s.getUser());
            statement2.setInt(3, s.getTimesplayed());

            statement2.execute();
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
                s.setFilename(s.getArtist() + "-"+ s.getName()+ ".mp3");

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

    // gets song of a specific user
    public ArrayList<Song> getUserSong(String username) throws SQLException {
        Connection connection = db.getConnection();
        ArrayList <Song> songs = new ArrayList<>();

        String query ="SELECT * FROM song INNER JOIN usersong ON song.idsong = usersong.idsong " +
                "WHERE username = '" + username + "'";
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
                s.setFilename(s.getArtist() + "-"+ s.getName()+ ".mp3");

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
                s.setUser(rs.getString("username"));
                s.setTimesplayed(rs.getInt("timesplayed"));

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
    public Song getSong(String songid, String username) throws SQLException {
        Connection connection = db.getConnection();

        String query ="SELECT * FROM song NATURAL JOIN usersong WHERE idsong = '" + songid +
                "' AND username = '" + username + "'";
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
                File theFile = new File(s.getArtist() + "-" + s.getName() + ".mp3");
                OutputStream out = new FileOutputStream(theFile);
                InputStream input = rs.getBinaryStream("songfile");
                byte[] buffer = new byte[4096];  // how much of the file to read/write at a time
                while (input.read(buffer) > 0) {
                    out.write(buffer);
                }

                s.setSongfile(theFile);
                //takes the exact location of the song
                s.setFilelocation(theFile.getAbsolutePath());
                s.setUser(rs.getString("username"));
                s.setTimesplayed(rs.getInt("timesplayed"));
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
    public ArrayList<Song> getSongName(String songname, String username) throws SQLException {
        Connection connection = db.getConnection();
        ArrayList<Song> songs = new ArrayList<>();

        String query ="SELECT * FROM song NATURAL JOIN usersong WHERE songname = '" + songname +
                "' AND '" + username + "'";
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
                s.setFilename(s.getArtist() + "-"+ s.getName()+ ".mp3");

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
                s.setUser(rs.getString("username"));
                s.setTimesplayed(rs.getInt("timesplayed"));

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
        Connection connection = db.getConnection();
        String query = "DELETE FROM song WHERE idsong = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        String query2 = "DELETE FROM usersong WHERE idsong = ?";
        PreparedStatement statement2 = connection.prepareStatement(query2);
        try {

            statement.setString(1, songid);
            statement2.setString(1, songid);
            statement2.execute();
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
    public boolean update(String songid, Song s, String username) throws SQLException {
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
                + " WHERE songid = ?";

        String query2 = "UPDATE usersong SET "
                + "timesplayed = ?, "
                + " WHERE username= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement statement2 = connection.prepareStatement(query2);
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
            statement.setString(10, songid)
            ;
            statement2.setInt(1, s.getTimesplayed());
            statement2.setString(2, username);

            statement.executeUpdate();

            //edits the metadata of the song file itself
            Mp3File mp3file = new Mp3File(s.getFilelocation());
            if (mp3file.hasId3v1Tag()) {
                mp3file.removeId3v1Tag();
            }
            if (mp3file.hasId3v2Tag()) {
                mp3file.removeId3v2Tag();
            }
            if (mp3file.hasCustomTag()) {
                mp3file.removeCustomTag();
            }
            ID3v1 id3v1Tag;
            ID3v1Genres ID3v1genres;
            /*if (mp3file.hasId3v1Tag()) {
                id3v1Tag =  mp3file.getId3v1Tag();
            } else {*/
                // mp3 does not have an ID3v1 tag, let's create one..
            ID3v2 id3v2Tag;
            id3v2Tag = new ID3v24Tag();
            mp3file.setId3v2Tag(id3v2Tag);
            //}

            id3v2Tag.setTrack(s.getTrackNumber() + "");
            id3v2Tag.setArtist(s.getArtist());
            id3v2Tag.setTitle(s.getName());
            id3v2Tag.setAlbum(s.getAlbum());
            id3v2Tag.setYear(s.getYear() + "");
            id3v2Tag.setGenre(ID3v1Genres.matchGenreDescription(s.getGenre()));
            /*id3v2Tag.setComment("Some comment");
            id3v2Tag.setLyrics("Some lyrics");
            id3v2Tag.setComposer("The Composer");
            id3v2Tag.setPublisher("A Publisher");
            id3v2Tag.setOriginalArtist("Another Artist");*/
            id3v2Tag.setAlbumArtist(s.getArtist());
            /*id3v2Tag.setCopyright("Copyright");
            id3v2Tag.setUrl("http://foobar");
            id3v2Tag.setEncoder("The Encoder");*/
            mp3file.save(s.getFilename());

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        return false;
    }
}
