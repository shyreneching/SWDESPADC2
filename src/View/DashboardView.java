/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.FacadeController;
import Model.FacadeModel;
import Model.Playlist;
import Model.SongInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Stanley Sie
 */
public class DashboardView extends View {

    @FXML
    private Button play, close, next, prev, repeat, shuffle, minim, search, profile, list, speaker, login, signup, upload, fw, bw;
    @FXML
    private Button tracks, albums, artists, genres, years, favMusic, favPlaylist, playlists, createPlaylist, playPlaylist;
    @FXML
    private Slider musicSlider, speakerSlider;
    @FXML
    private VBox playlistVBox;
    @FXML
    private AnchorPane profilePane, playlistPane, musicPlayerPane;
    @FXML
    private ImageView cover, lookPlaylist;
    @FXML
    private Label greetings, or, start, end, songName, artistName, listLabel;
    @FXML
    private TableView table;
    @FXML
    private TextField searchField;

    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    private Parent root;

    private FacadeModel model;
    private FacadeController controller;
    private SongListView songView;
    private PlaylistView playlistView;
    private boolean showProfile;

    private ObservableList<Label> listOfPlaylist;
    private FilteredList<SongInterface> filteredData;

    public DashboardView(FacadeModel model, Stage stage) {
        this.stage = stage;
        this.model = model;
        this.controller = new FacadeController(this.model);
        this.model.attach(this);
        listOfPlaylist = FXCollections.observableArrayList();

        try {
            if (model.getUser() == null && model.getAllSong() != null) {
                filteredData = new FilteredList<>(songView.getSongList(), p -> true);
            } else if (model.getUser() != null && model.getUserSongs() != null) {
                filteredData = new FilteredList<>(songView.getSongList(), p -> true);
            }
        } catch (SQLException ex) {
        }

        try {
            loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
            loader.setController(this);
            root = (Parent) loader.load();
            scene = new Scene(root);

            this.stage.setTitle("Dashboard");
            this.stage.setScene(scene);
            this.stage.centerOnScreen();
            this.stage.setResizable(false);
            this.stage.initStyle(StageStyle.UNDECORATED);
            this.stage.show();
        } catch (IOException ie) {
        }

        init();
    }

