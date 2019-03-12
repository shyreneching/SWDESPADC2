
package Model;


import java.util.ArrayList;

public class MusicPlayer {
    
    private Account user;
    private ArrayList<Song> songs;
    private ArrayList<Playlist> groups;
    private int repeat; //0 - no repeat, 1 - repeat all, 2 - repeat 1 song
    private boolean shuffle;


    public boolean login(String username, String password){


        return false;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public ArrayList<Playlist> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Playlist> groups) {
        this.groups = groups;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }
}
