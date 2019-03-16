
package Model;

//import View.DashboardView;
import java.util.ArrayList;

public class Dashboard {
    
    private Account user;
    private ArrayList<SongInterface> songs;
    private ArrayList<Playlist> groups;
    private SongInterface currentSong;

    public Dashboard() {
        songs = new ArrayList<>();
        groups = new ArrayList<>();
    }
    
    /*public Dashboard(DashboardView view) {
        super.attach(view);
    }*/
    
    public boolean login(String username, String password){


        return false;
    }

    public SongInterface getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(SongInterface currentSong) {
        this.currentSong = currentSong;
        /*super.notifyView();*/
    }
    
    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
        /*super.notifyView();*/
    }

    public ArrayList<SongInterface> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<SongInterface> songs) {
        this.songs = songs;
        /*super.notifyView();*/
    }

    public ArrayList<Playlist> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Playlist> groups) {
        this.groups = groups;
        /*super.notifyView();*/
    } 
    
    public Dashboard getState() {
        return this;
    }
    
    
}
