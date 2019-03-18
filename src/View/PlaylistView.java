/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.FacadeModel;
import Model.PlaylistInterface;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 *
 * @author Stanley Sie
 */
public class PlaylistView extends View {

    private FacadeModel model;
    private Label listLabel;
    private TableView table;
    private TableColumn title;
    private TextField changeTitle;
    private Button saveTitle;

    private ObservableList<PlaylistInterface> playlists;

    public PlaylistView(FacadeModel model, TableView table, Label listLabel, TextField changeTitle, Button saveTitle) {
        this.model = model;
        this.table = table;
        this.listLabel = listLabel;
        this.changeTitle = changeTitle;
        this.saveTitle = saveTitle;

        playlists = FXCollections.observableArrayList();
        this.model.attach(this);

        init();
    }

    private void init() {
        title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory("name"));
        title.setPrefWidth(220);
        title.setStyle("-fx-alignment: CENTER_LEFT;-fx-text-fill: white;");
        title.setResizable(false);

        update();
    }

    public void showPlaylist() {
        changeTitle.setVisible(false);
        changeTitle.setDisable(true);
        saveTitle.setVisible(false);
        saveTitle.setDisable(true);

        listLabel.setText(model.getCurrentPlaylist().getName());
        table.getColumns().setAll(title);
        table.setItems(playlists);
    }

    public void loadPlaylist() {
        
    }

    @Override
    public void update() {

    }
}
