/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FacadeModel;
import Model.Queue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Stanley Sie
 */
public class MusicPlayerController {
    
    private FacadeModel model;   
    private Queue list;
    private List<MediaPlayer> songs;
    private Stage stage;
    
    private boolean pause;
    private boolean shuffle;
    private boolean mute;
    private int repeat; //0 = not applicable, 1 = repeat all, 2 = repeat 1 song
    private int currentIndex;
    private MediaPlayer currentSong;
    private FacadeController main;
    
    public MusicPlayerController(FacadeModel model, Stage stage, FacadeController main) {
        this.model = model;
        this.main = main;
        this.stage = stage;
        list = new Queue();
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
        list.setShuffle(this.shuffle);
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
}
