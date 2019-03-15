
package Model;

//import View.DashboardView;
import java.util.ArrayList;

public class Dashboard extends Model {
    
    private Account user;
    private ArrayList<Song> songs;
    private ArrayList<Playlist> groups;
    private Song currentSong;

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

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
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

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
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
