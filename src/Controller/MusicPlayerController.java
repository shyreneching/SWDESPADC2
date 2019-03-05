/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import Model.MusicPlayer;
import Model.Song;
import View.MusicPlayerView;

/**
 *
 * @author Stanley Sie
 */
public class MusicPlayerController implements Controller {

    private MusicPlayer model;
    private MusicPlayerView view;
    
    public MusicPlayerController(MusicPlayer model) {
        this.model = model;
        view = new MusicPlayerView(this, this.model);
        this.model.attach(view);
    }
    
    public void setCurrentSong(Song song) {
        model.setCurrentSong(song);
    }
    
    @Override
    public void setModel(Model model) {
        this.model = (MusicPlayer) model;
    }
    
}
