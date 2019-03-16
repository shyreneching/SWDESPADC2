/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

//import View.DashboardView;
//import View.View;
import Mp3agic.InvalidDataException;
import Mp3agic.NotSupportedException;
import Mp3agic.UnsupportedTagException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.sql.SQLException;

public class FacadeModel{
    private Account user;
    private ObservableList<SongInterface> songs;
    private ObservableList<Playlist> groups;
    private SongInterface currentSong;

    private Service accountService;
    private Service playlistService;
    private Service songService;
    private AudioParserInterface parser;

    public FacadeModel() {
        songs = FXCollections.observableArrayList();
        groups = FXCollections.observableArrayList();
        accountService = new AccountService();
        playlistService = new PlaylistService();
        songService = new SongService();
        parser = new AudioParser();
    }
    /*
    public FacadeModel(DashboardView view) {
        super.attach(view);
    }
*/

    /*private View view;

    public void attach(View view) {
        this.view = view;
    }

    public void update() {
        view.update();
    }*/

    public SongInterface getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(SongInterface currentSong) {
        this.currentSong = currentSong;
        //update();
    }
    
    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
        //update();
    }

    public ObservableList<SongInterface> getSongs() {
        return songs;
    }

    public void setSongs(ObservableList<SongInterface> songs) {
        this.songs = songs;
        //update();
    }

    public ObservableList<Playlist> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<Playlist> groups) {
        this.groups = groups;
        //update();
    }

    //METHOD CONNECTED TO DATABASE
    /*Pass the username and password of the login attempt
    * Compares the username and password if it matches anthing in the database
    * */
    public boolean login(String username, String password) throws SQLException {
        ObservableList<Object> accounts = null;
        accounts = accountService.getAll();
        if (accounts != null){
            for (Object temp : accounts) {
                if (((Account)temp).getUsername().compareTo(username) == 0 && ((Account)temp).getPassword().compareTo(password) == 0){
                    user = (Account) temp;
                    return true;
                }
            }
        }
        //update();
        return false;
    }

    public void logout(){
        user = null;
        //update();
    }

    /*Add/import one song to the database under the current user
    * */
    public boolean addSong(String filelocation) throws SQLException {
        ObservableList<Object> songs = null;
        songs = songService.getAll();
        SongInterface s = new Song();
        File songFile = new File(filelocation);
        s.setSongfile(songFile);
        s.setSize(songFile.length());
        s = parser.getSongDetails(filelocation);
        s.setUser(this.user.getUsername());

        if (songs == null) {
            s.setSongid("S01");
            if(songService.add(s)){
                return true;
            }
        } else {
            for (Object temp : songs) {
                if (((Song) temp).getName().compareToIgnoreCase(s.getName()) != 0
                        && ((Song) temp).getArtist().compareToIgnoreCase(s.getArtist()) != 0
                        && ((Song) temp).getAlbum().compareToIgnoreCase(s.getAlbum()) != 0) {
                    s.setSongid(String.format("S%02d", songs.size() + 1));
                    if (songService.add(s)) {
                        return true;
                    }
                }
            }
        }
        //update();
        return false;
    }

    /*Deletes one specific song in the database using songid
    * Automatically deletes the song in the playlist that contains the song*/
    public boolean deleteSong(String songid) throws SQLException {
        //update();
        return ((SongService)songService).delete(songid, user);
    }

    /*Updates the title of the song given the new name and the song that wants to be changed*/
    public boolean updateSongName(String name, SongInterface s) throws SQLException, UnsupportedTagException, NotSupportedException, InvalidDataException, IOException {
        SongInterface old = s;
        s.setName(name);
        parser.editSongDetails(old, s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the genre of the song given the new name and the song that wants to be changed*/
    public boolean updateSongGenre(String genre, SongInterface s) throws SQLException, UnsupportedTagException, NotSupportedException, InvalidDataException, IOException {
        SongInterface old = s;
        s.setGenre(genre);
        parser.editSongDetails(old, s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the artist of the song given the new name and the song that wants to be changed*/
    public boolean updateSongArtist(String artist, SongInterface s) throws SQLException, UnsupportedTagException, NotSupportedException, InvalidDataException, IOException {
        SongInterface old = s;
        s.setArtist(artist);
        parser.editSongDetails(old, s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the album of the song given the new name and the song that wants to be changed*/
    public boolean updateSongAlbum(String album, SongInterface s) throws SQLException, UnsupportedTagException, NotSupportedException, InvalidDataException, IOException {
        SongInterface old = s;
        s.setAlbum(album);
        parser.editSongDetails(old, s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the year of the song given the new name and the song that wants to be changed*/
    public boolean updateSongYear(int year, SongInterface s) throws SQLException, UnsupportedTagException, NotSupportedException, InvalidDataException, IOException {
        SongInterface old = s;
        s.setYear(year);
        parser.editSongDetails(old, s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the track number of the song given the new name and the song that wants to be changed*/
    public boolean updateSongTrackNumber(int trackNumber, SongInterface s) throws SQLException, UnsupportedTagException, NotSupportedException, InvalidDataException, IOException {
        SongInterface old = s;
        s.setTrackNumber(trackNumber);
        parser.editSongDetails(old, s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the albumart of the song given the new name and the song that wants to be changed*/
    public boolean updateSongCover(File img, SongInterface s) throws SQLException, UnsupportedTagException, NotSupportedException, InvalidDataException, IOException {
        SongInterface old = s;
        s = parser.setSongImage(old, img);
        parser.editSongDetails(old, s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the filename of the song given the new name and the song that wants to be changed*/
    public boolean updateSongFileName(String filename, SongInterface s) throws SQLException {
        s.setFilename(filename);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the location the song will be save given the new name and the song that wants to be changed*/
    public boolean updateSongFileLocation(String filelocation, SongInterface s) throws SQLException {
        s.setFilename(filelocation);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Updates the album of the song given the new name and the song that wants to be changed*/
    public boolean playSong(SongInterface s) throws SQLException{
        s.setTimesplayed(s.getTimesplayed() + 1);
        setCurrentSong(s);
        //update();
        return ((SongService)songService).update(s.getSongid(), s, s.getUser());
    }

    /*Returns all the songs in the database*/
    public ObservableList<SongInterface> getAllSong() throws SQLException {
        ObservableList<Object> o = songService.getAll();
        ObservableList<SongInterface> songs = null;
        if(o != null) {
            for (Object temp : o) {
                songs.add((SongInterface)temp);
            }
            return songs;
        }
        return null;
    }

    /*Returns the songs that the user imported*/
    public ObservableList<SongInterface> getUserSongs() throws SQLException {
        ObservableList<SongInterface> songs  = ((SongService)songService).getUserSong(user.getUsername());
        if(songs != null) {
            return songs;
        }
        return null;
    }

    /*Adds the playlist in the database*/
    public boolean addPlaylist(Playlist p) throws SQLException {
        ObservableList<Object> playlists = null;
        playlists = playlistService.getAll();

        if (playlists == null) {
            p.setPlaylistid("P01");
            if(((PlaylistService)playlistService).add(p, user)){
                return true;
            }
        } else {
            p.setPlaylistid(String.format("P%02d", playlists.size() + 1));
            if(((PlaylistService)playlistService).add(p, user)){
                return true;
            }
        }
        //update();
        return false;
    }

    public boolean addSongToPlaylist(SongInterface s, Playlist p) throws SQLException {
        return ((PlaylistService)playlistService).addSongPlaylist(s, p.getPlaylistid());
    }

    /*Deletes the playlist in the database*/
    public boolean deletePlaylist(String playlistid) throws SQLException {
        return playlistService.delete(playlistid);
    }
    
    public boolean updatePlaylist() {
        return false;
    }

    /*Register/sign-up a new user and saves it to the database
    * Needs to pass account data type with*/
    public boolean createUser(Account a) throws SQLException {
        ObservableList<Object> accounts = null;
        accounts = accountService.getAll();

        if (accounts == null) {
            if(accountService.add(a)){
                user = a;
                return true;
            }

        } else {
            for (Object temp : accounts) {
                if (((Account)temp).getUsername().compareTo(a.getUsername()) == 0)
                    return false;
            }
            if(accountService.add(a)){
                user = a;
                return true;
            }
        }
        return true;
    }
    
    //METHOD CONNECTED TO DATABASE
    
    public FacadeModel getState() {
        return this;
    }
}
