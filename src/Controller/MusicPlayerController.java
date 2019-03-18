/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FacadeModel;
<<<<<<< HEAD
import Model.SongInterface;
import View.DashboardView;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
=======
import Model.Queue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
>>>>>>> eaf8d0e6ce36a9f59558bf2ae7b03be997a6ac1c

/**
 *
 * @author Stanley Sie
 */
public class MusicPlayerController {
    
<<<<<<< HEAD
    private DashboardView main;
    private FacadeModel model;
=======
    private FacadeModel model;
    private List<MediaPlayer> songs;
    private Stage stage;
>>>>>>> eaf8d0e6ce36a9f59558bf2ae7b03be997a6ac1c
    
    private boolean pause;
    private boolean shuffle;
    private boolean mute;
    private int repeat; //0 = not applicable, 1 = repeat all, 2 = repeat 1 song
    private int currentIndex;
    private MediaPlayer currentSong;
<<<<<<< HEAD
    private Media media;
    private List<MediaPlayer> list;
    
    public MusicPlayerController(FacadeModel model, DashboardView main) {
        this.main = main;
        this.model = model;
        list = new ArrayList<>();
        currentIndex = 0;
    }

    public List<MediaPlayer> getList() {
        return list;
    }

    public void setList(List<MediaPlayer> list) {
        this.list = list;
    }
    
    public MediaPlayer getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(MediaPlayer currentSong) {
        this.currentSong = currentSong;
    }
    
    public void playMusic() {
        if(pause) {
            currentSong.play();
            pause = false;
        } else {
            currentSong.pause();
            pause = true;
        }
    }
    
    public void nextMusic() {
        
    }
    
    public void prevMusic() {
        
    }
    
    public void fastForward() {
        
    }
    
    public void backTrack() {
        
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
    
    public void setSongs(ObservableList<SongInterface> songs) {
        list.clear();
        for(SongInterface s : songs) {
            list.add(new MediaPlayer(new Media(s.getSongfile().toURI().toString())));
        }
    }
    
    public void playSongs(SongInterface song) {
        list.add(new MediaPlayer(new Media(song.getSongfile().toURI().toString())));
        if(currentSong != null && currentSong.isAutoPlay()) {
            currentSong.stop();
        }
        setCurrentSong(list.get(list.size()-1));
        currentSong.setStartTime(Duration.ZERO);
        currentSong.play();
    }
    
    public void addSongs(SongInterface song) {
        list.add(new MediaPlayer(new Media(song.getSongfile().toURI().toString())));
    }
=======
    private FacadeController main;
    
    public MusicPlayerController(FacadeModel model, Stage stage, FacadeController main) {
        this.model = model;
        this.main = main;
        this.stage = stage;
        songs = new ArrayList<>();
        repeat = 0;
        mute = false;
        pause = true;
        shuffle = false;
        currentIndex = 0;
    }
    
    public void playMusic() {
        if(pause) {
            pause = false;
        } else {
            pause = true;
        }
    }   
    
    public void nextMusic() {
        
    }
    
    public void prevMusic() {
        
    }
    
    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }
    
    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
        ((Queue)model.getQueue()).setShuffle(this.shuffle);
    }

    public int getRepeat() {
        return repeat;
    }
    
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
    
    public void loadSongs() {
        FileChooser file = new FileChooser();
        List<File> newFile = file.showOpenMultipleDialog(stage);
        if (newFile != null) {
            for (int i = 0; i < newFile.size(); i++) {
                System.out.println(newFile.get(i).getAbsoluteFile());
                songs.add(new MediaPlayer(new Media(newFile.get(i).getAbsoluteFile().toURI().toString())));
            }
        }
    }
>>>>>>> eaf8d0e6ce36a9f59558bf2ae7b03be997a6ac1c
}
