/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

//import View.DashboardView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.SQLException;

public class FacadeModel extends Model {
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
    public SongInterface getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(SongInterface currentSong) {
        this.currentSong = currentSong;
        //super.update();
    }
    
    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
        //super.update();
    }

    public ObservableList<SongInterface> getSongs() {
        return songs;
    }

    public void setSongs(ObservableList<SongInterface> songs) {
        this.songs = songs;
        //super.update();
    }

    public ObservableList<Playlist> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<Playlist> groups) {
        this.groups = groups;
        //super.update();
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
        return false;
    }

    public void logout(){
        user = null;
    }

    /*Add/import one song to the database under the current user
    * */
    public boolean addSong(String filelocation) throws SQLException {
        ObservableList<Object> songs = null;
        songs = songService.getAll();
        SongInterface s = new Song();
        File songFile = new File(filelocation);
        s.setSongfile(songFile);
        s = parser.getSongDetails(filelocation);
        s.setUser(this.user.getUsername());

        if (songs == null) {
            s.setSongid("S01");
            if(songService.add(s)){
                return true;
            }
        } else {
            s.setSongid(String.format("S%02d", songs.size() + 1));
            if(songService.add(s)){
                return true;
            }
        }

        return false;
    }

    /*Deletes one specific song in the database using songid
    * Automatically deletes the song in the playlist that contains the song*/
    public boolean deleteSong(String songid) throws SQLException {
        return ((SongService)songService).delete(songid, user);
    }
    
    public boolean updateSong() {
        return false;
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

        return false;
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
