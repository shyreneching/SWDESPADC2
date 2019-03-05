/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DashboardController;
import Model.Dashboard;
import Model.Song;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Stanley Sie
 */
public class DashboardView extends View {
    
    private Dashboard model;
    
    @FXML
    private Button search, tracks, starred, musicPlayer, newPlaylist, profile, edit, delete, upload;
    @FXML
    private VBox playlistList;
    @FXML
    private TextField searchField;
    @FXML
    private TableView table;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label songName, artistName;
    
    private FXMLLoader loader;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public DashboardView(DashboardController controller, Dashboard model, Stage stage) {
        super(controller);
        this.model = model;
        this.stage = stage;
        
        try {
            loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
            loader.setController(this);
            root = (Parent) loader.load();
            scene = new Scene(root);
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setX(10);
            stage.setY(dim.getHeight()/2 - 350);
            stage.show();
        } catch (IOException ie) {
        }
        
        super.controller.buildMusicPlayer();
        init();
    }
    
    public void initialize() {
        newPlaylist.setOnAction(event -> {
            
        });
        musicPlayer.setOnAction(event -> {
            super.controller.buildMusicPlayer();
        });
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        table.setOnMouseClicked(event -> {
            Song song = null;
            song = (Song) table.getSelectionModel().getSelectedItem();
            if (event.getClickCount() == 1) {
                if(song != null) {
                    edit.setVisible(true);
                    edit.setDisable(false);
                    delete.setVisible(true);
                    delete.setDisable(false);
                }
            } else if(event.getClickCount() == 2) {
                if(song != null) {
                    super.controller.setCurrentSong(song);
                }
            }
        });
    }
    
    private void init() {
        scroll.setPrefHeight(220);
        edit.setVisible(false);
        edit.setDisable(true);
        delete.setVisible(false);
        delete.setDisable(true);
        
        TableColumn num = new TableColumn("#");
        num.setCellValueFactory(new PropertyValueFactory("songid"));
        num.setStyle("-fx-alignment: CENTER;");
        num.setPrefWidth(50);
        TableColumn title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory("name"));
        title.setPrefWidth(150);
        TableColumn artist = new TableColumn("Artist");
        artist.setCellValueFactory(new PropertyValueFactory("artist"));
        artist.setPrefWidth(150);
        TableColumn album = new TableColumn("Album");
        album.setCellValueFactory(new PropertyValueFactory("album"));
        album.setPrefWidth(150);
        TableColumn year = new TableColumn("Year");
        year.setCellValueFactory(new PropertyValueFactory("year"));
        year.setStyle("-fx-alignment: CENTER;");
        year.setPrefWidth(50);
        TableColumn genre = new TableColumn("Genre");
        genre.setCellValueFactory(new PropertyValueFactory("genre"));
        genre.setPrefWidth(100);
        TableColumn duration = new TableColumn("Duration");
        duration.setCellValueFactory(new PropertyValueFactory("duration"));
        duration.setStyle("-fx-alignment: CENTER;");
        duration.setPrefWidth(100);
        
        table.getColumns().setAll(num, title, artist, album, year, genre, duration);
        
        Song song = new Song();
        song.setSongid("1234");
        song.setName("Aloha");
        song.setAlbum("Hawai");
        song.setArtist("Larry");
        song.setGenre("Hip Hop");
        song.setYear(2019);
        song.setLength(198);
        
        super.controller.addSong(song);
        table.getItems().add(song);
    }
    
    @Override
    public void update() {
        songName.setText(super.controller.getCurrentSong().getName());
        artistName.setText(super.controller.getCurrentSong().getArtist());
    }
}