    public void initialize() {
        upload.setOnAction(event -> {
            songView.uploadSong();
        });
        playlists.setOnMouseEntered(event -> {
            lookPlaylist.setVisible(true);
        });
        playlists.setOnMouseExited(event -> {
            lookPlaylist.setVisible(false);
        });
        playlists.setOnAction(event -> {
            if (playlistPane.isVisible()) {
                playlistPane.setVisible(false);
                playlistPane.setDisable(true);
            } else {
                playlistPane.setVisible(true);
                playlistPane.setDisable(false);
            }
        });
        createPlaylist.setOnAction(event -> {
            controller.createPlaylist();
            addPlaylist(new Playlist());
        });
        playPlaylist.setOnAction(event -> {
            
        });
        play.setOnMouseEntered(event -> {
            play.setPrefSize(42, 42);
            play.setLayoutX(574);
        });
        play.setOnMouseExited(event -> {
            play.setPrefSize(40, 40);
            play.setLayoutX(575);
        });
//        play.setOnAction(event -> {
//            if (controller.musicPlayer.isPause()) {
//                play.setStyle("-fx-background-image: url('/Files/pause.png');");
//            } else {
//                play.setStyle("-fx-background-image: url('/Files/play.png');");
//            }
//            controller.musicPlayer.playMusic();
//        });
//        repeat.setOnMouseEntered(event -> {
//            if (controller.musicPlayer.getRepeat() == 0) {
//                repeat.setStyle("-fx-background-image: url('/Files/repeat_hover.png');");
//            } else if (controller.musicPlayer.getRepeat() == 1) {
//                repeat.setStyle("-fx-background-image: url('/Files/repeat_hover.png');");
//            } else if (controller.musicPlayer.getRepeat() == 2) {
//                repeat.setStyle("-fx-background-image: url('/Files/repeat_one_clicked_hover.png');");
//            }
//
//        });
//        repeat.setOnMouseExited(event -> {
//            if (controller.musicPlayer.getRepeat() == 0) {
//                repeat.setStyle("-fx-background-image: url('/Files/repeat.png');");
//            } else if (controller.musicPlayer.getRepeat() == 1) {
//                repeat.setStyle("-fx-background-image: url('/Files/repeat_clicked.png');");
//            } else if (controller.musicPlayer.getRepeat() == 2) {
//                repeat.setStyle("-fx-background-image: url('/Files/repeat_one_clicked.png');");
//            }
//        });
//        repeat.setOnAction(event -> {
//            if (controller.musicPlayer.getRepeat() == 0) {
//                controller.musicPlayer.setRepeat(1);
//                repeat.setStyle("-fx-background-image: url('/Files/repeat_clicked.png');");
//            } else if (controller.musicPlayer.getRepeat() == 1) {
//                controller.musicPlayer.setRepeat(2);
//                repeat.setStyle("-fx-background-image: url('/Files/repeat_one_clicked.png');");
//            } else if (controller.musicPlayer.getRepeat() == 2) {
//                controller.musicPlayer.setRepeat(0);
//                repeat.setStyle("-fx-background-image: url('/Files/repeat.png');");
//            }
//        });
//        shuffle.setOnMouseEntered(event -> {
//            shuffle.setStyle("-fx-background-image: url('/Files/shuffle_hover.png');");
//        });
//        shuffle.setOnMouseExited(event -> {
//            if (controller.musicPlayer.isShuffle()) {
//                shuffle.setStyle("-fx-background-image: url('/Files/shuffle_clicked.png');");
//            } else {
//                shuffle.setStyle("-fx-background-image: url('/Files/shuffle.png');");
//            }
//        });
//        shuffle.setOnAction(event -> {
//            if (controller.musicPlayer.isShuffle()) {
//                controller.musicPlayer.setShuffle(false);
//                shuffle.setStyle("-fx-background-image: url('/Files/shuffle.png');");
//            } else {
//                controller.musicPlayer.setShuffle(true);
//                shuffle.setStyle("-fx-background-image: url('/Files/shuffle_clicked.png');");
//            }
//        });
//        speaker.setOnMouseEntered(event -> {
//            if (controller.musicPlayer.isMute()) {
//                speaker.setStyle("-fx-background-image: url('/Files/mute_hover.png');");
//            } else {
//                speaker.setStyle("-fx-background-image: url('/Files/volume_hover.png');");
//            }
//        });
//        speaker.setOnMouseExited(event -> {
//            if (controller.musicPlayer.isMute()) {
//                speaker.setStyle("-fx-background-image: url('/Files/mute.png');");
//            } else {
//                speaker.setStyle("-fx-background-image: url('/Files/volume.png');");
//            }
//        });
//        speaker.setOnAction(event -> {
//            if (controller.musicPlayer.isMute()) {
//                controller.musicPlayer.setMute(false);
//                speaker.setStyle("-fx-background-image: url('/Files/volume.png');");
//            } else {
//                controller.musicPlayer.setMute(true);
//                speaker.setStyle("-fx-background-image: url('/Files/mute.png');");
//            }
//        });
//        list.setOnMouseEntered(event -> {
//            list.setStyle("-fx-background-image: url('/Files/list_hover.png');");
//        });
//        list.setOnMouseExited(event -> {
//            list.setStyle("-fx-background-image: url('/Files/list.png');");
//        });
//        login.setOnMouseEntered(event -> {
//            if (model.getUser() != null) {
//                login.setStyle("-fx-background-image: url('/Files/viewprofile_button_clicked.png')");
//            } else {
//                login.setStyle("-fx-background-image: url('/Files/login_button_clicked.png');");
//            }
//        });
//        login.setOnMouseExited(event -> {
//            if (model.getUser() != null) {
//                login.setStyle("-fx-background-image: url('/Files/viewprofile_button.png')");
//            } else {
//                login.setStyle("-fx-background-image: url('/Files/login_button.png');");
//            }
//        });
//        login.setOnAction(event -> {
//            if (model.getUser() == null) {
//                controller.login();
//            } else {
//                controller.viewProfile();
//            }
//        });
//        signup.setOnMouseEntered(event -> {
//            if (model.getUser() != null) {
//                signup.setStyle("-fx-background-image: url('/Files/logout_button_clicked.png')");
//            } else {
//                signup.setStyle("-fx-background-image: url('/Files/signup_button_red-black_clicked.png');");
//            }
//        });
//        signup.setOnMouseExited(event -> {
//            if (model.getUser() != null) {
//                signup.setStyle("-fx-background-image: url('/Files/logout_button.png')");
//            } else {
//                signup.setStyle("-fx-background-image: url('/Files/signup_button_red-black.png');");
//            }
//        });
//        signup.setOnAction(event -> {
//            if (model.getUser() != null) {
//                model.logout();
//            } else {
//                controller.signin();
//            }
//        });
//        close.setOnMouseEntered(event -> {
//            close.setPrefSize(25, 25);
//        });
//        close.setOnMouseExited(event -> {
//            close.setPrefSize(23, 23);
//        });
//        search.setOnMouseEntered(event -> {
//            search.setPrefSize(32, 32);
//        });
//        search.setOnMouseExited(event -> {
//            search.setPrefSize(30, 30);
//        });
//        minim.setOnMouseEntered(event -> {
//            minim.setPrefSize(25, 25);
//        });
//        minim.setOnMouseExited(event -> {
//            minim.setPrefSize(23, 23);
//        });
//        profile.setOnMouseEntered(event -> {
//            profile.setPrefSize(42, 42);
//            profile.setLayoutX(1091);
//        });
//        profile.setOnMouseExited(event -> {
//            profile.setPrefSize(40, 40);
//            profile.setLayoutX(1092);
//        });
//        profile.setOnAction(event -> {
//            if (showProfile) {
//                profilePane.setVisible(false);
//                profilePane.setDisable(true);
//                showProfile = false;
//            } else {
//                profilePane.setVisible(true);
//                profilePane.setDisable(false);
//                showProfile = true;
//            }
//        });
//        minim.setOnAction(event -> {
//            stage.setIconified(true);
//        });
//        close.setOnAction(event -> {
//            System.exit(0);
//            stage.close();
//        });
//        table.setRowFactory(table -> {
//            TableRow<SongInterface> row = new TableRow<>();
//            row.hoverProperty().addListener((Observable observable) -> {
//                SongInterface song = row.getItem();
//
//                if (song != null) {
//                    if (row.isHover()) {
//                        model.setSelectedSong(song);
//                        model.getSelectedSong().getAdd().setVisible(true);
//                        model.getSelectedSong().getEdit().setVisible(true);
//                        model.getSelectedSong().getPlay().setVisible(true);
//                        model.getSelectedSong().getDel().setVisible(true);
//                    } else {
//                        model.getSelectedSong().getAdd().setVisible(false);
//                        model.getSelectedSong().getEdit().setVisible(false);
//                        model.getSelectedSong().getPlay().setVisible(false);
//                        model.getSelectedSong().getDel().setVisible(false);
//                    }
//
//                    model.getSelectedSong().getPlay().setOnAction(event -> {
//                        model.setCurrentSong(model.getSelectedSong());
//                    });
//                    model.getSelectedSong().getPlay().setOnMouseEntered(event -> {
//                        model.getSelectedSong().getPlay().setPrefSize(32, 32);
//                    });
//                    model.getSelectedSong().getPlay().setOnMouseExited(event -> {
//                        model.getSelectedSong().getPlay().setPrefSize(30, 30);
//                    });
//
//                    model.getSelectedSong().getAdd().setOnAction(event -> {
//
//                    });
//                    model.getSelectedSong().getAdd().setOnMouseEntered(event -> {
//                        model.getSelectedSong().getAdd().setPrefSize(32, 32);
//                    });
//                    model.getSelectedSong().getAdd().setOnMouseExited(event -> {
//                        model.getSelectedSong().getAdd().setPrefSize(30, 30);
//                    });
//
//                    model.getSelectedSong().getEdit().setOnAction(event -> {
//
//                    });
//                    model.getSelectedSong().getEdit().setOnMouseEntered(event -> {
//                        model.getSelectedSong().getEdit().setPrefSize(32, 32);
//                    });
//                    model.getSelectedSong().getEdit().setOnMouseExited(event -> {
//                        model.getSelectedSong().getEdit().setPrefSize(30, 30);
//                    });
//
//                    model.getSelectedSong().getDel().setOnAction(event -> {
//
//                    });
//                    model.getSelectedSong().getDel().setOnMouseEntered(event -> {
//                        model.getSelectedSong().getDel().setPrefSize(32, 32);
//                    });
//                    model.getSelectedSong().getDel().setOnMouseExited(event -> {
//                        model.getSelectedSong().getDel().setPrefSize(30, 30);
//                    });
//                }
//            });
//
//            return row;
//        });
//        table.setOnMouseClicked(event -> {
//            model.setSelectedSong((SongInterface) table.getSelectionModel().getSelectedItem());
//
//            if (event.getClickCount() == 2) {
//                model.setCurrentSong((SongInterface) table.getSelectionModel().getSelectedItem());
//            }
//
//            if (playlistPane.isVisible()) {
//                playlistPane.setVisible(false);
//                playlistPane.setDisable(true);
//            }
//        });
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (filteredData != null) {
//                filteredData.setPredicate(song -> {
//                    if (newValue == null || newValue.isEmpty()) {
//                        return true;
//                    }
//
//                    String filter = newValue.toLowerCase();
//                    if (song.getName().toLowerCase().contains(filter)) {
//                        return true;
//                    }
//
//                    return false;
//                });
//
//                songView.updateTable(filteredData);
//            }
//        });
    }

    private void init() {

        songView = new SongListView(model, table, listLabel, stage);

        if (LocalDateTime.now().getHour() >= 0 && LocalDateTime.now().getHour() < 12) {
            if (model.getUser() != null) {
                greetings.setText("Good morning, " + model.getUser().getName() + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good morning, Guest!");
                or.setVisible(true);
            }
        } else if (LocalDateTime.now().getHour() == 12) {
            if (model.getUser() != null) {
                greetings.setText("Good day, " + model.getUser().getName() + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good day, Guest!");
                or.setVisible(true);
            }
        } else if (LocalDateTime.now().getHour() > 12 && LocalDateTime.now().getHour() < 18) {
            if (model.getUser() != null) {
                greetings.setText("Good afternoon, " + model.getUser().getName() + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good afternoon, Guest!");
                or.setVisible(true);
            }
        } else if (LocalDateTime.now().getHour() >= 18 && LocalDateTime.now().getHour() <= 23) {
            if (model.getUser() != null) {
                greetings.setText("Good evening, " + model.getUser().getName() + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good evening, Guest!");
                or.setVisible(true);
            }
        }

        profilePane.setVisible(false);
        profilePane.setDisable(true);
        playlistPane.setVisible(false);
        playlistPane.setDisable(true);
        lookPlaylist.setVisible(false);
        showProfile = false;

        playlistVBox.setMaxHeight(Double.MAX_VALUE);
        playlistVBox.setSpacing(20);

        for (int i = 0; i < 10; i++) {
            Playlist p = new Playlist();
            p.setName("Playlist" + (i + 1));

            Label label = new Label();
            label.setText("     " + p.getName());
            label.setAlignment(Pos.CENTER_LEFT);
            label.setFont(new Font("Segoe UI", 14));
            label.setStyle("-fx-text-fill: gray;");
            label.setPrefWidth(200);

            MenuItem item = new MenuItem("DeletePlaylist");
            item.setOnAction(event -> {
                listOfPlaylist.remove(label);
                updatePlaylist();
            });
            MenuItem editPlaylist = new MenuItem("Edit Playlist");
            ContextMenu menu = new ContextMenu();
            menu.getItems().add(editPlaylist);
            menu.getItems().add(item);
            label.setContextMenu(menu);

            label.setOnMouseClicked(event -> {
                System.out.println(p.getName());
            });
            label.setOnMouseEntered(event -> {
                label.setStyle("-fx-text-fill: white;");
            });
            label.setOnMouseExited(event -> {
                label.setStyle("-fx-text-fill: gray;");
            });

            listOfPlaylist.add(label);
            updatePlaylist();
        }
    }

    public void addPlaylist(Playlist p) {
        if (listOfPlaylist.get(0).getText().trim().equalsIgnoreCase("No playlist created")) {
            listOfPlaylist.clear();
        }

        p.setName("NewPlaylist");

        Label label = new Label();
        label.setText("     " + p.getName());
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(new Font("Segoe UI", 14));
        label.setStyle("-fx-text-fill: gray;");
        label.setPrefWidth(200);

        MenuItem item = new MenuItem("DeletePlaylist");
        item.setOnAction(event -> {
            listOfPlaylist.remove(label);
            updatePlaylist();
        });
        ContextMenu menu = new ContextMenu();
        menu.getItems().add(item);
        label.setContextMenu(menu);

        label.setOnMouseClicked(event -> {
            System.out.println(p.getName());
        });
        label.setOnMouseEntered(event -> {
            label.setStyle("-fx-text-fill: white;");
        });
        label.setOnMouseExited(event -> {
            label.setStyle("-fx-text-fill: gray;");
        });

        listOfPlaylist.add(label);
        updatePlaylist();
    }

    public void updatePlaylist() {
        playlistVBox.getChildren().clear();
        if (listOfPlaylist.size() == 0) {
            Label label = new Label();
            label.setText("     No playlist created");
            label.setAlignment(Pos.CENTER_LEFT);
            label.setFont(new Font("Segoe UI", 14));
            label.setStyle("-fx-text-fill: gray;");
            label.setPrefWidth(200);
            listOfPlaylist.add(label);
        }
        playlistVBox.getChildren().setAll(listOfPlaylist);
    }

    @Override
    public void update() {
        if (model.getCurrentSong() != null) {
            songName.setText(model.getCurrentSong().getName());
            artistName.setText(model.getCurrentSong().getArtist());
            int x = model.getCurrentSong().getLength();
            end.setText(String.format("%02d", x / 60) + ":" + String.format("%02d", x % 60));
        }
        
        updatePlaylist();
        
        if (model.getUser() != null) {
            login.setStyle("-fx-background-image: url('/Files/viewprofile_button.png')");
            signup.setStyle("-fx-background-image: url('/Files/logout_button.png')");
        } else {
            login.setStyle("-fx-background-image: url('/Files/login_button.png');");
            signup.setStyle("-fx-background-image: url('/Files/signup_button_red-black.png');");
        }

        if (LocalDateTime.now().getHour() >= 0 && LocalDateTime.now().getHour() < 12) {
            if (model.getUser() != null) {
                greetings.setText("Good morning, " + model.getUser().getName().substring(0, model.getUser().getName().indexOf(" ")) + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good morning, Guest!");
                or.setVisible(true);
            }
        } else if (LocalDateTime.now().getHour() == 12) {
            if (model.getUser() != null) {
                greetings.setText("Good day, " + model.getUser().getName().substring(0, model.getUser().getName().indexOf(" ")) + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good day, Guest!");
                or.setVisible(true);
            }
        } else if (LocalDateTime.now().getHour() > 12 && LocalDateTime.now().getHour() < 18) {
            if (model.getUser() != null) {
                greetings.setText("Good afternoon, " + model.getUser().getName().substring(0, model.getUser().getName().indexOf(" ")) + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good afternoon, Guest!");
                or.setVisible(true);
            }
        } else if (LocalDateTime.now().getHour() >= 18 && LocalDateTime.now().getHour() <= 23) {
            if (model.getUser() != null) {
                greetings.setText("Good evening, " + model.getUser().getName().substring(0, model.getUser().getName().indexOf(" ")) + "!");
                or.setVisible(false);
            } else {
                greetings.setText("Good evening, Guest!");
                or.setVisible(true);
            }
        }
    }
}
