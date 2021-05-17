package controller;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.inject.Inject;

import javafx.scene.control.cell.PropertyValueFactory;
import sudoku.GameResult;
import sudoku.LeadBoard;
import sudoku.utility.IOclass;


public class leadboardcontroller {
    @Inject private FXMLLoader fxmlLoader;

    @FXML private TableView<GameResult> leadboard;

    @FXML private TableColumn<GameResult, String> player;

    @FXML private TableColumn<GameResult, Long> time;

    private LeadBoard board;

    @FXML
    private void initialize() throws IOException {

        board = IOclass.getBoard();

        ObservableList<GameResult> list = FXCollections.observableArrayList(board.leadboard);

        player.setCellValueFactory(new PropertyValueFactory<GameResult, String>("Player"));
        time.setCellValueFactory(new PropertyValueFactory<GameResult, Long>("Time"));
        leadboard.setItems(list);
    }
}
