/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.MusicPlayerView;

/**
 *
 * @author Stanley Sie
 */
public class MusicPlayer extends Model {
    
    private int repeat; //0 - no repeat, 1 - repeat all, 2 - repeat 1 song
    private boolean shuffle;
    private Playlist currentPlaylist;
    private Song currentSong;

    public MusicPlayer() {
        
    }
    
    public MusicPlayer(MusicPlayerView view) {
        super.attach(view);
    }
    
    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
        super.notifyView();
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
        super.notifyView();
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
        super.notifyView();
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
        super.notifyView();
    }
}
